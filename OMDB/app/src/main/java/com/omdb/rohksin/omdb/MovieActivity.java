package com.omdb.rohksin.omdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.movie_cord_layout);
        frame = (FrameLayout)findViewById(R.id.frameLayout);
        ImageView imageView = (ImageView)findViewById(R.id.image);


    }
}
