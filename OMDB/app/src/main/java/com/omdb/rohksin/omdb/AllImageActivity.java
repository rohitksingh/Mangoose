package com.omdb.rohksin.omdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.omdb.rohksin.omdb.Adaters.ImageLandingListAdapter;
import com.omdb.rohksin.omdb.Adaters.RvAdapter;

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
        setContentView(R.layout.image_landing_layout);

        List<String> moviesList = getIntent().getStringArrayListExtra(BlankActivity.MOVIE_LIST);
        /*RecyclerView recyclerImageView = (RecyclerView)findViewById(R.id.image_list_landing);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        ImageLandingListAdapter adapter = new ImageLandingListAdapter(moviesList,this);
        recyclerImageView.setLayoutManager(llm);
        recyclerImageView.setAdapter(adapter);
        */
        Toast.makeText(this,"kjdksjd",Toast.LENGTH_LONG);

    }
}
