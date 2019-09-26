package com.example.codifyv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BuscadorAdapter extends RecyclerView.Adapter<BuscadorHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    List<Item_Buscador> data;
    Context c;
    private View.OnClickListener listener;

    public BuscadorAdapter(Context c, List<Item_Buscador> data) {
        this.c = c;
        inflater = LayoutInflater.from(c);
        this.data = data;
    }
    @NonNull
    @Override
    public BuscadorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_busqueda, parent, false);
        v.setOnClickListener(this);
        return new BuscadorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BuscadorHolder holder, int position) {
        holder.txt_nombre_item.setText(data.get(position).getNombreItem());
        holder.txt_tipo_item.setText(data.get(position).getTipoItem());
        Glide.with(c).load(data.get(position).getUrlImgaenItem()).into(holder.img_imagen_item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener =listener;
    }
    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }

    }
}
