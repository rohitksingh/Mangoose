package com.omdb.rohksin.omdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.EpisodeIDURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.SeasonIdURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.URLBuilder;

import org.json.JSONObject;

/**
 * Created by Illuminati on 6/30/2017.
 */
public class EpisodeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceSstate)
    {
    super.onCreate(savedInstanceSstate);
    setContentView(R.layout.tv_show_activity);
    String tvShowId = (String)getIntent().getStringExtra("tv_id");
    String season_num = (String)getIntent().getStringExtra("season_num");
    String episode_num = (String)getIntent().getStringExtra("episode_num");

    final TextView responseTextView = (TextView)findViewById(R.id.response);

        URLBuilder urlBuilder = new EpisodeIDURLBuilder(tvShowId,season_num,episode_num);
    String endpoint = urlBuilder.bulidURL();

    Log.d("TVURL", endpoint);
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,endpoint,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.d("ENDPOINT", response.toString());

                    responseTextView.setText(response.toString());
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }

    );

    requestQueue.add(request);

}

}
