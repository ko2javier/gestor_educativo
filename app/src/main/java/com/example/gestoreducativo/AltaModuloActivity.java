package com.example.gestoreducativo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AltaModuloActivity extends AppCompatActivity {

    private EditText edxIdModulo, edxNombreCompleto, edxNombreCorto, edxHoras;
    private Button btnGuardar, btnCancelar;
    private  int horas = 0;
    private DatabaseReference dbModulos;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_modulo);

        rootView = findViewById(android.R.id.content);

        // Referencias UI
        edxIdModulo = findViewById(R.id.edx_idModulo);
        edxNombreCompleto = findViewById(R.id.edx_nombreCompleto);
        edxNombreCorto = findViewById(R.id.edx_nombreCorto);
        edxHoras = findViewById(R.id.edx_horas);

        btnGuardar = findViewById(R.id.btn_guardar);
        btnCancelar = findViewById(R.id.btn_cancelar);

        // Firebase
        FirebaseDatabase database =
                FirebaseDatabase.getInstance("https://gestoreducativo-a2d44-default-rtdb.firebaseio.com");
        dbModulos = database.getReference("modulos");

        btnCancelar.setOnClickListener(v -> finish());

        btnGuardar.setOnClickListener(v -> guardarModulo());
    }

    private void guardarModulo() {

        String idModulo = edxIdModulo.getText().toString().trim();
        String nombreCompleto = edxNombreCompleto.getText().toString().trim();
        String nombreCorto = edxNombreCorto.getText().toString().trim();


        try {
            horas = Integer.parseInt(edxHoras.getText().toString().trim());
        } catch (Exception ignored) { }

        if (idModulo.isEmpty()) {
            Snackbar.make(rootView, "El ID del módulo es obligatorio", Snackbar.LENGTH_SHORT).show();
            return;
        }

        // Comprobar si el módulo ya existe
        dbModulos.child(idModulo).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {

                            Snackbar.make( rootView,"Ya existe un módulo con ese ID", Snackbar.LENGTH_SHORT
                            ).show();

                        } else {

                            Modulo nuevo = new Modulo(
                                    idModulo,
                                    nombreCompleto,
                                    nombreCorto,
                                    horas
                            );

                            dbModulos.child(idModulo).setValue(nuevo)
                                    .addOnSuccessListener(unused -> {
                                        Snackbar.make(rootView,"Módulo creado correctamente", Snackbar.LENGTH_SHORT
                                        ).show();
                                        finish();
                                    })
                                    .addOnFailureListener(e ->
                                            Snackbar.make( rootView,"Error al guardar módulo", Snackbar.LENGTH_SHORT
                                            ).show()
                                    );
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Snackbar.make(rootView,"Error de conexión con Firebase", Snackbar.LENGTH_SHORT
                        ).show();
                    }
                });
    }
}
