package com.example.codifyv2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BuscadorHolder extends RecyclerView.ViewHolder {
    ImageView img_imagen_item;
    TextView txt_nombre_item;
    TextView txt_tipo_item;
    public BuscadorHolder(@NonNull View itemView) {
        super(itemView);
        txt_nombre_item = itemView.findViewById(R.id.txt_nombre_item);
        txt_tipo_item = itemView.findViewById(R.id.txt_tipo_item);
        img_imagen_item = itemView.findViewById(R.id.img_imagen_item);
    }
}
