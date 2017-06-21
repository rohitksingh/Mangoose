package com.omdb.rohksin.omdb.LandingActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.omdb.rohksin.omdb.Adaters.CrewListAdapter;
import com.omdb.rohksin.omdb.BlankActivity;
import com.omdb.rohksin.omdb.NewSearch.POJO.Crew;
import com.omdb.rohksin.omdb.R;
import com.omdb.rohksin.omdb.SerializableCarriers.SerializableCrewList;

import java.util.List;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class AllCrewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_landing_layout);

        SerializableCrewList serializableCrewList = (SerializableCrewList)getIntent().getSerializableExtra(BlankActivity.MOVIE_LIST);
        List<Crew> crews = serializableCrewList.getObjectList();

        RecyclerView crewRecyclerView = (RecyclerView)findViewById(R.id.image_list_landing);
        CrewListAdapter adapter = new CrewListAdapter(crews,this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        crewRecyclerView.setLayoutManager(llm);
        crewRecyclerView.setAdapter(adapter);








    }
}
