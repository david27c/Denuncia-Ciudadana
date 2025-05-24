package com.example.denunciaciudadana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.denunciaciudadana.R;
import com.example.denunciaciudadana.models.Reporte;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ReporteViewHolder> {

    private Context context;
    private List<Reporte> lista;

    public ReporteAdapter(Context context, List<Reporte> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ReporteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reporte, parent, false);
        return new ReporteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReporteViewHolder holder, int position) {
        Reporte reporte = lista.get(position);
        holder.descripcion.setText(reporte.getDescripcion());
        holder.tipo.setText("Tipo: " + reporte.getTipo());
        holder.estado.setText("Estado: " + reporte.getEstado());
        holder.fecha.setText("Fecha: " + new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(reporte.getFecha()));

        Glide.with(context)
                .load(reporte.getImagenUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ReporteViewHolder extends RecyclerView.ViewHolder {
        TextView descripcion, tipo, estado, fecha;
        ImageView imagen;

        public ReporteViewHolder(@NonNull View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.descripcionText);
            tipo = itemView.findViewById(R.id.tipoText);
            estado = itemView.findViewById(R.id.estadoText);
            fecha = itemView.findViewById(R.id.fechaText);
            imagen = itemView.findViewById(R.id.imagenReporte);
        }
    }
}