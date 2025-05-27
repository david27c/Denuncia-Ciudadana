package com.example.denunciaciudadana;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DenunciarFragment extends Fragment {

    private EditText etTitulo, etDescripcion;
    private Button btnEnviar;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_denunciar, container, false);

        etTitulo = view.findViewById(R.id.etTitulo);
        etDescripcion = view.findViewById(R.id.etDescripcion);
        btnEnviar = view.findViewById(R.id.btnEnviarDenuncia);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        btnEnviar.setOnClickListener(v -> {
            String titulo = etTitulo.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();
            String uid = auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : "";

            if (TextUtils.isEmpty(titulo) || TextUtils.isEmpty(descripcion)) {
                Toast.makeText(getContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> denuncia = new HashMap<>();
            denuncia.put("titulo", titulo);
            denuncia.put("descripcion", descripcion);
            denuncia.put("uid", uid);
            denuncia.put("timestamp", System.currentTimeMillis());

            db.collection("denuncias")
                    .add(denuncia)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(getContext(), "Denuncia enviada", Toast.LENGTH_SHORT).show();
                        etTitulo.setText("");
                        etDescripcion.setText("");
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(getContext(), "Error al enviar", Toast.LENGTH_SHORT).show());
        });

        return view;
    }
}