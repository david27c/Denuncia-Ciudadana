package com.example.denunciaciudadana;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.example.denunciaciudadana.models.Reporte;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetalleReporteActivity extends AppCompatActivity {

    private ImageView imagenReporte;
    private TextView tipoText, estadoText, fechaText, descripcionText, ubicacionText;
    private Button chatBtn;

    private String reporteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reporte);

        imagenReporte = findViewById(R.id.imagenReporte);
        tipoText = findViewById(R.id.tipoText);
        estadoText = findViewById(R.id.estadoText);
        fechaText = findViewById(R.id.fechaText);
        descripcionText = findViewById(R.id.descripcionText);
        ubicacionText = findViewById(R.id.ubicacionText);
        chatBtn = findViewById(R.id.chatBtn);

        reporteId = getIntent().getStringExtra("reporteId");

        if (reporteId == null) {
            Toast.makeText(this, "No se proporcionó el ID del reporte", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        cargarDatosDelReporte();

        chatBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChatIndividualActivity.class);
            intent.putExtra("reporteId", reporteId);
            startActivity(intent);
        });
    }

    private void cargarDatosDelReporte() {
        FirebaseFirestore.getInstance().collection("reportes").document(reporteId)
                .get()
                .addOnSuccessListener(snapshot -> {
                    if (snapshot.exists()) {
                        Reporte r = snapshot.toObject(Reporte.class);
                        if (r != null) {
                            tipoText.setText("Tipo: " + r.getTipo());
                            estadoText.setText("Estado: " + r.getEstado());
                            descripcionText.setText("Descripción: " + r.getDescripcion());
                            fechaText.setText("Fecha: " + new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(r.getFecha()));
                            ubicacionText.setText("Ubicación: Lat " + r.getLatitud() + ", Lon " + r.getLongitud());
                            Glide.with(this).load(r.getImagenUrl()).into(imagenReporte);
                        }
                    } else {
                        Toast.makeText(this, "Reporte no encontrado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al cargar datos del reporte", Toast.LENGTH_SHORT).show();
                });
    }
}