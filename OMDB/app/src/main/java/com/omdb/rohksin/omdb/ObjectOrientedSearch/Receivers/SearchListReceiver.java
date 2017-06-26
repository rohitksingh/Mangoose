package com.omdb.rohksin.omdb.ObjectOrientedSearch.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.omdb.rohksin.omdb.Adaters.PeopleListAdapter;
import com.omdb.rohksin.omdb.Adaters.RvAdapter;
import com.omdb.rohksin.omdb.Adaters.TVShowListAdapter;
import com.omdb.rohksin.omdb.Movie;
import com.omdb.rohksin.omdb.NewSearch.POJO.PeopleDetail;
import com.omdb.rohksin.omdb.NewSearch.POJO.TvShow;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search;
import com.omdb.rohksin.omdb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class SearchListReceiver extends BroadcastReceiver {


    private View view;

    public SearchListReceiver(View v)
    {
        view = v;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equalsIgnoreCase(Search.SEARCH_FINISHED)) {

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);

            if(intent.getStringExtra(Search.SEARCH_TYPE).equalsIgnoreCase("PEOPLE")) {

                ArrayList<PeopleDetail> peopleDetails = (ArrayList<PeopleDetail>) intent.getSerializableExtra(Search.RESULT);
                PeopleListAdapter adapter = new PeopleListAdapter(peopleDetails, context);
                recyclerView.setAdapter(adapter);

            }
            else if(intent.getStringExtra(Search.SEARCH_TYPE).equalsIgnoreCase("MOVIE"))
            {

                ArrayList<Movie> movieList = (ArrayList<Movie>) intent.getSerializableExtra(Search.RESULT);
                RvAdapter adapter = new RvAdapter(movieList,context);
                recyclerView.setAdapter(adapter);

            }
            else
            {

                ArrayList<TvShow> tvShowsList = (ArrayList<TvShow>) intent.getSerializableExtra(Search.RESULT);
                TVShowListAdapter adapter = new TVShowListAdapter(tvShowsList,context);
                recyclerView.setAdapter(adapter);
            }

        }
    }
  }
