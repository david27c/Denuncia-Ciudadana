package com.example.denunciaciudadana;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    EditText editTextNombre, editTextUsuario, editTextCorreo, editTextContrasena, editTextConfirmarContrasena;
    Button btnSubirFoto, btnCrearCuenta;
    TextView tvYaTieneCuenta;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        editTextConfirmarContrasena = findViewById(R.id.editTextConfirmarContrasena);
        btnSubirFoto = findViewById(R.id.btnSubirFoto);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        tvYaTieneCuenta = findViewById(R.id.tvYaTieneCuenta);

        btnCrearCuenta.setOnClickListener(v -> {
            String nombre = editTextNombre.getText().toString().trim();
            String usuario = editTextUsuario.getText().toString().trim();
            String correo = editTextCorreo.getText().toString().trim();
            String contrasena = editTextContrasena.getText().toString().trim();
            String confirmar = editTextConfirmarContrasena.getText().toString().trim();

            if (nombre.isEmpty() || usuario.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            } else if (!contrasena.equals(confirmar)) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            } else {
                UserDatabaseManager dbManager = new UserDatabaseManager(this);
                dbManager.guardarUsuarioLocal(nombre, correo, contrasena); // También puedes guardar 'usuario'
                dbManager.registrarUsuarioFirebase(correo, contrasena);
                Toast.makeText(this, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        });

        tvYaTieneCuenta.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        btnSubirFoto.setOnClickListener(v -> {
            Toast.makeText(this, "Función de subir foto aún no implementada", Toast.LENGTH_SHORT).show();
        });
    }
}