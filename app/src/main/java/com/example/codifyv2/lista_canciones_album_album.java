package com.example.codifyv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lista_canciones_album_album extends MyAppCompatActivity {
    String url;
    String tokken;

    RecyclerView canciones_album;
    List<ItemAlbum> listdatos;
    JsonSpotify json;
    Adaptador_Album adaptador_album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_canciones_album_album);

        canciones_album = findViewById(R.id.recy_listaCanciones);

        canciones_album.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        listdatos = new ArrayList<>();
        tokken = getString(R.string.tokken);
        url = "https://api.spotify.com/v1/albums/6akEvsycLGftJxYudPjmqK/tracks";

        json = new JsonSpotify(lista_canciones_album_album.this,url,tokken);
        json.ExtractResponse();
        adaptador_album = new Adaptador_Album(this, listdatos);
        canciones_album.setAdapter(adaptador_album);
    }
    public void Response(String response){
        try {
            JSONObject json_data = new JSONObject(response);
            JSONArray items = json_data.getJSONArray("items");
            for(int i = 0; i<items.length(); i++){
                JSONObject track = items.getJSONObject(i);

                listdatos.add(new ItemAlbum(track.getString("id"),
                        track.getString("name"),
                        track.getString("duration_ms")));

                adaptador_album.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
