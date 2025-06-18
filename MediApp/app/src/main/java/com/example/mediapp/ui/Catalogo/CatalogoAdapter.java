package com.example.mediapp.ui.Catalogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mediapp.R;

import java.util.List;

public class CatalogoAdapter extends BaseAdapter {
    private Context context;
    private List<Catalogo> medicamentos;

    public CatalogoAdapter(Context context, List<Catalogo> medicamentos) {
        this.context = context;
        this.medicamentos = medicamentos;
    }

    @Override
    public int getCount() {
        return medicamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return medicamentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Inflar el layout del item
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_medicamento, parent, false);
        }

        // Obtener los datos del producto
        Catalogo producto = medicamentos.get(position);

        // Referenciar las vistas
        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvNom = convertView.findViewById(R.id.tvNombre_US);
        TextView tvDes= convertView.findViewById(R.id.tvcargo_US);
        TextView tvPre = convertView.findViewById(R.id.tvuser_US);

        Button btnCarito = convertView.findViewById(R.id.btnAgregarCarito);

        btnCarito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context.getApplicationContext(),"Editar "+producto.getId(),Toast.LENGTH_LONG).show();
                //EditarProducto(producto.getId(), context);
            }
        });


        // Establecer los valores
        tvId.setText(String.valueOf(producto.getId()));
        tvNom.setText(producto.getNombre());
        tvDes.setText("Descripción: "+producto.getDescripcion());
        tvPre.setText("Precio Compra: S/. "+String.valueOf(producto.getPrecio()));

        return convertView;
    }

}
