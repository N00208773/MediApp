package com.example.mediapp.ui.Productos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mediapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ProductoFragment extends Fragment {
    public static String servidor = "http://10.0.2.2/MediApp/";
    ListView lista;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_producto, container, false);
        lista = rootView.findViewById(R.id.lst_ListaP);
        ListarProductos();
        return rootView;
    }

    private void ListarProductos() {
        String url = servidor + "producto_mostrar_edit.php";
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray jsonArray = new JSONArray(new String(responseBody));
                    ArrayList<Productos> listaProductos = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject productoJson = jsonArray.getJSONObject(i);

                        Productos producto = new Productos(
                                productoJson.getInt("id_producto"),
                                productoJson.getString("nom_producto"),
                                productoJson.getString("desc_producto"),
                                productoJson.getString("nom_proveedor"),
                                productoJson.getDouble("precio_venta"),
                                productoJson.getDouble("precio_compra"),
                                productoJson.getString("stock")
                        );
                        listaProductos.add(producto);
                    }

                    ProductoAdapter adapter = new ProductoAdapter(getActivity(), listaProductos);
                    lista.setAdapter(adapter);

                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "Error al parsear datos: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getActivity(), "Error al conectar: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public static void EliminarProducto(int id, Context context)
    {
        //Declarar la URL
        String url = servidor + "eliminar_producto.php";

        //Enviar parámetros
        RequestParams requestParams = new RequestParams();
        requestParams.put("id_producto",id);

        //Envio al web service y respuesta
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respuesta = new String(responseBody);
                Toast.makeText(context, "Respuesta: " + respuesta, Toast.LENGTH_LONG).show();

                if (context instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) context;

                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
                    navController.navigate(R.id.action_nav_Productos_self);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String mensaje = "Error: " + statusCode + " - " + error.getMessage();
                Toast.makeText(context,mensaje,Toast.LENGTH_LONG).show();
            }
        });

    }
}