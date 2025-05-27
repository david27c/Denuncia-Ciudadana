package com.example.denunciaciudadana;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DenunciasAdapter extends RecyclerView.Adapter<DenunciasAdapter.ViewHolder> {

    private final List<Denuncia> listaDenuncias;

    public DenunciasAdapter(List<Denuncia> denuncias) {
        this.listaDenuncias = denuncias;
    }

    @NonNull
    @Override
    public DenunciasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_denuncia, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DenunciasAdapter.ViewHolder holder, int position) {
        Denuncia denuncia = listaDenuncias.get(position);
        holder.tvTitulo.setText(denuncia.getTitulo());
        holder.tvDescripcion.setText(denuncia.getDescripcion());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String fechaFormateada = sdf.format(denuncia.getFecha().toDate());
        holder.tvFecha.setText(fechaFormateada);
    }

    @Override
    public int getItemCount() {
        return listaDenuncias.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvDescripcion, tvFecha;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvFecha = itemView.findViewById(R.id.tvFecha);
        }
    }
}