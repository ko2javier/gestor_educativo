package com.example.gestoreducativo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActividadREcibirinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_actividad_recibirinfo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        //2A
//        Intent i=getIntent();
//        Bundle recogidaDatos= i.getExtras();
        //2B
        Intent i=getIntent();
        Bundle RecogidaDatos=i.getBundleExtra("b");
        String nombreUsuario=RecogidaDatos.getString("NombreUsuario");
        String Contraseña=RecogidaDatos.getString("Contrasena");
        int Edad=RecogidaDatos.getInt("edad");
        TextView Nombre= findViewById(R.id.textoInformativo);
        Nombre.setText("Hola "+nombreUsuario+"\n"+"La contraseña es: "+Contraseña+"\n"+"Y la edad es de: "+Edad);

    }
}