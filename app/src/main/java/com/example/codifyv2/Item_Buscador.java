package com.example.codifyv2;

public class Item_Buscador {
    String idItem;
    String nombreItem;
    String tipoItem;
    String urlImgaenItem;

    public Item_Buscador(String idItem, String nombreItem, String tipoItem, String urlImgaenItem) {
        this.idItem = idItem;
        this.nombreItem = nombreItem;
        this.tipoItem = tipoItem;
        this.urlImgaenItem = urlImgaenItem;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public String getUrlImgaenItem() {
        return urlImgaenItem;
    }

    public void setUrlImgaenItem(String urlImgaenItem) {
        this.urlImgaenItem = urlImgaenItem;
    }
}
