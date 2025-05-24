package com.example.denunciaciudadana;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.denunciaciudadana.R;
import com.example.denunciaciudadana.models.Mensaje;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.ViewHolder> {

    private Context context;
    private List<Mensaje> lista;

    public MensajeAdapter(Context context, List<Mensaje> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mensaje, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mensaje m = lista.get(position);
        holder.texto.setText(m.getTexto());
        holder.usuario.setText(m.getUsuario());
        holder.fecha.setText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(m.getTimestamp()));
        Glide.with(context).load(m.getFotoUrl()).into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView texto, usuario, fecha;
        ImageView foto;

        public ViewHolder(@NonNull View v) {
            super(v);
            texto = v.findViewById(R.id.textoMsg);
            usuario = v.findViewById(R.id.usuarioMsg);
            fecha = v.findViewById(R.id.horaMsg);
            foto = v.findViewById(R.id.fotoMsg);
        }
    }
}