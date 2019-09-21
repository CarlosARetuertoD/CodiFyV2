package com.example.codifyv2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlaylistHolder extends RecyclerView.ViewHolder {
    ImageView img_album;
    TextView txt_nombrecancion, txt_artistacancion;
    public PlaylistHolder(@NonNull View itemView) {
        super(itemView);
        img_album = itemView.findViewById(R.id.img_albumcancion);
        txt_nombrecancion = itemView.findViewById(R.id.txt_nombrecancion);
        txt_artistacancion = itemView.findViewById(R.id.txt_artistacancion);
    }
}
