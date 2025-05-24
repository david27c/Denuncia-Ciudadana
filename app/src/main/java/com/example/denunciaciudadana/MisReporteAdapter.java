package com.example.denunciaciudadana;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.denunciaciudadana.R;
import com.example.denunciaciudadana.models.Reporte;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MisReporteAdapter extends RecyclerView.Adapter<MisReporteAdapter.ViewHolder> {

    private Context context;
    private List<Reporte> lista;
    private List<String> documentIds;

    public MisReporteAdapter(Context context, List<Reporte> lista, List<String> documentIds) {
        this.context = context;
        this.lista = lista;
        this.documentIds = documentIds;
    }

    @NonNull
    @Override
    public MisReporteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reporte_usuario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MisReporteAdapter.ViewHolder holder, int position) {
        Reporte reporte = lista.get(position);
        String docId = documentIds.get(position);

        holder.descripcion.setText(reporte.getDescripcion());
        holder.tipo.setText("Tipo: " + reporte.getTipo());
        holder.estado.setText("Estado: " + reporte.getEstado());
        holder.fecha.setText("Fecha: " + new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(reporte.getFecha()));

        Glide.with(context)
                .load(reporte.getImagenUrl())
                .into(holder.imagen);

        holder.eliminarBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Eliminar Reporte")
                    .setMessage("¿Estás seguro de eliminar este reporte?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        FirebaseFirestore.getInstance()
                                .collection("reportes")
                                .document(docId)
                                .delete()
                                .addOnSuccessListener(unused -> {
                                    Toast.makeText(context, "Reporte eliminado", Toast.LENGTH_SHORT).show();
                                    lista.remove(position);
                                    documentIds.remove(position);
                                    notifyItemRemoved(position);
                                });
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView descripcion, tipo, estado, fecha;
        ImageView imagen;
        Button eliminarBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.descripcionText);
            tipo = itemView.findViewById(R.id.tipoText);
            estado = itemView.findViewById(R.id.estadoText);
            fecha = itemView.findViewById(R.id.fechaText);
            imagen = itemView.findViewById(R.id.imagenReporte);
            eliminarBtn = itemView.findViewById(R.id.eliminarBtn);
        }
    }
}