package com.example.gestoreducativo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);
        TextView traerBBDD=findViewById(R.id.traerBBDD);

        Intent EditaPerfil=new Intent(this,EditarPerfil.class);
        Intent CerrarSesion=new Intent(this,PantallaInicio.class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        /*Intent i=getIntent();
        Bundle NombreBU=i.getBundleExtra("Rdatos");
        String nombre=NombreBU.getString("NombreUsuario");*/
        TextView nombreUsuario=findViewById(R.id.saludo);
        //nombreUsuario.setText("Bienvenido "+nombre);
        Intent pantallaRegistro= new Intent(this,MenuPrincipal.class);
        Button gestorModulos=findViewById(R.id.GestorModulos);
        Intent pantallaLsitado=new Intent(this,ListadoModulos.class);
        gestorModulos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(pantallaLsitado);
            }
        });


        Button EditarPerfil=findViewById(R.id.GestorPerfil);
        Button Cerrarsesion=findViewById(R.id.CerrarSesion);
        EditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(EditaPerfil);
            }
        });

        Cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CerrarSesion);
            }
        });
    }
}