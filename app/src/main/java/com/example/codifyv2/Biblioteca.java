package com.example.codifyv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.BatchingListUpdateCallback;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    String urlPlaylist, urlMeGusta, urlAlbumes;
    String tokken;

    ImageButton btn_busqueda;

    RecyclerView rv_biblioteca, rv_megusta, rv_albumes;
    List<Item_Biblioteca> playlist_data,megusta_data, albumes_data;
    BibliotecaAdapter playlist_adapter, megusta_adapter, albumes_adapter;
    JsonSpotifyBiblioteca jsonSpotifyPlaylist, jsonSpotifyMeGusta, jsonSpotifyAlbumes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblioteca);
        btn_busqueda = findViewById(R.id.btn_busqueda);

        rv_biblioteca = findViewById(R.id.rv_biblioteca);
        rv_megusta = findViewById(R.id.rv_tus_me_gusta);
        rv_albumes = findViewById(R.id.rv_albums);

        playlist_data = new ArrayList<>();
        megusta_data = new ArrayList<>();
        albumes_data = new ArrayList<>();

        btn_busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Biblioteca.this, Buscador.class);
                startActivityForResult(intent, 1);
            }
        });

        tokken = getString(R.string.tokken);
        urlPlaylist = "https://api.spotify.com/v1/users/"+user+"/playlists";
        urlMeGusta = "https://api.spotify.com/v1/me/tracks";
        urlAlbumes = "https://api.spotify.com/v1/me/albums";
        ConstruirRecyclerPlaylist();
        ConstruirRecyclerMeGusta();
        ConstruirRecyclerAlbumes();
    }
    void ConstruirRecyclerMeGusta(){
        jsonSpotifyMeGusta = new JsonSpotifyBiblioteca(Biblioteca.this,urlMeGusta,tokken,"tus_me_gusta");
        jsonSpotifyMeGusta.ExtractResponse();
        megusta_adapter = new BibliotecaAdapter(this, megusta_data);
        megusta_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Biblioteca.this, Reproductor.class);
                intent.putExtra("id",megusta_data.get(rv_megusta.getChildAdapterPosition(view)).getId());
                startActivityForResult(intent, 1);
            }
        });
        rv_megusta.setAdapter(megusta_adapter);
        rv_megusta.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
    void ConstruirRecyclerPlaylist(){
        jsonSpotifyPlaylist = new JsonSpotifyBiblioteca(Biblioteca.this,urlPlaylist,tokken,"playlist");
        jsonSpotifyPlaylist.ExtractResponse();
        playlist_adapter = new BibliotecaAdapter(this, playlist_data);
        playlist_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Biblioteca.this, PlayList.class);
                intent.putExtra("id",playlist_data.get(rv_biblioteca.getChildAdapterPosition(view)).getId());
                startActivityForResult(intent, 1);
            }
        });
        rv_biblioteca.setAdapter(playlist_adapter);
        rv_biblioteca.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
    void ConstruirRecyclerAlbumes(){
        jsonSpotifyAlbumes = new JsonSpotifyBiblioteca(Biblioteca.this,urlAlbumes,tokken,"albumes");
        jsonSpotifyAlbumes.ExtractResponse();
        albumes_adapter = new BibliotecaAdapter(this, albumes_data);
        albumes_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Biblioteca.this, PlayList.class);
                intent.putExtra("id",albumes_data.get(rv_albumes.getChildAdapterPosition(view)).getId());
                startActivityForResult(intent, 1);
            }
        });
        rv_albumes.setAdapter(albumes_adapter);
        rv_albumes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
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
    public void Response(String response, String lista){
        if(lista.equals("playlist")){
            try {
                JSONObject json_data = new JSONObject(response);
                JSONArray items = json_data.getJSONArray("items");
                for(int i = 0; i<items.length(); i++){
                    JSONObject playlist = items.getJSONObject(i);
                    JSONObject images = playlist.getJSONArray("images").getJSONObject(0);
                    playlist_data.add(new Item_Biblioteca(
                            playlist.getString("id"),
                            playlist.getString("name"),
                            images.getString("url")));
                    playlist_adapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(lista.equals("tus_me_gusta")){
            try {
                JSONObject json_data = new JSONObject(response);
                JSONArray items = json_data.getJSONArray("items");
                for(int i = 0; i<items.length(); i++){
                    JSONObject track = items.getJSONObject(i).getJSONObject("track");
                    JSONObject images = track.getJSONObject("album").getJSONArray("images").getJSONObject(1);
                    megusta_data.add(new Item_Biblioteca(track.getString("id"),
                            track.getString("name"),
                            images.getString("url")));
                    megusta_adapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(lista.equals("albumes")){
            try {
                JSONObject json_data = new JSONObject(response);
                JSONArray items = json_data.getJSONArray("items");
                for(int i = 0; i<items.length(); i++){
                    JSONObject album = items.getJSONObject(i).getJSONObject("album");
                    JSONObject images = album.getJSONArray("images").getJSONObject(1);
                    albumes_data.add(new Item_Biblioteca(
                            album.getString("id"),
                            album.getString("name"),
                            images.getString("url")));
                    albumes_adapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
