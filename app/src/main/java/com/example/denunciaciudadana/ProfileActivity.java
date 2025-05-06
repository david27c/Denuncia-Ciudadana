package com.example.denunciaciudadana;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView textName, textEmail;
    private ImageView imageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textName = findViewById(R.id.text_name);
        textEmail = findViewById(R.id.text_email);
        imageProfile = findViewById(R.id.image_profile);

        // Aquí podrías obtener los datos reales del usuario desde Firebase o SQLite
        textName.setText("David Soto");
        textEmail.setText("david@ejemplo.com");
    }
}