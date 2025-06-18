package com.example.mediapp.ui.Usuarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mediapp.R;

import java.util.List;

public class UsuarioAdapter extends BaseAdapter {
    private Context context;
    private List<Usuario> usuarios;

    public UsuarioAdapter(Context context, List<Usuario> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
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
            convertView = inflater.inflate(R.layout.item_usuarios, parent, false);
        }

        // Obtener los datos del usuario
        Usuario usuario = usuarios.get(position);

        // Referenciar las vistas
        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvNombre = convertView.findViewById(R.id.tvNombre_US);
        TextView tvCargo = convertView.findViewById(R.id.tvcargo_US);
        TextView tvUser = convertView.findViewById(R.id.tvuser_US);
        TextView tvPass = convertView.findViewById(R.id.tvpass_US);
        TextView tvApePa = convertView.findViewById(R.id.tvApePa_US);
        TextView tvApeMa = convertView.findViewById(R.id.tvApeMa_US);
        TextView tvEmail = convertView.findViewById(R.id.tvEmail_US);
        TextView tvCel = convertView.findViewById(R.id.tvCel_US);
        TextView tvEdad = convertView.findViewById(R.id.tvEdad_US);
        TextView tvFecha = convertView.findViewById(R.id.tvFecha_RE_US);



        // Establecer los valores
        tvId.setText(String.valueOf(usuario.getId_usuarios()));
        tvNombre.setText(usuario.getNom_usuario());
        tvCargo.setText("Cargo: " + usuario.getNom_cargo());
        tvUser.setText("Usuario: " + usuario.getUser_usuario());
        tvPass.setText("Contraseña: " + usuario.getPass_usuario());
        tvApePa.setText("Ap. Paterno: " + usuario.getApePa_usuario());
        tvApeMa.setText("Ap. Materno: " + usuario.getApeMa_usuario());
        tvEmail.setText("Email: " + usuario.getEmail_usuario());
        tvCel.setText("Celular: " + usuario.getCel_usuario());
        tvEdad.setText("Edad: " + usuario.getEdad_usuario());
        tvFecha.setText("Registro: " + usuario.getFc_re_usuario());

        return convertView;
    }
}