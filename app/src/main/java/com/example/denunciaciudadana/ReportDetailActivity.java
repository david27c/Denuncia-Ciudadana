package com.example.denunciaciudadana;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReportDetailActivity extends AppCompatActivity {

    private TextView reportTitle, reportDescription, reportLocation, reportDate, reportStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);

        // Vincular los elementos en la vista
        reportTitle = findViewById(R.id.report_title);
        reportDescription = findViewById(R.id.report_description);
        reportLocation = findViewById(R.id.report_location);
        reportDate = findViewById(R.id.report_date);
        reportStatus = findViewById(R.id.report_status);

        // Cargar los datos del reporte (en este caso solo simulados)
        loadReportDetails();
    }

    private void loadReportDetails() {
        // Aquí iría la lógica para obtener y mostrar los detalles del reporte
        reportTitle.setText("Reporte de Bache");
        reportDescription.setText("Descripción completa del problema con detalles sobre el bache.");
        reportLocation.setText("Ubicación: Calle 5, Sector 3");
        reportDate.setText("Fecha: 10/10/2025");
        reportStatus.setText("Estado: Pendiente");
    }
}