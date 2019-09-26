package com.example.codifyv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayList extends MyAppCompatActivity {
    String url;
    String tokken;

    ImageView img_playlist;
    TextView txt_nombreplaylist;
    TextView txt_propietario;
    TextView txt_followers;
    Button btn_back;

    RecyclerView rv_playlist;
    List<Item_Playlist> playlist_data;
    PlaylistAdapter adapter;
    JsonSpotify jsonSpotify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        img_playlist = findViewById(R.id.img_playlist);
        txt_nombreplaylist = findViewById(R.id.txt_nombreplaylist);
        txt_propietario = findViewById(R.id.txt_propietario);
        txt_followers = findViewById(R.id.txt_followers);
        btn_back = findViewById(R.id.btn_back_playlist);
        rv_playlist = findViewById(R.id.rv_playlist);
        playlist_data = new ArrayList<>();

        Intent intent = getIntent();
        tokken = getString(R.string.tokken);
        url = "https://api.spotify.com/v1/playlists/" + intent.getStringExtra("id");

        jsonSpotify = new JsonSpotify(PlayList.this,url,tokken);
        jsonSpotify.ExtractResponse();
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
        adapter = new PlaylistAdapter(this, playlist_data);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayList.this, Reproductor.class);
                intent.putExtra("id",playlist_data.get(rv_playlist.getChildAdapterPosition(view)).getId());
                startActivityForResult(intent, 1);
            }
        });
        rv_playlist.setAdapter(adapter);
        rv_playlist.setLayoutManager(new LinearLayoutManager(this));
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
        }
    }
}
