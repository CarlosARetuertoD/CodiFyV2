package com.example.codifyv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Buscador extends MyAppCompatActivity {
    String url;
    String tokken;

    EditText edit_busqueda;
    ImageButton btn_back;
    ImageButton btn_clear;

    RecyclerView rv_busqueda;
    List<Item_Buscador> busqueda_data;
    BuscadorAdapter adapter;
    JsonSpotify jsonSpotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);

        edit_busqueda = findViewById(R.id.edit_busqueda);
        btn_back = findViewById(R.id.btn_back_buscador);
        btn_clear = findViewById(R.id.btn_limpiar_busqueda);
        rv_busqueda = findViewById(R.id.rv_busqueda);
        busqueda_data = new ArrayList<>();

        busqueda_data.add(new Item_Buscador("asdasd",
                "Love me Harder",
                "Track",
                "https://m.media-amazon.com/images/M/MV5BMmRhNDIyMTgtMjEwMS00ODJiLWExNDEtMTI2MDMyZjEzZmM1XkEyXkFqcGdeQXVyNDQ5MDYzMTk@._V1_.jpg"));
        /*Intent intent = getIntent();
        tokken = getString(R.string.tokken);
        url = "https://api.spotify.com/v1/playlists/" + intent.getStringExtra("id_playlist");

        jsonSpotify = new JsonSpotify(PlayList.this,url,tokken);
        jsonSpotify.ExtractResponse();*/
        ConstruirRecycler();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("response","Reproducción exitosa");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
    void ConstruirRecycler(){
        adapter = new BuscadorAdapter(this, busqueda_data);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Buscador.this, Biblioteca.class);
                intent.putExtra("id_item",busqueda_data.get(rv_busqueda.getChildAdapterPosition(view)).getIdItem());
                startActivityForResult(intent, 1);
            }
        });
        rv_busqueda.setAdapter(adapter);
        rv_busqueda.setLayoutManager(new LinearLayoutManager(this));
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
        /*try {
            JSONObject json_data = new JSONObject(response);
            JSONObject followers = json_data.getJSONObject("followers");
            JSONObject owner = json_data.getJSONObject("owner");
            JSONArray images = json_data.getJSONArray("images");
            JSONObject url = images.getJSONObject(0);

            JSONObject tracks = json_data.getJSONObject("tracks");
            JSONArray items = tracks.getJSONArray("items");
            for(int i = 0; i<items.length(); i++){
                JSONObject ObjectPlaylist = items.getJSONObject(i);
                JSONObject track = ObjectPlaylist.getJSONObject("track");
                JSONObject artista = track.getJSONArray("artists").getJSONObject(0);
                JSONObject imagessong = track.getJSONObject("album").getJSONArray("images").getJSONObject(2);
                playlist_data.add(new Item_Playlist(track.getString("id"),
                        track.getString("name"),
                        artista.getString("name"),
                        imagessong.getString("url")));
                adapter.notifyDataSetChanged();
            }
            Glide.with(this).load(url.getString("url")).into(img_playlist);
            txt_nombreplaylist.setText(json_data.getString("name"));
            txt_propietario.setText("De " + owner.getString("display_name"));
            txt_followers.setText(followers.getString("total") + " seguidores");

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
}
