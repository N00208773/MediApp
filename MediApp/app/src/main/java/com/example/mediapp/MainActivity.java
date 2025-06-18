package com.example.mediapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mediapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //acceder al nav_header_main
        View headerView = binding.navView.getHeaderView(0);
        //accerder a los textview del nav_header_main
        TextView tvMenuUsuario = headerView.findViewById(R.id.tv_m_user);
        TextView tvMenuEmail = headerView.findViewById(R.id.tv_m_correo);

        //obtener datos de SharedPreferences
        String username = getSharedPreferences("datos", MODE_PRIVATE).getString("username", "");
        String password = getSharedPreferences("datos", MODE_PRIVATE).getString("password", "");
        String id_empleado = getSharedPreferences("datos", MODE_PRIVATE).getString("id_usuario", "");
        String nom_empleado = getSharedPreferences("datos", MODE_PRIVATE).getString("nom_usuario", "");
        String em_empleado = getSharedPreferences("datos", MODE_PRIVATE).getString("email_usuario", "");
        String nom_cargo = getSharedPreferences("datos", MODE_PRIVATE).getString("nom_cargo", "");
        int opc_catalogo=getSharedPreferences("datos", MODE_PRIVATE).getInt("opc_catalogo", 0);
        int opc_usuarios=getSharedPreferences("datos", MODE_PRIVATE).getInt("opc_usuarios", 0);
        int opc_proveedores=getSharedPreferences("datos", MODE_PRIVATE).getInt("opc_proveedores", 0);
        int opc_ventas=getSharedPreferences("datos", MODE_PRIVATE).getInt("opc_ventas", 0);
        int opc_productos=getSharedPreferences("datos", MODE_PRIVATE).getInt("opc_productos", 0);


        //mostrar datos en los textview
        tvMenuUsuario.setText(nom_empleado);
        tvMenuEmail.setText("Cargo: " + nom_cargo);

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_Catalogo, R.id.nav_Usuarios,R.id.nav_Proveedores,R.id.nav_Ventas,R.id.nav_Productos, R.id.nav_Configuracion,R.id.nav_Cerrar)
                .setOpenableLayout(drawer)
                .build();
        VerificarAcceso(navigationView,opc_catalogo,opc_usuarios,opc_proveedores,opc_ventas,opc_productos);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            // ✅ Si el usuario selecciona "Cerrar Sesión"
            if (id == R.id.nav_Cerrar) {
                logout(); // se ejecuta el método logout
                return true;
            }

            // Para los demás ítems, navegación normal
            boolean handled = NavigationUI.onNavDestinationSelected(item, navController);
            drawer.closeDrawers();
            return handled;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        //si presion action_logout se ejecuta la funcion logout
        menu.findItem(R.id.action_settings).setOnMenuItemClickListener(item -> {
            logout();
            return true;
        });



        return true;
    }

    private void logout() {
        // Borrar SharedPreferences
        getSharedPreferences("datos", MODE_PRIVATE).edit()
                .remove("username")
                .remove("password")
                .remove("id_empleado")
                .remove("nom_empleado")
                .remove("em_empleado")
                .apply();

        // Ir al Login y cerrar MainActivity
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

    private void VerificarAcceso(NavigationView navigationView,int opc_catalogo,int opc_usuarios,int opc_proveedores,int opc_venta,int opc_productos) {

        if(opc_catalogo==0){
            navigationView.getMenu().findItem(R.id.nav_Catalogo).setVisible(false);
        }
        if(opc_usuarios==0){
            navigationView.getMenu().findItem(R.id.nav_Usuarios).setVisible(false);
        }
        if(opc_proveedores==0){
            navigationView.getMenu().findItem(R.id.nav_Proveedores).setVisible(false);
        }
        if(opc_venta==0){
            navigationView.getMenu().findItem(R.id.nav_Ventas).setVisible(false);
        }
        if(opc_productos==0){
            navigationView.getMenu().findItem(R.id.nav_Productos).setVisible(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}