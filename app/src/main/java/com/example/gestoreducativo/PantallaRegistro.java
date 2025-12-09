package com.example.gestoreducativo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PantallaRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent cancelar=new Intent(this,PantallaInicio.class);
        Intent AccederRegistro=new Intent(this,MenuPrincipal.class);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_registro);

        UsuariosSQliteHelper baseDatos=new UsuariosSQliteHelper(this,"Usuarios",null,1);
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        //db.execSQL();

        TextView pantallaRegistro=findViewById(R.id.Registro);
        Button Cancelar=findViewById(R.id.cancelar);
        //Button Registrarse=findViewById(R.id.Registrarte);
        EditText BienvenidaUser=findViewById(R.id.Nombre);
        Button Registrar=findViewById(R.id.Registrarte);
        Bundle RecogidaDatos=new Bundle();
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecogidaDatos.putString("NombreUsuario", String.valueOf(BienvenidaUser.getText()));
                AccederRegistro.putExtra("Rdatos" ,RecogidaDatos);
                startActivity(AccederRegistro);
            }
        });


        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(cancelar);
            }
        });
//        Registrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(AccederRegistro);
//            }
//        });

    }
}