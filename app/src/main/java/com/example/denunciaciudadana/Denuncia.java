package com.example.denunciaciudadana;

import com.google.firebase.Timestamp;

public class Denuncia {
    private String titulo;
    private String descripcion;
    private String usuarioId;
    private Timestamp fecha;

    public Denuncia() {} // Constructor vacío necesario para Firestore

    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getUsuarioId() { return usuarioId; }
    public Timestamp getFecha() { return fecha; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }
}