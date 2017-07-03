package com.omdb.rohksin.omdb;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.omdb.Adaters.RvAdapter;
import com.omdb.rohksin.omdb.NewSearch.POJO.ActorDetail;
import com.omdb.rohksin.omdb.NewSearch.POJO.Genre;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl.ActorDetailMapper;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Illuminati on 7/2/2017.
 */
public class FilterMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstaceState)
    {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.filter_movie_layout);
        final LinearLayout parent = (LinearLayout)findViewById(R.id.parent);
        /*
        RecyclerView rv = (RecyclerView)findViewById(R.id.filterMovieView);
        LinearLayout llm = new LinearLayout(this);

        String endpoint = (String)getIntent().getStringExtra("");

        RvAdapter adapter = new RvAdapter(null,this);
        rv.setAdapter(adapter);
        */
        parent.post(new Runnable() {
            @Override
            public void run() {
                int radius= (int)(Math.hypot(parent.getWidth(),parent.getHeight()));
                if(Build.VERSION.SDK_INT>20) {
                    Animator anim = ViewAnimationUtils.createCircularReveal(parent, parent.getRight(), parent.getBottom(), 0, radius);
                    anim.setDuration(1000);
                    anim.start();
                }

            }
        });

    }




}
