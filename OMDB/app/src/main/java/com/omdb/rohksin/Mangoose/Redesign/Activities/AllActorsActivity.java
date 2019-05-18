package com.omdb.rohksin.Mangoose.Redesign.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.omdb.rohksin.Mangoose.Redesign.Adapters.ActorsListAdapter;

import com.omdb.rohksin.Mangoose.R;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Cast;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class AllActorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.image_landing_layout);

        getSupportActionBar().setTitle("Cast");

        List<Cast> casts = (List<Cast>) getIntent().getSerializableExtra(MovieDetailActivity.MOVIE_LIST);

        RecyclerView allActorRecyclerView = (RecyclerView)findViewById(R.id.image_list_landing);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        allActorRecyclerView.setLayoutManager(llm);

        ActorsListAdapter adapter = new ActorsListAdapter(casts,this);
        allActorRecyclerView.setAdapter(adapter);
    }

    public void setAnimation()
    {
        if(Build.VERSION.SDK_INT>20) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setDuration(400);
            slide.setInterpolator(new AccelerateDecelerateInterpolator());
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
        }
    }
}
