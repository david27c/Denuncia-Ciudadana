package com.example.denunciaciudadana;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class VerDenunciasActivity extends AppCompatActivity {

    private RecyclerView rvDenuncias;
    private DenunciasAdapter adapter;
    private List<Denuncia> denunciasList;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_denuncias);

        rvDenuncias = findViewById(R.id.rvDenuncias);
        rvDenuncias.setLayoutManager(new LinearLayoutManager(this));

        denunciasList = new ArrayList<>();
        adapter = new DenunciasAdapter(denunciasList);
        rvDenuncias.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        cargarDenuncias();
    }

    private void cargarDenuncias() {
        String userId = mAuth.getCurrentUser().getUid();

        db.collection("denuncias")
                .whereEqualTo("usuarioId", userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        denunciasList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Denuncia d = document.toObject(Denuncia.class);
                            denunciasList.add(d);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Error al cargar denuncias", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}