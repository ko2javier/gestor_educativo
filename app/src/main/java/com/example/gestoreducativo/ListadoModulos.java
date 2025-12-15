package com.example.gestoreducativo;


import android.os.Bundle;
import android.view.View;

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

    private RecyclerView rvModulos;
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

        // Más adelante:
        // Intent a ActivityEditarModulo
    }

    @Override
    public void onBorrarClick(Modulo modulo) {

        Snackbar.make( rvModulos, "Borrar: " + modulo.getNombreCompleto(), Snackbar.LENGTH_SHORT
        ).show();

        // Más adelante:
        // borrar de Firebase
    }

}
