package com.example.codifyv2;

import androidx.appcompat.app.AppCompatActivity;

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

import de.hdodenhof.circleimageview.CircleImageView;

public class Reproductor extends MyAppCompatActivity {
    String url;
    String tokken;

    TextView txt_namesong;
    TextView txt_artist;
    TextView txt_song_end_time;

    ImageView img_background;
    CircleImageView img_album;

    Button btn_play, btn_estado ,btn_next,btn_previous,btn_addFavoritos,btn_back;

    int bnd_reproduction = 0;// 0-Continua 1-Aleatorio 2-Repetir
    boolean bnd_reproduction_status = false , bnd_favorite_status = false;
    JsonSpotify jsonSpotify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        Iniciar_items();
        BotonesListener();

        Intent intent = getIntent();
        tokken = getString(R.string.tokken);
        url = "https://api.spotify.com/v1/tracks/" + intent.getStringExtra("id");

        jsonSpotify = new JsonSpotify(Reproductor.this,url,tokken);
        jsonSpotify.ExtractResponse();
    }
    void Iniciar_items(){
        txt_namesong = findViewById(R.id.txt_namesong);
        txt_artist = findViewById(R.id.txt_artistsong);
        txt_song_end_time = findViewById(R.id.txt_song_end_time);
        img_background = findViewById(R.id.img_background);
        img_album = findViewById(R.id.img_circlealbum);

        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);
        btn_play = findViewById(R.id.btn_play);
        btn_estado = findViewById(R.id.btn_estado);
        btn_addFavoritos = findViewById(R.id.btn_addfavoritos);
        btn_back = findViewById(R.id.btn_back);
    }
    void BotonesListener(){
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_previous.setBackgroundResource(R.drawable.img_prevsongoff);
                btn_next.setBackgroundResource(R.drawable.img_nextsong);
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_next.setBackgroundResource(R.drawable.img_nextsongoff);
                btn_previous.setBackgroundResource(R.drawable.img_prevsong);
            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bnd_reproduction_status == false) {
                    bnd_reproduction_status = true;
                    btn_play.setBackgroundResource(R.drawable.img_pause);
                }else{
                    bnd_reproduction_status = false;
                    btn_play.setBackgroundResource(R.drawable.img_play);
                }
            }
        });
        btn_addFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bnd_favorite_status == false) {
                    bnd_favorite_status = true;
                    btn_addFavoritos.setBackgroundResource(R.drawable.img_likesongup);
                }else{
                    bnd_favorite_status = false;
                    btn_addFavoritos.setBackgroundResource(R.drawable.img_likesongdown);
                }
            }
        });
        btn_estado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bnd_reproduction == 0){
                    bnd_reproduction = 1; //CAMBIANDO AL MODO ALEATORIO
                    btn_estado.setBackgroundResource(R.drawable.img_shuffle);
                }else if(bnd_reproduction == 1){
                    bnd_reproduction = 2; //CAMBIANDO AL MODO REPETIR
                    btn_estado.setBackgroundResource(R.drawable.img_repeat);
                }else if(bnd_reproduction == 2){
                    bnd_reproduction = 0; //CAMBIANDO AL MODO CONTINUO
                    btn_estado.setBackgroundResource(R.drawable.img_continuousreproduction);
                }
            }
        });
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
    public void Response(String response){
        try {

            JSONObject json_data = new JSONObject(response);

            JSONArray artist = json_data.getJSONArray("artists");
            JSONObject name_artista = artist.getJSONObject(0);

            JSONObject album = json_data.getJSONObject("album");
            JSONArray images = album.getJSONArray("images");
            JSONObject url = images.getJSONObject(0);

            Glide.with(this).load(url.getString("url")).into(img_album);
            Glide.with(this).load(url.getString("url")).into(img_background);

            txt_namesong.setText(json_data.getString("name"));
            txt_artist.setText(name_artista.getString("name"));
            txt_song_end_time.setText(String.valueOf(json_data.getLong("duration_ms") / 60));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
