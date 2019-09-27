package com.example.codifyv2;

public class ItemAlbumAlbum {

    String idAlbum;
    String nombredAlbum;
    String nombreArtista;
    String añoAlbum;

    public ItemAlbumAlbum(String idAlbum, String nombredAlbum, String nombreArtista, String añoAlbum) {
        this.idAlbum = idAlbum;
        this.nombredAlbum = nombredAlbum;
        this.nombreArtista = nombreArtista;
        this.añoAlbum = añoAlbum;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombredAlbum() {
        return nombredAlbum;
    }

    public void setNombredAlbum(String nombredAlbum) {
        this.nombredAlbum = nombredAlbum;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getAñoAlbum() {
        return añoAlbum;
    }

    public void setAñoAlbum(String añoAlbum) {
        this.añoAlbum = añoAlbum;
    }
}
