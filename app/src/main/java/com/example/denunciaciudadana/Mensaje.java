package com.example.denunciaciudadana;
public class Mensaje {
    private String texto, usuario, fotoUrl;
    private long timestamp;

    public Mensaje() {}

    public Mensaje(String texto, String usuario, String fotoUrl, long timestamp) {
        this.texto = texto;
        this.usuario = usuario;
        this.fotoUrl = fotoUrl;
        this.timestamp = timestamp;
    }

    public String getTexto() { return texto; }
    public String getUsuario() { return usuario; }
    public String getFotoUrl() { return fotoUrl; }
    public long getTimestamp() { return timestamp; }
}