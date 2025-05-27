package com.example.denunciaciudadana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class VerDenunciasFragment extends Fragment {

    private ListView listView;
    private ArrayList<String> denunciasList;
    private ArrayAdapter<String> adapter;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_denuncias, container, false);

        listView = view.findViewById(R.id.listViewDenuncias);
        denunciasList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, denunciasList);
        listView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();

        cargarDenuncias();

        return view;
    }

    private void cargarDenuncias() {
        db.collection("denuncias")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    denunciasList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        String titulo = doc.getString("titulo");
                        String descripcion = doc.getString("descripcion");
                        denunciasList.add("📌 " + titulo + "\n" + descripcion);
                    }
                    adapter.notifyDataSetChanged();
                });
    }
}
