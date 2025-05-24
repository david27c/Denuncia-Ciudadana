package com.example.denunciaciudadana;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;
import com.example.denunciaciudadana.adapters.MensajeAdapter;
import com.example.denunciaciudadana.models.Mensaje;

import java.util.*;

public class ChatComunitarioActivity extends AppCompatActivity {

    private Spinner temaSpinner;
    private RecyclerView recyclerView;
    private EditText mensajeEditText;
    private Button enviarBtn;

    private ArrayList<com.example.denunciaciudadana.Mensaje> mensajes = new ArrayList<>();
    private com.example.denunciaciudadana.MensajeAdapter adapter;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private ListenerRegistration chatListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_comunitario);

        temaSpinner = findViewById(R.id.temaSpinner);
        recyclerView = findViewById(R.id.chatRecyclerView);
        mensajeEditText = findViewById(R.id.mensajeEditText);
        enviarBtn = findViewById(R.id.enviarBtn);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        adapter = new MensajeAdapter(this, mensajes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        String[] temas = {"General", "Agua", "Electricidad", "Baches", "Basura"};
        temaSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, temas));

        temaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (chatListener != null) chatListener.remove();
                escucharMensajes(temas[pos]);
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        enviarBtn.setOnClickListener(v -> {
            String texto = mensajeEditText.getText().toString().trim();
            if (!texto.isEmpty()) {
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                String tema = temaSpinner.getSelectedItem().toString();

                db.collection("usuarios").document(uid).get().addOnSuccessListener(snapshot -> {
                    String nombre = snapshot.getString("usuario");
                    String fotoUrl = snapshot.getString("fotoPerfil");

                    Mensaje msg = new Mensaje(texto, nombre, fotoUrl, System.currentTimeMillis());

                    db.collection("chats").document(tema).collection("mensajes").add(msg);
                    mensajeEditText.setText("");
                });
            }
        });
    }

    private void escucharMensajes(String tema) {
        chatListener = db.collection("chats")
                .document(tema)
                .collection("mensajes")
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener((snapshots, e) -> {
                    if (snapshots != null) {
                        mensajes.clear();
                        for (DocumentSnapshot doc : snapshots.getDocuments()) {
                            mensajes.add(doc.toObject(Mensaje.class));
                        }
                        adapter.notifyDataSetChanged();
                        recyclerView.scrollToPosition(mensajes.size() - 1);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (chatListener != null) chatListener.remove();
    }
}