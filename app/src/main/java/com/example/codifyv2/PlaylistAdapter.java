package com.example.codifyv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistHolder> implements View.OnClickListener {
    LayoutInflater inflater;
    List<Item_Playlist> data;
    Context c;
    private View.OnClickListener listener;
    public PlaylistAdapter(Context c, List<Item_Playlist> data) {
        this.c = c;
        inflater = LayoutInflater.from(c);
        this.data = data;
    }
    @NonNull
    @Override
    public PlaylistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_playlist, parent, false);
        v.setOnClickListener(this);
        return new PlaylistHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistHolder holder, int position) {
        holder.txt_nombrecancion.setText(data.get(position).getNombrecancion());
        holder.txt_artistacancion.setText(data.get(position).getArtistacancion());
        Glide.with(c).load(data.get(position).getUrl_album()).into(holder.img_album);
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
class PlaylistHolder extends RecyclerView.ViewHolder {
    ImageView img_album;
    TextView txt_nombrecancion, txt_artistacancion;
    public PlaylistHolder(@NonNull View itemView) {
        super(itemView);
        img_album = itemView.findViewById(R.id.img_albumcancion);
        txt_nombrecancion = itemView.findViewById(R.id.txt_nombrecancion);
        txt_artistacancion = itemView.findViewById(R.id.txt_artistacancion);
    }
}