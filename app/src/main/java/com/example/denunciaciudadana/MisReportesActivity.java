package com.example.denunciaciudadana;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.denunciaciudadana.adapters.MisReporteAdapter;
import com.example.denunciaciudadana.models.Reporte;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.List;

public class MisReportesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MisReporteAdapter adapter;
    private List<Reporte> listaReportes;
    private List<String> documentIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_reportes);

        recyclerView = findViewById(R.id.misReportesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaReportes = new ArrayList<>();
        documentIds = new ArrayList<>();
        adapter = new MisReporteAdapter(this, listaReportes, documentIds);

        recyclerView.setAdapter(adapter);

        cargarMisReportes();
    }

    private void cargarMisReportes() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseFirestore.getInstance().collection("reportes")
                .whereEqualTo("uid", uid)
                .orderBy("fecha", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    listaReportes.clear();
                    documentIds.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Reporte reporte = doc.toObject(Reporte.class);
                        listaReportes.add(reporte);
                        documentIds.add(doc.getId());
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error al cargar tus reportes", Toast.LENGTH_SHORT).show());
    }
}