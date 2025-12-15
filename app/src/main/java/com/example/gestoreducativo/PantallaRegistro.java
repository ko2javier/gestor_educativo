package com.example.gestoreducativo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PantallaRegistro extends AppCompatActivity {

    private EditText edxNombre, edxApellidos, edxEmail, edxPass, edxRepPass, edxBirth;
    private RadioButton rbProfesor, rbAlumno;
    private CheckBox checkCondiciones;
    private Button btnRegister, btnCancelar;

    private DatabaseReference dbUsers;
    private View rootView; // vista raíz para Snackbar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);

        // Vista raíz (para Snackbar)
        rootView = findViewById(android.R.id.content);

        // XML
        edxNombre = findViewById(R.id.edx_nombre);
        edxApellidos = findViewById(R.id.edx_apellidos);
        edxEmail = findViewById(R.id.edx_email);
        edxPass = findViewById(R.id.edx_pss);
        edxRepPass = findViewById(R.id.edx_rep_pass);
        edxBirth = findViewById(R.id.edx_birth);

        rbProfesor = findViewById(R.id.rb_profesor);
        rbAlumno = findViewById(R.id.rb_alumno);
        checkCondiciones = findViewById(R.id.check);

        btnRegister = findViewById(R.id.btn_register);
        btnCancelar = findViewById(R.id.cancelar);

        // Firebase
        FirebaseDatabase database =
                FirebaseDatabase.getInstance("https://gestoreducativo-a2d44-default-rtdb.firebaseio.com");

       dbUsers = database.getReference("users");

        btnRegister.setOnClickListener(v -> registrarUsuario());

        btnCancelar.setOnClickListener(v ->
                startActivity(new Intent(this, PantallaInicio.class))
        );
    }

    // =====================================================
    // REGISTRO SIN VALIDACIONES (solo Firebase)
    // =====================================================
    private void registrarUsuario() {

        String nombre = edxNombre.getText().toString().trim();
        String apellidos = edxApellidos.getText().toString().trim();
        String email = edxEmail.getText().toString().trim();
        String password = edxPass.getText().toString().trim();
        String fecha = edxBirth.getText().toString().trim();

        String tipoUsuario = rbProfesor.isChecked() ? "profesor" : "alumno";

        // Firebase no permite '.' en las claves
        String emailKey = email.replace(".", "_");

        dbUsers.child(emailKey).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {

                            Snackbar.make( rootView, "El usuario ya existe",Snackbar.LENGTH_SHORT).show();

                        } else {

                            Usuario usuario = new Usuario(nombre, apellidos,email, password ,fecha, tipoUsuario);

                            dbUsers.child(emailKey).setValue(usuario)
                                    .addOnSuccessListener(unused -> {
                                        Snackbar.make( rootView, "Usuario registrado correctamente",Snackbar.LENGTH_SHORT).show();


                                        startActivity(new Intent(PantallaRegistro.this,MenuPrincipal.class));
                                        finish();
                                    })
                                    .addOnFailureListener(e ->
                                            Snackbar.make( rootView, "Error al registrar usuario",Snackbar.LENGTH_SHORT).show()
                                    );
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Snackbar.make(
                                rootView,
                                "Error de conexión con Firebase",
                                Snackbar.LENGTH_SHORT
                        ).show();
                    }
                });
    }
}
