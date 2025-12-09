package com.example.gestoreducativo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class ListadoModulos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_modulos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listadoModulos=findViewById(R.id.ListModulos);
        String[] nombreModulos=new String[]{"Bases de datos","Acceso a Datos","Sistemas de gestion Empresarial","Bases de datos","Acceso a Datos","Sistemas de gestion Empresarial","Bases de datos","Acceso a Datos","Sistemas de gestion Empresarial"};
        ArrayAdapter<String>adaptadorModulos=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nombreModulos);
        listadoModulos.setAdapter(adaptadorModulos);
        listadoModulos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String elementoSeleccionado=(String) parent.getAdapter().getItem(position);
                parent.getItemAtPosition(position);
                Snackbar BarraInformativa=Snackbar.make(ListadoModulos.this,view, "Elemento seleccionado " + elementoSeleccionado, Snackbar.LENGTH_SHORT);
                BarraInformativa.show();
            }
        });
    }
}