package com.omdb.rohksin.omdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.omdb.rohksin.omdb.Adaters.ActorsListAdapter;
import com.omdb.rohksin.omdb.NewSearch.POJO.Actor;
import com.omdb.rohksin.omdb.QueryBuilder.SerializableObject;
import com.omdb.rohksin.omdb.R;

import java.util.List;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class AllActorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_landing_layout);

        SerializableObject object = (SerializableObject)getIntent().getSerializableExtra(BlankActivity.MOVIE_LIST);
        List<Actor> actorList = object.getObjectList();

        RecyclerView allActorRecyclerView = (RecyclerView)findViewById(R.id.image_list_landing);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        allActorRecyclerView.setLayoutManager(llm);
        Log.d("LIST EMPTY",(actorList==null)+"");
        ActorsListAdapter adapter = new ActorsListAdapter(actorList,this);
        allActorRecyclerView.setAdapter(adapter);



    }
}
