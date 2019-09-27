package com.example.codifyv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador_Album_Album extends RecyclerView.Adapter<Adaptador_Album_Album.ViewHolderDatos> {

    Context c2;
    List<ItemAlbumAlbum> listdatos2;

    public Adaptador_Album_Album(Context c2, List<ItemAlbumAlbum> listdatos) {
        this.c2 = c2;
        this.listdatos2 = listdatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detalles_album_album,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarNombreAlbum(listdatos2.get(position).getNombredAlbum());
        holder.asignarNombreArtista(listdatos2.get(position).getNombreArtista());
        holder.asignarAñoAlbum(listdatos2.get(position).getAñoAlbum());
    }

    @Override
    public int getItemCount() {
        return listdatos2.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nombreAlbum;
        TextView nombreArtista;
        TextView añoAlbum;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombreAlbum = itemView.findViewById(R.id.txt_nombreAlbum2);
            nombreArtista = itemView.findViewById(R.id.txt_nombreArtistaAlbum);
            añoAlbum = itemView.findViewById(R.id.txt_año);
        }

        public void asignarNombreAlbum (String nombreAlbum2){
            nombreAlbum.setText(nombreAlbum2);
        }

        public void asignarNombreArtista(String nombreArtista2){
            nombreArtista.setText(nombreArtista2);
        }

        public void asignarAñoAlbum(String añoAlbum2){
            añoAlbum.setText(añoAlbum2);
        }
    }

}
