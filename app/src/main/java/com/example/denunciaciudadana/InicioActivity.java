package com.example.denunciaciudadana;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    Button btnInicio, btnReportar, btnChat, btnMisReportes, btnPerfil, btnConfiguracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnInicio = findViewById(R.id.btnInicio);
        btnReportar = findViewById(R.id.btnReportar);
        btnChat = findViewById(R.id.btnChat);
        btnMisReportes = findViewById(R.id.btnMisReportes);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnConfiguracion = findViewById(R.id.btnConfiguracion);

        btnInicio.setOnClickListener(v ->
                Toast.makeText(this, "Ya estás en Inicio", Toast.LENGTH_SHORT).show()
        );

        btnReportar.setOnClickListener(v ->
                startActivity(new Intent(this, ReportarActivity.class))
        );

        btnChat.setOnClickListener(v ->
                startActivity(new Intent(this, com.example.denunciaciudadana.MisReportesActivity.class))
        );

        btnMisReportes.setOnClickListener(v ->
                startActivity(new Intent(this, MisReportesActivity.class))
        );

        btnPerfil.setOnClickListener(v ->
                startActivity(new Intent(this, InicioActivity.class))
        );

        btnConfiguracion.setOnClickListener(v ->
                startActivity(new Intent(this, MisReportesActivity.class))
        );
    }
}

