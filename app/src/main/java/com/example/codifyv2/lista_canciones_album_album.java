package com.example.codifyv2;

import androidx.appcompat.app.AppCompatActivity;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lista_canciones_album_album extends AppCompatActivity {

    RecyclerView canciones_album;
    List<Item_album> list_album;
    JsonSpotify json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_canciones_album_album);

        canciones_album = findViewById(R.id.recy_listaCanciones);
        list_album = new ArrayList<>();
        json = new JsonSpotify("items","https://api.spotify.com/v1/albums/6akEvsycLGftJxYudPjmqK/tracks",
                "BQDge21oKrU5OhmU-wyxjfGIxy_t3GuBmm4WeTiNrHMjrsroAm7lt0rMg6Nuhn0ExsbcYFb825aorqNBI68DQx9Ic13hbaTlcDAkyDiSlHf6W62Nm5qAcZTfzdpLWUHhxSSdcP8918YAYUO8-fTWfsLCJGLKcE1BJkMjjYH05oFTzQfb5Z0nttl2PlrjjJIo8HJOIsbduVkl2ZX1txXwxEPNGanlUxEzhdTo4TNKn0pR5J8Ncd2sB18NoGqgnJJOYivxzbVX7MI0-D_Yju3FcCvAL9nN3Z1u");

    }

}
