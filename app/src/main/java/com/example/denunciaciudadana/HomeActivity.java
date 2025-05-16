package com.example.denunciaciudadana;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private EditText searchEditText;
    private TextView locationTextView;
    private TextView reportList;

    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Referencias UI
        searchEditText = findViewById(R.id.search_edit_text);
        ImageView searchButton = findViewById(R.id.search_button);
        locationTextView = findViewById(R.id.ubicacion);
        reportList = findViewById(R.id.report_list);

        // Acción de búsqueda
        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString().trim();
            if (!query.isEmpty()) {
                performSearch(query);
            } else {
                Toast.makeText(this, "Por favor ingrese un término de búsqueda.", Toast.LENGTH_SHORT).show();
            }
        });

        // Mostrar ubicación activada
        locationTextView.setText("Ubicación: Activada");

        // Mostrar algunos reportes simulados
        reportList.setText("• Bache en calle principal\n• Poste caído\n• Contenedor rebalsado");

        // Barra de navegación
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_inicio); // Seleccionar esta pestaña

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.nav_reportar:
                    Toast.makeText(this, "Ir a Reportar", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_chat:
                    Toast.makeText(this, "Ir a Chat", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_mis_reportes:
                    Toast.makeText(this, "Ir a Mis Reportes", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_notificaciones:
                    Toast.makeText(this, "Ir a Notificaciones", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_perfil:
                    Toast.makeText(this, "Ir a Perfil", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_configuracion:
                    Toast.makeText(this, "Ir a Configuración", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        });
    }

    private void performSearch(String query) {
        // Lógica de búsqueda simulada
        Toast.makeText(this, "Buscando: " + query, Toast.LENGTH_SHORT).show();
    }
}