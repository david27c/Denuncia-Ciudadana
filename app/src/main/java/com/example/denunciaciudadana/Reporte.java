package com.example.denunciaciudadana;

public class Reporte {
    private String descripcion, tipo, imagenUrl, estado;
    private double latitud, longitud;
    private long fecha;

    public Reporte() {} // Necesario para Firebase

    public Reporte(String descripcion, String tipo, String imagenUrl, String estado, double latitud, double longitud, long fecha) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.imagenUrl = imagenUrl;
        this.estado = estado;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
    }

    // Getters y setters
    public String getDescripcion() { return descripcion; }
    public String getTipo() { return tipo; }
    public String getImagenUrl() { return imagenUrl; }
    public String getEstado() { return estado; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }
    public long getFecha() { return fecha; }
}