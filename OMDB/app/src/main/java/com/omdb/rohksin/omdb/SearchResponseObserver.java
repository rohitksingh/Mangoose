package com.omdb.rohksin.omdb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.omdb.rohksin.omdb.Adaters.RvAdapter;
import com.omdb.rohksin.omdb.ObjectMaps.MovieResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Illuminati on 5/23/2017.
 */
public class SearchResponseObserver extends BroadcastReceiver {

    View parent;
    public final static String RESPONSE ="com.omdb.rohksin.omdb.RESPONSE";

    //private MovieResponse response;
    private SearchResponse response;

    public SearchResponseObserver(View parent)
    {
        this.parent = parent;
    }

    @Override
    public void onReceive(final Context context ,Intent intent)
    {
        if(intent.getAction().equals(MainActivity.RESPONSE_RECEIVED)) {
            // do something
            Toast.makeText(context, "Received", Toast.LENGTH_LONG).show();

            RecyclerView moviesRecyclerView = (RecyclerView)parent.findViewById(R.id.rv);

            response = (SearchResponse)intent.getSerializableExtra("movieResponse");

            List<Movie> movieList = response.getList();



            RvAdapter adapter = new RvAdapter(movieList,context);
            moviesRecyclerView.setAdapter(adapter);


        }

    }
}

