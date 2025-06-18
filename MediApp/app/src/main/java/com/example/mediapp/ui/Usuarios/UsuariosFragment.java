package com.example.mediapp.ui.Usuarios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mediapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class UsuariosFragment extends Fragment {
    public String servidor = "http://10.0.2.2/MediApp/";
    ListView lista;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_usuarios, container, false);
        lista = rootView.findViewById(R.id.lstLista_US);

        ListarUsuarios();

        return rootView;
    }

    private void ListarUsuarios() {
        // Declarar la URL
        String url = servidor + "usuarios_mostrar.php";

        // Enviar parámetros
        RequestParams requestParams = new RequestParams();

        // Envio al web service y respuesta
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respuesta = new String(responseBody);
                try {
                    JSONArray jsonArray = new JSONArray(respuesta);

                    // Crear una lista para almacenar los objetos
                    ArrayList<Usuario> usuariosList = new ArrayList<>();

                    // Recorrer el array JSON y agregar cada usuario a la lista
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject usuarioJson = jsonArray.getJSONObject(i);

                        int id_usuarios = usuarioJson.getInt("id_usuarios");
                        int id_cargos = usuarioJson.getInt("id_cargos");
                        String user_usuario = usuarioJson.getString("user_usuario");
                        String pass_usuario = usuarioJson.getString("pass_usuario");
                        String nom_usuario = usuarioJson.getString("nom_usuario");
                        String apePa_usuario = usuarioJson.getString("apePa_usuario");
                        String apeMa_usuario = usuarioJson.getString("apeMa_usuario");
                        String email_usuario = usuarioJson.getString("email_usuario");
                        String cel_usuario = usuarioJson.getString("cel_usuario");
                        int edad_usuario = usuarioJson.getInt("edad_usuario");
                        String fc_re_usuario = usuarioJson.getString("fc_re_usuario");
                        int est_usuario = usuarioJson.getInt("est_usuario");
                        // Aquí está el cambio importante - obtenemos el nom_cargo del JSON
                        String nom_cargo = usuarioJson.getString("nom_cargo");

                        // Crear un objeto Usuario y agregarlo a la lista
                        Usuario usuario = new Usuario(
                                id_usuarios, id_cargos, user_usuario, pass_usuario,
                                nom_usuario, apePa_usuario, apeMa_usuario, email_usuario,
                                cel_usuario, edad_usuario, fc_re_usuario, est_usuario, nom_cargo
                        );
                        usuariosList.add(usuario);
                    }

                    // Crear el adaptador
                    UsuarioAdapter adapter = new UsuarioAdapter(getActivity(), usuariosList);
                    // Establecer el adaptador en el ListView
                    lista.setAdapter(adapter);

                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "Error al parsear JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String mensaje = "Error: " + statusCode + " - " + error.getMessage();
                Toast.makeText(getActivity(), mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }
}