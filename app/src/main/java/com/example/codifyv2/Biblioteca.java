package com.example.codifyv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca extends MyAppCompatActivity{
    String user = "12163601739";
    String user2 = "dm15a6qs15hjah6dcmet6ytjf";
    String url;
    String tokken;
    ImageView img_item_biblioteca;
    TextView txt_nombre_item_biblioteca;

    RecyclerView rv_biblioteca;
    List<Item_Biblioteca> biblioteca_data;
    BibliotecaAdapter adapter;
    JsonSpotify jsonSpotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblioteca);
        img_item_biblioteca = findViewById(R.id.img_item_biblioteca);
        txt_nombre_item_biblioteca = findViewById(R.id.txt_nombre_item_biblioteca);
        rv_biblioteca = findViewById(R.id.rv_biblioteca);
        biblioteca_data = new ArrayList<>();

        tokken = getString(R.string.tokken);
        url = "https://api.spotify.com/v1/users/"+user+"/playlists";

        jsonSpotify = new JsonSpotify(Biblioteca.this,url,tokken);
        jsonSpotify.ExtractResponse();
        ConstruirRecycler();
    }
    void ConstruirRecycler(){
        adapter = new BibliotecaAdapter(this, biblioteca_data);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Biblioteca.this, PlayList.class);
                intent.putExtra("id_playlist",biblioteca_data.get(rv_biblioteca.getChildAdapterPosition(view)).getId());
                startActivityForResult(intent, 1);
            }
        });
        rv_biblioteca.setAdapter(adapter);
        rv_biblioteca.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                /*Toast.makeText(getApplicationContext(),
                        data.getStringExtra("response"), Toast.LENGTH_SHORT).show();*/
            }
        }
    }

    public void Response(String response){
        try {
            JSONObject json_data = new JSONObject(response);
            JSONArray items = json_data.getJSONArray("items");
            for(int i = 0; i<items.length(); i++){
                JSONObject playlist = items.getJSONObject(i);
                JSONObject images = playlist.getJSONArray("images").getJSONObject(0);
                biblioteca_data.add(new Item_Biblioteca(
                        playlist.getString("id"),
                        playlist.getString("name"),
                        images.getString("url")));
                adapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
