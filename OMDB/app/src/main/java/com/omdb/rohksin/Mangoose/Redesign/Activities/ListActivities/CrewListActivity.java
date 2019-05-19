package com.omdb.rohksin.Mangoose.Redesign.Activities.ListActivities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.omdb.rohksin.Mangoose.Redesign.Activities.DetailActivities.MovieDetailActivity;
import com.omdb.rohksin.Mangoose.Redesign.Adapters.CrewListAdapter;
import com.omdb.rohksin.Mangoose.R;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Crew;

import java.util.List;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class CrewListActivity extends BasicListActivity {

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
        getSupportActionBar().setTitle("Crew");
    }

}
