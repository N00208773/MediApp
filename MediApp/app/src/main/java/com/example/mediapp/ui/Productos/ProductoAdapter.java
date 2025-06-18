package com.example.mediapp.ui.Productos;

import static com.example.mediapp.ui.Productos.ProductoFragment.EliminarProducto;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mediapp.R;

import java.util.List;

public class ProductoAdapter extends BaseAdapter {
    private Context context;
    private List<Productos> productos;

    public ProductoAdapter(Context context, List<Productos> productos) {
        this.context = context;
        this.productos = productos;
    }

    @Override
    public int getCount() { return productos.size(); }

    @Override
    public Object getItem(int position) { return productos.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_producto, parent, false);
        }

        Productos producto = productos.get(position);

        TextView tvNombre = convertView.findViewById(R.id.tvnom_pro);
        TextView tvDesc = convertView.findViewById(R.id.tvdesc_pro);
        TextView tvProveedor = convertView.findViewById(R.id.tv_proveedor_pro);
        TextView tvPrecioVenta = convertView.findViewById(R.id.tv_precio_venta_pro);
        TextView tvPrecioCompra = convertView.findViewById(R.id.tv_precio_compra);
        TextView tvStock = convertView.findViewById(R.id.tv_stock_pro);
        Button btnEliminar = convertView.findViewById(R.id.btnEliminar_PRO);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Confirmación antes de eliminar
                new AlertDialog.Builder(context)
                        .setTitle("Confirmar eliminación")
                        .setMessage("¿Estás seguro de eliminar este producto?")
                        .setPositiveButton("Eliminar", (dialog, which) -> {
                            EliminarProducto(producto.getId(), context);
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });

        tvNombre.setText(producto.getNombre());
        tvDesc.setText(producto.getDescripcion());
        tvProveedor.setText("Proveedor: " + producto.getProveedor());
        tvPrecioVenta.setText("P. Venta: S/ " + producto.getPrecioVenta());
        tvPrecioCompra.setText("P. Compra: S/ " + producto.getPrecioCompra());
        tvStock.setText("Stock: " + producto.getStock());

        return convertView;
    }
}