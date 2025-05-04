package com.example.denunciaciudadana;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private EditText searchEditText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Vinculación de elementos en la vista
        searchEditText = findViewById(R.id.search_edit_text);
        ImageView searchButton = findViewById(R.id.search_button);

        // Acciones al presionar el botón de búsqueda
        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString();
            if (!query.isEmpty()) {
                // Acción de búsqueda
                performSearch(query);
            } else {
                Toast.makeText(HomeActivity.this, "Por favor ingrese un término de búsqueda.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performSearch(String query) {
        // Aquí puedes agregar la lógica para filtrar y mostrar los reportes
        Toast.makeText(this, "Buscando: " + query, Toast.LENGTH_SHORT).show();
    }
}
