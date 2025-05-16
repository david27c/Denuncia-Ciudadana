package com.example.denunciaciudadana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout chatListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatListLayout = findViewById(R.id.chatListLayout);

        // Aquí agregaríamos los chats de manera manual
        addChat("Chat 1", "Último mensaje del chat 1");
        addChat("Chat 2", "Último mensaje del chat 2");
        addChat("Chat 3", "Último mensaje del chat 3");
    }

    // Método para agregar chats al LinearLayout
    private void addChat(final String chatName, String lastMessage) {
        TextView chatView = new TextView(this);
        chatView.setText(chatName + "\n" + lastMessage);
        chatView.setPadding(16, 16, 16, 16);
        chatView.setTextSize(18);
        chatView.setBackgroundResource(android.R.color.darker_gray);
        chatView.setClickable(true);
        chatView.setFocusable(true);

        // Establecemos un OnClickListener para abrir la actividad de detalles del chat
        chatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir ChatDetalleActivity con el nombre del chat
                Intent intent = new Intent(ChatActivity.this, ChatDetalleActivity.class);
                intent.putExtra("chatName", chatName);  // Enviar el nombre del chat
                startActivity(intent);
            }
        });

        // Agregar el TextView al LinearLayout
        chatListLayout.addView(chatView);
    }
}