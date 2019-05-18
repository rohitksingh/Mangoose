package com.omdb.rohksin.Mangoose.Redesign.Activities.ListActivities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.omdb.rohksin.Mangoose.Redesign.Activities.BasicListActivity;
import com.omdb.rohksin.Mangoose.Redesign.Activities.MovieDetailActivity;
import com.omdb.rohksin.Mangoose.Redesign.Adapters.CrewListAdapter;
import com.omdb.rohksin.Mangoose.R;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Crew;
import com.omdb.rohksin.Mangoose.SerializableCarriers.SerializableCrewList;

import java.util.List;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class AllCrewActivity extends BasicListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public RecyclerView.Adapter createAdapter() {

        List<Crew> crews = (List<Crew>) getIntent().getSerializableExtra(MovieDetailActivity.MOVIE_LIST);
        return new CrewListAdapter(crews,this);
    }

    @Override
    public void createUI() {
        setContentView(R.layout.image_landing_layout);
        getSupportActionBar().setTitle("Crew");
        recyclerView = (RecyclerView)findViewById(R.id.image_list_landing);
    }

}
