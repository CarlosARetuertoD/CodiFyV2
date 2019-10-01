package com.example.codifyv2;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Buscador extends MyAppCompatActivity {
    String url;
    String tokken;
    String type;

    ImageButton btn_back;
    SearchView sv_buscador;
    RecyclerView rv_busqueda;
    List<Item_Buscador> busqueda_data;
    BuscadorAdapter adapter;
    JsonSpotify jsonSpotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);

        btn_back = findViewById(R.id.btn_back_buscador);
        rv_busqueda = findViewById(R.id.rv_busqueda);
        sv_buscador = findViewById(R.id.sv_buscador);
        busqueda_data = new ArrayList<>();

        tokken = getString(R.string.tokken);
        url = "https://api.spotify.com/v1/search?";
        type = "track";

        ConstruirRecycler();
        ListenersItems();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
            }
        }
    }
    void ConstruirRecycler(){
        adapter = new BuscadorAdapter(this, busqueda_data);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Buscador.this, Reproductor.class);
                intent.putExtra("id",busqueda_data.get(rv_busqueda.getChildAdapterPosition(view)).getIdItem());
                startActivityForResult(intent, 1);
            }
        });
        rv_busqueda.setAdapter(adapter);
        rv_busqueda.setLayoutManager(new LinearLayoutManager(this));
    }
    void ListenersItems(){
        sv_buscador.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                busqueda_data.clear();
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        sv_buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Uri builtURI = Uri.parse(url).buildUpon()
                        .appendQueryParameter("q",s)
                        .appendQueryParameter("type", type)
                        .build();
                jsonSpotify = new JsonSpotify(Buscador.this,builtURI.toString(),tokken);
                jsonSpotify.ExtractResponse();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Uri builtURI = Uri.parse(url).buildUpon()
                        .appendQueryParameter("q",s)
                        .appendQueryParameter("type", type)
                        .build();
                jsonSpotify = new JsonSpotify(Buscador.this,builtURI.toString(),tokken);
                jsonSpotify.ExtractResponse();
                return false;
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("response","Busqueda exitosa");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
    public void Response(String response){
        busqueda_data.clear();
        if (type == "track") {
            ResponseTracks(response);
        }
        if (type == "artist") {
            ResponseArtist(response);
        }
    }
    void ResponseArtist(String response){
        try {
            JSONObject json_data_artists = new JSONObject(response).getJSONObject("artists");
            JSONArray items = json_data_artists.getJSONArray("items");
            for(int i = 0; i<items.length(); i++){
                JSONObject item = items.getJSONObject(i);
                JSONObject images = item.getJSONArray("images").getJSONObject(2);
                busqueda_data.add(new Item_Buscador(item.getString("id"),
                        item.getString("name"),
                        item.getString("type"),
                        images.getString("url")
                ));
                adapter.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    void ResponseTracks(String response){
        try {
            JSONObject json_data_artists = new JSONObject(response).getJSONObject("tracks");
            JSONArray items = json_data_artists.getJSONArray("items");
            for(int i = 0; i<items.length(); i++){
                JSONObject item = items.getJSONObject(i);
                JSONObject images = item.getJSONObject("album").getJSONArray("images").getJSONObject(2);
                JSONObject artist = item.getJSONArray("artists").getJSONObject(0);
                busqueda_data.add(new Item_Buscador(item.getString("id"),
                        item.getString("name"),
                        artist.getString("name")+ "/" + item.getString("type"),
                        images.getString("url")
                        ));
                adapter.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
