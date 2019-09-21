package com.example.codifyv2;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class JsonSpotify {
    Context c;
    MyAppCompatActivity activity;
    final String url;
    final String tokken;

    public JsonSpotify(MyAppCompatActivity a, String url, String tokken) {
        this.c = a.getApplicationContext();
        this.url = url;
        this.tokken = tokken;
        this.activity = a;
    }
    public void ExtractResponse(){
        RequestQueue queue = Volley.newRequestQueue(c);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        activity.Response(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(c, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer "+ tokken);
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
