package com.example.denunciaciudadana;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationsActivity extends AppCompatActivity {

    private RecyclerView notificationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        notificationsList = findViewById(R.id.notifications_list);

        // Aquí puedes configurar el adapter si tienes una lista de notificaciones
    }
}