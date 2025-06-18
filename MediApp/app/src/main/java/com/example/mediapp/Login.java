package com.example.mediapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity implements View.OnClickListener {

    public static final String servidor = "http://10.0.2.2/MediApp/";

    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonLogin;
    ProgressBar progressBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername2);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword2);
        buttonLogin = (Button) findViewById(R.id.buttonLogin2);
        buttonLogin.setOnClickListener(this);
        progressBarLogin = (ProgressBar) findViewById(R.id.progressBarLogin2);

        //verificar si hay datos guardados en SharedPreferences
        String username = getSharedPreferences("datos", MODE_PRIVATE).getString("username", "");
        String password = getSharedPreferences("datos", MODE_PRIVATE).getString("password", "");
        if (!username.isEmpty() && !password.isEmpty()) {
            IniciarSesion(username, password);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonLogin2) {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            //verificar si los campos estan completos
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                IniciarSesion(username, password);

            }
        }
    }

    private void IniciarSesion(String username, String password) {
        //Mostrar el ProgressBar
        progressBarLogin.setVisibility(View.VISIBLE);
        //Ocultar el botón de inicio de sesión
        buttonLogin.setVisibility(View.GONE);

        String url = servidor+"usuario_autentificar.php";

        RequestParams requestParams = new RequestParams();
        requestParams.put("username", username);
        requestParams.put("password", password);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressBarLogin.setVisibility(View.GONE);
                buttonLogin.setVisibility(View.VISIBLE);

                String respuesta = new String(responseBody);
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(respuesta);

                    if(jsonArray.length()==0) {
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    } else {
                        String id_empleado="";
                        String nom_empleado="";
                        String em_empleado="";
                        String nom_cargo="";
                        int opc_catalogo=0;
                        int opc_usuarios=0;
                        int opc_proveedores=0;
                        int opc_ventas=0;
                        int opc_compras=0;
                        int opc_productos=0;

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject contactoJson = jsonArray.getJSONObject(i);
                            id_empleado = contactoJson.getString("id_usuarios");
                            nom_empleado = contactoJson.getString("nom_usuario");
                            em_empleado = contactoJson.getString("email_usuario");
                            nom_cargo = contactoJson.getString("nom_cargo"); // 👈 NUEVO

                            opc_catalogo = contactoJson.getInt("opc_catalogo");
                            opc_usuarios = contactoJson.getInt("opc_usuarios");
                            opc_proveedores = contactoJson.getInt("opc_proveedores");
                            opc_ventas = contactoJson.getInt("opc_ventas");
                            opc_compras = contactoJson.getInt("opc_compras");
                            opc_productos = contactoJson.getInt("opc_productos");
                        }

                        Toast.makeText(getApplicationContext(), "Bienvenido " + nom_empleado, Toast.LENGTH_LONG).show();

                        // Guardar TODOS los datos actualizados
                        GuardarSharedPreferences(username, password, id_empleado, nom_empleado, em_empleado, nom_cargo,
                                opc_catalogo, opc_usuarios, opc_proveedores, opc_ventas, opc_compras, opc_productos);


                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        //finish();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                progressBarLogin.setVisibility(View.GONE);
                buttonLogin.setVisibility(View.VISIBLE);
                String mensaje = "Error: " + statusCode + " - " + error.getMessage();
                Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void GuardarSharedPreferences(String username, String password, String id_empleado, String nom_empleado, String em_empleado,String nom_cargo, int opc_catalogo, int opc_usuarios, int opc_proveedores, int opc_ventas, int opc_compras, int opc_productos) {
        //guardar usuario y contraseña en modo privado
        getSharedPreferences("datos", MODE_PRIVATE).edit()
                .putString("username", username)
                .putString("password", password)
                .putString("id_usuario", id_empleado)
                .putString("nom_usuario", nom_empleado)
                .putString("email_usuario", em_empleado)
                .putString("nom_cargo", nom_cargo)
                .putInt("opc_catalogo", opc_catalogo)
                .putInt("opc_usuarios", opc_usuarios)
                .putInt("opc_proveedores", opc_proveedores)
                .putInt("opc_ventas", opc_ventas)
                .putInt("opc_compras", opc_compras)
                .putInt("opc_productos", opc_productos)
                .apply();

    }
}