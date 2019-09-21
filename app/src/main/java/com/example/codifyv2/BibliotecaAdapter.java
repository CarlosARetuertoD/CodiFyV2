package com.example.codifyv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BibliotecaAdapter extends RecyclerView.Adapter<BibliotecaHolder> implements View.OnClickListener {
    LayoutInflater inflater;
    List<Item_Biblioteca> data;
    Context c;
    private View.OnClickListener listener;
    public BibliotecaAdapter(Context c, List<Item_Biblioteca> data) {
        this.c = c;
        inflater = LayoutInflater.from(c);
        this.data = data;
    }
    @NonNull
    @Override
    public BibliotecaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_biblioteca, parent, false);
        v.setOnClickListener(this);
        return new BibliotecaHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BibliotecaHolder holder, int position) {
        holder.txt_nombre_item_biblioteca.setText(data.get(position).getNombre());
        Glide.with(c).load(data.get(position).getUrl()).into(holder.img_item_biblioteca);
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
