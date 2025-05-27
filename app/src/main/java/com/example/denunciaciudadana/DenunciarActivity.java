package com.example.denunciaciudadana;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.ArchTaskExecutor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DenunciarActivity extends AppCompatActivity {

    private EditText etTitulo, etDescripcion;
    private Button btnEnviar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denunciar);

        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnEnviar = findViewById(R.id.btnEnviarDenuncia);

        mAuth = FirebaseAuth.getInstance();
        ArchTaskExecutor FirebaseFirestore;
        db = FirebaseFirestore.getInstance();

        btnEnviar.setOnClickListener(v -> {
            String titulo = etTitulo.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();

            if (titulo.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            guardarDenuncia(titulo, descripcion);
        });
    }

    private void guardarDenuncia(String titulo, String descripcion) {
        String userId = mAuth.getCurrentUser().getUid();

        Map<String, Object> denuncia = new HashMap<>();
        denuncia.put("usuarioId", userId);
        denuncia.put("titulo", titulo);
        denuncia.put("descripcion", descripcion);
        denuncia.put("fecha", new Date());

        db.collection("denuncias")
                .add(denuncia)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Denuncia enviada con éxito", Toast.LENGTH_SHORT).show();
                    finish(); // Volver a menú principal
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al enviar denuncia: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}