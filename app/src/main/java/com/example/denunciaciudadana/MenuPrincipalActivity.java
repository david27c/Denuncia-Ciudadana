package com.example.denunciaciudadana;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MenuPrincipalActivity extends AppCompatActivity {

    private Button btnDenunciar, btnVerDenuncias, btnCerrarSesion;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        // Inicializar Firebase Auth y Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnDenunciar = findViewById(R.id.btnDenunciar);
        btnVerDenuncias = findViewById(R.id.btnVerDenuncias);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnDenunciar.setOnClickListener(v -> {
            // Redirigir a DenunciarActivity
            startActivity(new Intent(MenuPrincipalActivity.this, DenunciarActivity.class));
        });

        btnVerDenuncias.setOnClickListener(v -> {
            // Redirigir a VerDenunciasActivity
            startActivity(new Intent(MenuPrincipalActivity.this, VerDenunciasActivity.class));
        });

        btnCerrarSesion.setOnClickListener(v -> {
            // Cerrar sesión con FirebaseAuth y volver a LoginActivity
            mAuth.signOut();
            startActivity(new Intent(MenuPrincipalActivity.this, LoginActivity.class));
            finish();
        });
    }

    // Ejemplo método para guardar una denuncia en Firestore
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
                    Toast.makeText(this, "Denuncia guardada con éxito", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al guardar denuncia: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            // Usuario no está logueado, ir al login
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}