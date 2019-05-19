package com.omdb.rohksin.Mangoose.Redesign.Search.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.Redesign.Adapters.PeopleListAdapter;
import com.omdb.rohksin.Mangoose.Redesign.Adapters.RvAdapter;
import com.omdb.rohksin.Mangoose.Redesign.Adapters.TVShowListAdapter;
import com.omdb.rohksin.Mangoose.Movie;
import com.omdb.rohksin.Mangoose.Redesign.Models.PeopleDetail;
import com.omdb.rohksin.Mangoose.Redesign.Models.TvShow;
import com.omdb.rohksin.Mangoose.Redesign.Search.SearchAlgo.Search;
import com.omdb.rohksin.Mangoose.R;

import java.util.ArrayList;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class SearchListReceiver extends BroadcastReceiver {


    private View view;
    private Context context;
    private RecyclerView recyclerView;

    public SearchListReceiver(View v)
    {
        view = v;
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equalsIgnoreCase(Search.SEARCH_FINISHED)) {

            recyclerView = (RecyclerView) view.findViewById(R.id.rv);

            hideMsg();
            stopAnimation();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

            recyclerView.setLayoutManager(linearLayoutManager);

            if(intent.getStringExtra(Search.SEARCH_TYPE).equalsIgnoreCase("PEOPLE")) {

                ArrayList<PeopleDetail> peopleDetails = (ArrayList<PeopleDetail>) intent.getSerializableExtra(Search.RESULT);
                if(peopleDetails.size()==0)
                {
                    NoResultFound();
                }
                else {
                    recyclerView.setVisibility(View.VISIBLE);
                    PeopleListAdapter adapter = new PeopleListAdapter(peopleDetails, context);
                    recyclerView.setAdapter(adapter);
                }

            }
            else if(intent.getStringExtra(Search.SEARCH_TYPE).equalsIgnoreCase("MOVIE"))
            {

                ArrayList<Movie> movieList = (ArrayList<Movie>) intent.getSerializableExtra(Search.RESULT);
                if(movieList.size()==0)
                {
                    NoResultFound();
                }
                else {
                    recyclerView.setVisibility(View.VISIBLE);
                    RvAdapter adapter = new RvAdapter(movieList, context);
                    recyclerView.setAdapter(adapter);
                }

            }
            else
            {

                ArrayList<TvShow> tvShowsList = (ArrayList<TvShow>) intent.getSerializableExtra(Search.RESULT);
                if(tvShowsList.size()==0)
                {
                    NoResultFound();
                }
                else {
                    recyclerView.setVisibility(View.VISIBLE);
                    TVShowListAdapter adapter = new TVShowListAdapter(tvShowsList, context);
                    recyclerView.setAdapter(adapter);
                }
            }

        }
    }



    public void stopAnimation()
    {
        ImageView cancelButton = (ImageView)view.findViewById(R.id.cancel);
        cancelButton.clearAnimation();
    }


    public void hideMsg()
    {
        TextView layout = (TextView) view.findViewById(R.id.comingSoon);
        layout.setVisibility(View.GONE);
        TextView share = (TextView) view.findViewById(R.id.Share);
        share.setVisibility(View.GONE);

    }

    public void NoResultFound()
    {
            TextView layout = (TextView) view.findViewById(R.id.comingSoon);
            layout.setVisibility(View.VISIBLE);
            layout.setText("NO RESULT FOUND :(");

            TextView share = (TextView) view.findViewById(R.id.Share);
            share.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);



    }
  }
