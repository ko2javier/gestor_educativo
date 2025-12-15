package com.example.gestoreducativo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListadoModulos extends AppCompatActivity  implements ModuloAdapter.OnModuloClickListener{

    private RecyclerView rvModulos; private Button btnAltaModulo;
    private ModuloAdapter moduloAdapter;
    private List<Modulo> listaModulos;

    private DatabaseReference dbModulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_modulos);

        //  RecyclerView
        rvModulos = findViewById(R.id.rvModulos);
        rvModulos.setLayoutManager(new LinearLayoutManager(this));

        // Lista + Adapter
        listaModulos = new ArrayList<>();
        moduloAdapter = new ModuloAdapter(listaModulos, this);
        rvModulos.setAdapter(moduloAdapter);

        // Firebase
        FirebaseDatabase database =
                FirebaseDatabase.getInstance("https://gestoreducativo-a2d44-default-rtdb.firebaseio.com");

        dbModulos = database.getReference("modulos");
        btnAltaModulo = findViewById(R.id.btnAltaModulo);

        btnAltaModulo.setOnClickListener(v -> {
            Intent i = new Intent(ListadoModulos.this, AltaModuloActivity.class);
            startActivity(i);
        });

        // Cargar datos
        cargarModulos();
    }

    private void cargarModulos() {

        dbModulos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listaModulos.clear();

                for (DataSnapshot modSnapshot : snapshot.getChildren()) {

                    Modulo modulo = modSnapshot.getValue(Modulo.class);

                    if (modulo != null) {
                        if (modulo.getIdModulo() == null || modulo.getIdModulo().isEmpty()) {
                            modulo.setIdModulo(modSnapshot.getKey()); // clave BD/AD/SGE...
                        }
                        listaModulos.add(modulo);
                    }
                }

                moduloAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Snackbar.make(
                        rvModulos,
                        "Error al cargar módulos",
                        Snackbar.LENGTH_SHORT
                ).show();
            }
        });
    }

    @Override
    public void onEditarClick(Modulo modulo) {

        Snackbar.make(rvModulos,"Editar: " + modulo.getNombreCompleto(), Snackbar.LENGTH_SHORT
        ).show();
        Intent i = new Intent(this, EditarModuloActivity.class);
        i.putExtra("ID_MODULO", modulo.getIdModulo());
        startActivity(i);
    }
    private void borrarModulo(Modulo modulo) {

        dbModulos.child(modulo.getIdModulo()).removeValue()
                .addOnSuccessListener(unused ->
                        Snackbar.make(rvModulos, "Módulo eliminado", Snackbar.LENGTH_SHORT).show()
                )
                .addOnFailureListener(e ->
                        Snackbar.make(rvModulos, "Error al eliminar", Snackbar.LENGTH_SHORT).show()
                );
    }


    @Override
    public void onBorrarClick(Modulo modulo) {
        Snackbar.make( rvModulos, "Borrar: " + modulo.getNombreCompleto(), Snackbar.LENGTH_SHORT
        ).show();

        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Eliminar módulo")
                .setMessage("¿Seguro que deseas borrar \"" + modulo.getNombreCompleto() + "\"?")
                .setPositiveButton("Borrar", (dialog, which) -> borrarModulo(modulo))
                .setNegativeButton("Cancelar", null)
                .show();
    }


}
