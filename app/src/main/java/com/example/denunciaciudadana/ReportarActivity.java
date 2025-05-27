package com.example.denunciaciudadana;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReportarActivity extends AppCompatActivity {

    private EditText etDescripcion, etUbicacion;
    private Button btnSeleccionarImagen, btnEnviarReporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar);

        // Initialize the EditText and Button views by finding them by their IDs in the layout.
        etDescripcion = findViewById(R.id.etDescripcion);
        etUbicacion = findViewById(R.id.etUbicacion);
        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        btnEnviarReporte = findViewById(R.id.btnEnviarReporte);

        // Set an OnClickListener for the image selection button.
        btnSeleccionarImagen.setOnClickListener(v -> {
            // Logic to open camera or gallery will go here in the future.
            // For now, it displays a Toast message.
            Toast.makeText(this, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show();
        });

        // Set an OnClickListener for the report submission button.
        btnEnviarReporte.setOnClickListener(v -> {
            // Get the text from the description EditText.
            String descripcion = etDescripcion.getText().toString();

            // Check if the description is empty.
            if (descripcion.isEmpty()) {
                // If empty, show a Toast message asking the user to describe the problem.
                Toast.makeText(this, "Por favor describe el problema.", Toast.LENGTH_SHORT).show();
                return; // Stop further execution of this listener.
            }
            // If description is not empty, show a success Toast message.
            Toast.makeText(this, "Reporte enviado correctamente.", Toast.LENGTH_SHORT).show();

            // In a real application, you would add logic here to:
            // 1. Get the simulated location (or real GPS location).
            // 2. Handle the selected image (if any).
            // 3. Send the report data to a backend server or save it locally.
            // 4. Navigate back to a previous activity or to a confirmation screen.
        });

        // Call the method to get (simulated) location when the activity is created.
        obtenerUbicacion();
    }

    /**
     * Simulates obtaining the user's location.
     * In a real application, this method would use Android's LocationManager
     * or Google Play Services Location API to get the actual GPS coordinates.
     */
    private void obtenerUbicacion() {
        // For now, we simulate obtaining the location by setting a predefined text.
        etUbicacion.setText("Lat: 10.12345, Lon: -84.56789"); // In the future, use real GPS.
    }
}
