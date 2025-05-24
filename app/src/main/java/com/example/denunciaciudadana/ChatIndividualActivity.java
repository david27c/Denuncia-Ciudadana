package com.example.denunciaciudadana;

package com.tuempresa.denunciaciudadana;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;
import com.tuempresa.denunciaciudadana.adapters.MensajeAdapter;
import com.tuempresa.denunciaciudadana.models.Mensaje;

import java.util.*;

public class ChatIndividualActivity extends AppCompatActivity {

    private EditText mensajeEditText;
    private Button enviarBtn;
    private RecyclerView recyclerView;

    private List<Mensaje> mensajes = new ArrayList<>();
    private MensajeAdapter adapter;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private ListenerRegistration chatListener;

    private String reporteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_individual);

        mensajeEditText = findViewById(R.id.mensajeEditText);
        enviarBtn = findViewById(R.id.enviarBtn);
        recyclerView = findViewById(R.id.chatRecyclerView);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        adapter = new MensajeAdapter(this, mensajes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Recibir ID del reporte desde el intent
        reporteId = getIntent().getStringExtra("reporteId");

        escucharMensajes();

        enviarBtn.setOnClickListener(v -> {
            String texto = mensajeEditText.getText().toString().trim();
            if (!texto.isEmpty()) {
                String uid = mAuth.getCurrentUser().getUid();
                db.collection("usuarios").document(uid).get().addOnSuccessListener(snapshot -> {
                    String nombre = snapshot.getString("usuario");
                    String fotoUrl = snapshot.getString("fotoPerfil");

                    Mensaje mensaje = new Mensaje(texto, nombre, fotoUrl, System.currentTimeMillis());

                    db.collection("reportes")
                            .document(reporteId)
                            .collection("chat")
                            .add(mensaje);

                    mensajeEditText.setText("");
                });
            }
        });
    }

    private void escucharMensajes() {
        chatListener = db.collection("reportes")
                .document(reporteId)
                .collection("chat")
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