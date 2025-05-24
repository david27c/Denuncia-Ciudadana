package com.example.denunciaciudadana;

package com.tuempresa.denunciaciudadana;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.*;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class ReportProblemActivity extends AppCompatActivity {

    private EditText descriptionEditText;
    private Spinner typeSpinner;
    private ImageView imageView;
    private Button takePhotoButton, selectPhotoButton, sendReportButton;
    private Uri imageUri;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private double latitude = 0, longitude = 0;

    private FirebaseFirestore db;
    private StorageReference storageRef;

    private final int IMAGE_PICK_CODE = 1000;
    private final int CAMERA_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_problem);

        descriptionEditText = findViewById(R.id.descriptionEditText);
        typeSpinner = findViewById(R.id.typeSpinner);
        imageView = findViewById(R.id.imageView);
        takePhotoButton = findViewById(R.id.takePhotoButton);
        selectPhotoButton = findViewById(R.id.selectPhotoButton);
        sendReportButton = findViewById(R.id.sendReportButton);

        db = FirebaseFirestore.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();

        // Llenar el spinner con tipos de problemas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Bache", "Fuga de agua", "Basura", "Falla eléctrica", "Otro"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        takePhotoButton.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        });

        selectPhotoButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, IMAGE_PICK_CODE);
        });

        sendReportButton.setOnClickListener(v -> enviarReporte());
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return;
        }

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == IMAGE_PICK_CODE || requestCode == CAMERA_REQUEST_CODE)
                && resultCode == RESULT_OK && data != null) {
            if (requestCode == CAMERA_REQUEST_CODE && data.getExtras() != null) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
                imageUri = data.getData(); // a veces puede ser null con cámara
            } else {
                imageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void enviarReporte() {
        String descripcion = descriptionEditText.getText().toString().trim();
        String tipo = typeSpinner.getSelectedItem().toString();

        if (descripcion.isEmpty() || imageUri == null) {
            Toast.makeText(this, "Completa la descripción y agrega una imagen", Toast.LENGTH_SHORT).show();
            return;
        }

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Enviando reporte...");
        dialog.show();

        String nombreArchivo = "reportes/" + UUID.randomUUID().toString();

        storageRef.child(nombreArchivo).putFile(imageUri)
                .addOnSuccessListener(taskSnapshot ->
                        storageRef.child(nombreArchivo).getDownloadUrl().addOnSuccessListener(uri -> {
                            HashMap<String, Object> reporte = new HashMap<>();
                            reporte.put("descripcion", descripcion);
                            reporte.put("tipo", tipo);
                            reporte.put("latitud", latitude);
                            reporte.put("longitud", longitude);
                            reporte.put("imagenUrl", uri.toString());
                            reporte.put("estado", "pendiente");
                            reporte.put("fecha", System.currentTimeMillis());

                            db.collection("reportes").add(reporte)
                                    .addOnSuccessListener(documentReference -> {
                                        dialog.dismiss();
                                        Toast.makeText(this, "Reporte enviado con éxito", Toast.LENGTH_SHORT).show();
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        dialog.dismiss();
                                        Toast.makeText(this, "Error al guardar reporte", Toast.LENGTH_SHORT).show();
                                    });
                        }))
                .addOnFailureListener(e -> {
                    dialog.dismiss();
                    Toast.makeText(this, "Error al subir imagen", Toast.LENGTH_SHORT).show();
                });
    }
}