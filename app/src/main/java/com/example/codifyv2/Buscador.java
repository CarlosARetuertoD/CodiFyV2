package com.example.codifyv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Buscador extends MyAppCompatActivity {
    String url;
    String tokken;
    String type;

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

        tokken = getString(R.string.tokken);
        url = "https://api.spotify.com/v1/search?";
        type = "artist";
        ConstruirRecycler();
        edit_busqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Uri builtURI = Uri.parse(url).buildUpon()
                        .appendQueryParameter("q",edit_busqueda.getText().toString())
                        .appendQueryParameter("type", type)
                        .build();
                jsonSpotify = new JsonSpotify(Buscador.this,builtURI.toString(),tokken);
                jsonSpotify.ExtractResponse();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_busqueda.setText("");
                busqueda_data.clear();
                adapter.notifyDataSetChanged();
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
        busqueda_data.clear();
        if (type == "track") {
            ResponseTracks(response);
        }
        if (type == "artist") {
            ResponseArtist(response);
        }
    }
    public void ResponseArtist(String response){
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
    public void ResponseTracks(String response){
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
