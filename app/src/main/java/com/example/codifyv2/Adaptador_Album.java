package com.example.codifyv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adaptador_Album extends RecyclerView.Adapter<Adaptador_Album.ViewHolderDatos> {
    Context c;
    List<ItemAlbum> Listdatos;

    public Adaptador_Album(Context c, List<ItemAlbum> listdatos) {
        this.c = c;
        Listdatos = listdatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detalles_cancion_album,null,false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarNombreCancion(Listdatos.get(position).getNombreAlbum());
        holder.asignarDuracion(Listdatos.get(position).getDuracionAlbum());
    }

    @Override
    public int getItemCount() {
        return Listdatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView txt_nombreCancion;
        TextView txt_duracionCancion;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txt_nombreCancion = itemView.findViewById(R.id.txt_nombreCancion);
            txt_duracionCancion = itemView.findViewById(R.id.txt_duracionCancion);
        }

        public void asignarNombreCancion(String nombres) {
            txt_nombreCancion.setText(nombres);
        }

        public void asignarDuracion(String duracion) {
            txt_duracionCancion.setText(duracion);
        }
    }
}
