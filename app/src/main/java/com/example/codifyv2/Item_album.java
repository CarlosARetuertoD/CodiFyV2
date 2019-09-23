package com.example.codifyv2;

public class Item_album {
    public String nombreCancion;
    public int duracion;
    public String nombreArtista;
    public String nombreAlbum;

    public Item_album(String nombreCancion, int duracion, String nombreArtista, String nombreAlbum) {
        this.nombreCancion = nombreCancion;
        this.duracion = duracion;
        this.nombreArtista = nombreArtista;
        this.nombreAlbum = nombreAlbum;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }
}
