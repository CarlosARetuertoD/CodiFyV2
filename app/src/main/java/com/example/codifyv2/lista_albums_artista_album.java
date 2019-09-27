package com.example.codifyv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class lista_albums_artista_album extends MyAppCompatActivity {

    String url;

    String tokken;

    TextView artista;

    RecyclerView rcy_listaAlbums;

    List<ItemAlbumAlbum> listdatos2;

    JsonSpotify json;

    Adaptador_Album_Album adaptador_album_album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_albums_artista_album);

        artista = findViewById(R.id.txt_nombreArtistaAlbum);
        rcy_listaAlbums = findViewById(R.id.rcy_listaAlbums);

        rcy_listaAlbums.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        listdatos2 = new ArrayList<>();
        tokken = getString(R.string.tokken);
        url = "https://api.spotify.com/v1/artists/2mUI4K6csTQd3jieswcmiI/albums";

        json = new JsonSpotify(lista_albums_artista_album.this,url,tokken);
        json.ExtractResponse();
        adaptador_album_album = new Adaptador_Album_Album(this, listdatos2);
        rcy_listaAlbums.setAdapter(adaptador_album_album);

    }

    public void Response(String response) {
        try {
            JSONObject json_data = new JSONObject(response);
            JSONArray items = json_data.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject track = items.getJSONObject(i);

                listdatos2.add(new ItemAlbumAlbum(

                        track.getString("id"),
                        track.getString("name"),
                        track.getString("name"),
                        track.getString("release_date")));

                adaptador_album_album.notifyDataSetChanged();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
