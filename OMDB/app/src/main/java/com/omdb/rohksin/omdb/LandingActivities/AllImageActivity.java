package com.omdb.rohksin.omdb.LandingActivities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.omdb.rohksin.omdb.Adaters.ImageLandingListAdapter;
import com.omdb.rohksin.omdb.Adaters.ListAdaper;
import com.omdb.rohksin.omdb.Adaters.RvAdapter;
import com.omdb.rohksin.omdb.BlankActivity;
import com.omdb.rohksin.omdb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 6/20/2017.
 */
public class AllImageActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstaceState)
    {
        super.onCreate(savedInstaceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setAnimation();
        setContentView(R.layout.image_landing_layout);

        List<String> moviesList = getIntent().getStringArrayListExtra(BlankActivity.MOVIE_LIST);
        RecyclerView recyclerImageView = (RecyclerView)findViewById(R.id.image_list_landing);
        GridLayoutManager llm = new GridLayoutManager(this,2);
        ListAdaper adapter = new ListAdaper(moviesList,this);
        recyclerImageView.setLayoutManager(llm);
        recyclerImageView.setAdapter(adapter);

        //Toast.makeText(this,"kjdksjd",Toast.LENGTH_LONG);

    }

    public void setAnimation()
    {
        if(Build.VERSION.SDK_INT>20) {
            Explode explode = new Explode();
            explode.setDuration(1000);
            explode.setInterpolator(new DecelerateInterpolator());
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
        }
    }
}
