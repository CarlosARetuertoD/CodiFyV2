package com.example.codifyv2;

public class Item_Playlist {
    String id;
    String nombrecancion;
    String artistacancion;
    String url_album;

    public Item_Playlist(String id, String nombrecancion, String artistacancion, String url_album) {
        this.id = id;
        this.nombrecancion = nombrecancion;
        this.artistacancion = artistacancion;
        this.url_album = url_album;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrecancion() {
        return nombrecancion;
    }

    public void setNombrecancion(String nombrecancion) {
        this.nombrecancion = nombrecancion;
    }

    public String getArtistacancion() {
        return artistacancion;
    }

    public void setArtistacancion(String artistacancion) {
        this.artistacancion = artistacancion;
    }

    public String getUrl_album() {
        return url_album;
    }

    public void setUrl_album(String url_album) {
        this.url_album = url_album;
    }
}
