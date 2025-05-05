package com.example.denunciaciudadana;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class UserDatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "usuarios.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    private FirebaseAuth mAuth;

    public UserDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        mAuth = FirebaseAuth.getInstance(); // Firebase auth
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE usuarios (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT NOT NULL, " +
                        "correo TEXT NOT NULL, " +
                        "contrasena TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }

    // Método para guardar en SQLite
    public void guardarUsuarioLocal(String nombre, String correo, String contrasena) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("correo", correo);
        values.put("contrasena", contrasena);

        long result = db.insert("usuarios", null, values);
        if (result == -1) {
            Toast.makeText(context, "Error al guardar localmente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Guardado localmente", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para registrar en Firebase
    public void registrarUsuarioFirebase(String correo, String contrasena) {
        mAuth.createUserWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(context, "Registrado en Firebase", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Error Firebase: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}