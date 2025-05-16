package com.example.denunciaciudadana;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Cargar el fragmento de inicio por defecto
        loadFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.nav_reportar:
                    fragment = new ReportarFragment();
                    break;
                case R.id.nav_chat:
                    fragment = new ChatFragment();
                    break;
                case R.id.nav_mis_reportes:
                    fragment = new MisReportesFragment();
                    break;
                case R.id.nav_perfil:
                    fragment = new PerfilFragment();
                    break;
                case R.id.nav_configuracion:
                    fragment = new ConfiguracionFragment();
                    break;
                case R.id.nav_notificaciones:
                    fragment = new NotificacionesFragment();
                    break;
            }

            if (fragment != null) {
                loadFragment(fragment);
                return true;
            }

            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}