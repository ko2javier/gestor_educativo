package com.example.gestoreducativo;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PantallaInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent pantallaRegistro=new Intent(this,PantallaRegistro.class);
        EdgeToEdge.enable(this);
        Intent PantallaRecibir=new Intent(this,ActividadREcibirinfo.class);
        setContentView(R.layout.activity_pantalla_inicio);
        Button inicioSesion= findViewById(R.id.inicioSesion);
        TextView saludar=findViewById(R.id.saludo);
        Button TraerDatos=findViewById(R.id.TraerDatos);
        EditText correo=findViewById(R.id.correo);
        Bundle b=new Bundle();
        Button registroBBDD=findViewById(R.id.RegistroBBDD);
        EditText contrasena=findViewById(R.id.contraseña);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button id_registro= findViewById(R.id.Registro);
        Button preferencias=findViewById(R.id.BotonPreferencias);

        SharedPreferences preferenciaTamañoTexto=getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferenciaTamañoTexto.edit();

        String Tamano=preferenciaTamañoTexto.getString("Tamaño","valor por defecto");
        if(Tamano.equals("grande")){
            saludar.setTextSize(70);
        }
        preferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("Tamaño", "grande");
                editor.commit();
            }
        });
        UsuariosSQliteHelper baseDatos=new UsuariosSQliteHelper(this,"Usuarios",null,1);
        SQLiteDatabase db=baseDatos.getReadableDatabase();
        //db.execSQL("Insert Into InicioSesion values(correo.getstring(),contraseña.getString())");

        Button btn_firebase= findViewById(R.id.btn_firebase);
        TextView txt_firebase= findViewById(R.id.txt_firebase);

        btn_firebase.setOnClickListener(v->{
            FirebaseDatabase database = FirebaseDatabase.getInstance("https://gestoreducativo-a2d44-default-rtdb.firebaseio.com");
            DatabaseReference myRef = database.getReference("message");

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.getValue(String.class);
                    Log.d(TAG, "Value is: " + value);
                    txt_firebase.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        });


        b.putString("NombreUsuario",correo.getText().toString());
        b.putString("Contrasena","1234");
        b.putInt("edad",24);
       // PantallaRecibir.putExtras(b);
          PantallaRecibir.putExtra("b",b);

        Button Bundle=findViewById(R.id.PruebaBundle);
        Button registro= findViewById(R.id.Registro);
        TextView saludo=findViewById(R.id.saludo);


        inicioSesion.setOnClickListener(v->{
            Intent i= new Intent(this, MenuPrincipal.class);
            startActivity(i);
        });

        Bundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PantallaRecibir);
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://gestoreducativo-a2d44-default-rtdb.firebaseio.com");
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");
                //startActivity(pantallaRegistro);
            }
        });
        registroBBDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                UsuariosSQliteHelper baseDatos=new UsuariosSQliteHelper(this,"Usuarios",null,1);
                SQLiteDatabase db=baseDatos.getReadableDatabase();*/
                String nombre=correo.getText().toString();
                String pasword=contrasena.getText().toString();

                db.execSQL(
                        "INSERT INTO InicioSesion (nombre, contasena) VALUES (?, ?)",
                        new Object[]{nombre, pasword}
                        //"INSERT INTO InicioSesion (nombre, contasena) VALUES ('"+nombre+"','"+pasword+"')"
                );
            }
        });
        TraerDatos.setOnClickListener(v->{
            String[]args=new String[]{"2"};
            Cursor miCursor=db.rawQuery("Select nombre,contasena from InicioSesion where idUsuario=?",args);
            if(miCursor.moveToFirst()){
                correo.setText(miCursor.getString(0));
                contrasena.setText(miCursor.getString(1));
            }
        });
    }
}