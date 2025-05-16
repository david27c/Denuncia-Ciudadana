package com.example.denunciaciudadana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class ChatDetailActivity extends AppCompatActivity {

    private EditText editTextMessage;
    private Button buttonSend;
    private RecyclerView chatMessagesList;
    private ChatAdapter chatAdapter;
    private DatabaseReference chatRef;
    private String chatWith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);

        editTextMessage = findViewById(R.id.edit_text_message);
        buttonSend = findViewById(R.id.button_send);
        chatMessagesList = findViewById(R.id.chat_messages_list);
        chatMessagesList.setLayoutManager(new LinearLayoutManager(this));

        chatWith = getIntent().getStringExtra("chatWith");
        TextView userNameText = findViewById(R.id.chat_user_name);
        userNameText.setText(chatWith);

        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String chatId = generateChatId(currentUser, chatWith);
        chatRef = FirebaseDatabase.getInstance().getReference("chats").child(chatId);

        chatAdapter = new ChatAdapter();
        chatMessagesList.setAdapter(chatAdapter);

        buttonSend.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString();
            if (!message.isEmpty()) {
                String fullMessage = currentUser + ": " + message;
                chatRef.push().setValue(fullMessage);
                editTextMessage.setText("");
            }
        });

        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                String message = snapshot.getValue(String.class);
                chatAdapter.addMessage(message);
            }

            @Override public void onChildChanged(DataSnapshot s, String p) {}
            @Override public void onChildRemoved(DataSnapshot s) {}
            @Override public void onChildMoved(DataSnapshot s, String p) {}
            @Override public void onCancelled(DatabaseError error) {}
        });
    }

    private String generateChatId(String user1, String user2) {
        return user1.hashCode() < user2.hashCode()
                ? user1 + "_" + user2
                : user2 + "_" + user1;
    }

    // CLASE INTERNA: ChatAdapter
    private class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

        private ArrayList<String> messages = new ArrayList<>();

        @NonNull
        @Override
        public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ChatViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
            holder.textView.setText(messages.get(position));
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

        public void addMessage(String message) {
            messages.add(message);
            notifyItemInserted(messages.size() - 1);
            chatMessagesList.scrollToPosition(messages.size() - 1);
        }

        class ChatViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public ChatViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }
        }
    }
}