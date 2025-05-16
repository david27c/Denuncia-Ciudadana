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

        reportTitle = findViewById(R.id.report_title);
        reportDescription = findViewById(R.id.report_description);
        reportLocation = findViewById(R.id.report_location);
        reportDate = findViewById(R.id.report_date);
        reportStatus = findViewById(R.id.report_status);

        loadReportDetails();
    }

    private void loadReportDetails() {
        Report report = (Report) getIntent().getSerializableExtra("report");

        if (report != null) {
            reportTitle.setText(report.getTitle());
            reportDescription.setText(report.getDescription());
            reportLocation.setText("Ubicación: " + report.getLocation());
            reportDate.setText("Fecha: " + report.getDate());
            reportStatus.setText("Estado: " + report.getStatus());
        }
    }
}