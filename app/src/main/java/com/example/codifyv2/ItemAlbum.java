package com.example.codifyv2;

public class ItemAlbum {
    String idAlbum;
    String nombreAlbum;
    String duracionAlbum;

    public ItemAlbum(String idAlbum, String nombreAlbum, String duracionAlbum) {
        this.idAlbum = idAlbum;
        this.nombreAlbum = nombreAlbum;
        this.duracionAlbum = duracionAlbum;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public String getDuracionAlbum() {
        return duracionAlbum;
    }

    public void setDuracionAlbum(String duracionAlbum) {
        this.duracionAlbum = duracionAlbum;
    }
}
