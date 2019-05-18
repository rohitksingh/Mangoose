package com.omdb.rohksin.Mangoose.Redesign.Activities.ListActivities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.omdb.rohksin.Mangoose.Redesign.Activities.DetailActivities.MovieDetailActivity;
import com.omdb.rohksin.Mangoose.Redesign.Adapters.ActorsListAdapter;

import com.omdb.rohksin.Mangoose.R;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Cast;

import java.util.List;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class AllActorsActivity extends BasicListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public RecyclerView.Adapter createAdapter() {

        List<Cast> casts = (List<Cast>) getIntent().getSerializableExtra(MovieDetailActivity.MOVIE_LIST);
        return new ActorsListAdapter(casts,this);
    }

    @Override
    public void createUI() {
        setContentView(R.layout.image_landing_layout);
        getSupportActionBar().setTitle("Cast");
        recyclerView = (RecyclerView)findViewById(R.id.image_list_landing);
    }

}
