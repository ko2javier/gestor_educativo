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

public class EditarModuloActivity extends AppCompatActivity {

    private EditText edxId, edxNombreCompleto, edxNombreCorto, edxHoras;
    private Button btnGuardar, btnCancelar;

    private DatabaseReference dbModulos;
    private View rootView;

    private String idModulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_modulo);

        rootView = findViewById(android.R.id.content);

        edxId = findViewById(R.id.edx_idModulo);
        edxNombreCompleto = findViewById(R.id.edx_nombreCompleto);
        edxNombreCorto = findViewById(R.id.edx_nombreCorto);
        edxHoras = findViewById(R.id.edx_horas);

        btnGuardar = findViewById(R.id.btn_guardar);
        btnCancelar = findViewById(R.id.btn_cancelar);

        idModulo = getIntent().getStringExtra("ID_MODULO");
        if (idModulo == null || idModulo.isEmpty()) {
            Snackbar.make(rootView, "ID de módulo no recibido", Snackbar.LENGTH_SHORT).show();
            finish();
            return;
        }

        FirebaseDatabase database =
                FirebaseDatabase.getInstance("https://gestoreducativo-a2d44-default-rtdb.firebaseio.com");
        dbModulos = database.getReference("modulos");

        cargarModulo(idModulo);

        btnCancelar.setOnClickListener(v -> finish());
        btnGuardar.setOnClickListener(v -> guardarCambios());
    }

    private void cargarModulo(String id) {

        dbModulos.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Modulo m = snapshot.getValue(Modulo.class);
                if (m == null) {
                    Snackbar.make(rootView, "No existe el módulo", Snackbar.LENGTH_SHORT).show();
                    finish();
                    return;
                }

                // Aseguramos ID
                if (m.getIdModulo() == null || m.getIdModulo().isEmpty()) {
                    m.setIdModulo(id);
                }

                edxId.setText(m.getIdModulo());
                edxNombreCompleto.setText(m.getNombreCompleto());
                edxNombreCorto.setText(m.getNombreCorto());
                edxHoras.setText(String.valueOf(m.getNumeroHoras()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Snackbar.make(rootView, "Error cargando módulo", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void guardarCambios() {

        String nombreCompleto = edxNombreCompleto.getText().toString().trim();
        String nombreCorto = edxNombreCorto.getText().toString().trim();

        int horas = 0;
        try {
            horas = Integer.parseInt(edxHoras.getText().toString().trim());
        } catch (Exception ignored) { }

        Modulo actualizado = new Modulo(idModulo, nombreCompleto, nombreCorto, horas);

        dbModulos.child(idModulo).setValue(actualizado)
                .addOnSuccessListener(unused -> {
                    Snackbar.make(rootView, "Módulo actualizado", Snackbar.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e ->
                        Snackbar.make(rootView, "Error al guardar", Snackbar.LENGTH_SHORT).show()
                );
    }
}
