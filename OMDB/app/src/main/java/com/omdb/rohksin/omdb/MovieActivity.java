package com.omdb.rohksin.omdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;




/**
 * Created by Illuminati on 5/8/2017.
 */
public class MovieActivity extends AppCompatActivity{

    private FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_short_layout);
        frame = (FrameLayout)findViewById(R.id.frameLayout);
        ImageView imageView = (ImageView)findViewById(R.id.image);


    }
}
