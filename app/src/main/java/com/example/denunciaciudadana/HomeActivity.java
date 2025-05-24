package com.example.denunciaciudadana;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.denunciaciudadana.adapters.ReporteAdapter;
import com.example.denunciaciudadana.models.Reporte;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReporteAdapter adapter;
    private List<Reporte> listaReportes;
    private Button fabReportar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.reportesRecyclerView);
        fabReportar = findViewById(R.id.fabReportar);

        listaReportes = new ArrayList<>();
        adapter = new ReporteAdapter(this, listaReportes);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fabReportar.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ReportProblemActivity.class);
            startActivity(intent);
        });

        cargarReportes();
    }

    private void cargarReportes() {
        FirebaseFirestore.getInstance().collection("reportes")
                .orderBy("fecha", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(this::onSuccess)
                .addOnFailureListener(e -> {
                    Toast.makeText(HomeActivity.this, "Error al cargar reportes", Toast.LENGTH_SHORT).show();
                });
    }

    private void onSuccess(QuerySnapshot queryDocumentSnapshots) {
        listaReportes.clear();
        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
            Reporte reporte = doc.toObject(Reporte.class);
            listaReportes.add(reporte);
        }
        adapter.notifyDataSetChanged();
    }
}