package com.example.codifyv2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BibliotecaHolder extends RecyclerView.ViewHolder {
    ImageView img_item_biblioteca;
    TextView txt_nombre_item_biblioteca;
    public BibliotecaHolder(@NonNull View itemView) {
        super(itemView);
        img_item_biblioteca = itemView.findViewById(R.id.img_item_biblioteca);
        txt_nombre_item_biblioteca = itemView.findViewById(R.id.txt_nombre_item_biblioteca);
    }
}
