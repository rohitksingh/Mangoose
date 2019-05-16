package com.omdb.rohksin.Mangoose.Redesign.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.omdb.rohksin.Mangoose.Redesign.Adapters.CrewListAdapter;
import com.omdb.rohksin.Mangoose.Redesign.Models.Crew;
import com.omdb.rohksin.Mangoose.R;
import com.omdb.rohksin.Mangoose.SerializableCarriers.SerializableCrewList;

import java.util.List;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class AllCrewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.image_landing_layout);

        getSupportActionBar().setTitle("Crew");

        SerializableCrewList serializableCrewList = (SerializableCrewList)getIntent().getSerializableExtra(MovieDetailActivity.MOVIE_LIST);
        List<Crew> crews = serializableCrewList.getObjectList();

        RecyclerView crewRecyclerView = (RecyclerView)findViewById(R.id.image_list_landing);
        CrewListAdapter adapter = new CrewListAdapter(crews,this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        crewRecyclerView.setLayoutManager(llm);
        crewRecyclerView.setAdapter(adapter);

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
