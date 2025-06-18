package com.example.mediapp.ui.Catalogo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mediapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class CatalogoFragment extends Fragment{
    public  String servidor = "http://10.0.2.2/MediApp/";
    ListView lista;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_catalogo, container, false);
        lista = (ListView) rootView.findViewById(R.id.lstLista_M);


        ListarMedicamento();


        return rootView;
    }

    private void ListarMedicamento() {
        //Declarar la URL
        String url = servidor + "producto_mostrar.php";

        //Enviar parámetros
        RequestParams requestParams = new RequestParams();

        //Envio al web service y respuesta
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respuesta = new String(responseBody);
                //Toast.makeText(getApplicationContext(), "Respuesta: " + respuesta, Toast.LENGTH_LONG).show();
                // Parsear el JSON
                try {
                    JSONArray jsonArray = new JSONArray(respuesta);

                    // Crear una lista para almacenar los objetos
                    ArrayList<Catalogo> mediList = new ArrayList<>();

                    // Recorrer el array JSON y agregar cada contacto a la lista
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject contactoJson = jsonArray.getJSONObject(i);

                        int id_medicamento = contactoJson.getInt("id_producto");
                        String nom_medicamento = contactoJson.getString("nom_producto");
                        String descripcion_medicamento = contactoJson.getString("desc_producto");
                        double precio_medicamento = contactoJson.getDouble("precio_venta");



                        // Crear un objeto Contact y agregarlo a la lista
                        Catalogo catalogo = new Catalogo(id_medicamento,
                                nom_medicamento,
                                descripcion_medicamento,
                                precio_medicamento
                        );
                        mediList.add(catalogo);
                    }

                    Toast.makeText(getActivity(),String.valueOf(jsonArray.length()),Toast.LENGTH_SHORT).show();

                    // Crear el adaptador
                    CatalogoAdapter adapter = new CatalogoAdapter(getActivity(), mediList);
                    // Establecer el adaptador en el ListView
                    lista.setAdapter(adapter);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String mensaje = "Error: " + statusCode + " - " + error.getMessage();
                Toast.makeText(getActivity(),mensaje,Toast.LENGTH_LONG).show();
            }
        });
    }


}