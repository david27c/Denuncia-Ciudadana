package com.example.denunciaciudadana;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileSettingsActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText;
    private Button saveButton;
    private CheckBox showNameCheckBox, showProfilePicCheckBox, showEmailCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        // Vinculación de elementos
        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        ImageView profileImageView = findViewById(R.id.profile_image_view);
        saveButton = findViewById(R.id.save_button);
        showNameCheckBox = findViewById(R.id.show_name_checkbox);
        showProfilePicCheckBox = findViewById(R.id.show_profile_pic_checkbox);
        showEmailCheckBox = findViewById(R.id.show_email_checkbox);

        saveButton.setOnClickListener(v -> {
            // Lógica para guardar los cambios del perfil
            saveProfileSettings();
        });
    }

    private void saveProfileSettings() {
        // Aquí se agregaría la lógica para guardar la configuración del perfil
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();

        boolean isNameVisible = showNameCheckBox.isChecked();
        boolean isProfilePicVisible = showProfilePicCheckBox.isChecked();
        boolean isEmailVisible = showEmailCheckBox.isChecked();

        // Guardar estos datos en base de datos o SharedPreferences según lo necesites
    }
}