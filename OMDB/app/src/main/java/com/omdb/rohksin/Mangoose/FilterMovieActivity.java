package com.omdb.rohksin.Mangoose;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;

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
