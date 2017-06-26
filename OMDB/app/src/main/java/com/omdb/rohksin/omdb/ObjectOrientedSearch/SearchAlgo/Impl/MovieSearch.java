package com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Impl;

import android.content.Intent;
import android.util.Log;

import com.omdb.rohksin.omdb.Movie;
import com.omdb.rohksin.omdb.NewSearch.POJO.PeopleDetail;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl.MovieListMap;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.MovieNameURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.PeopleNameURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.URLBuilder;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class MovieSearch extends Search {

    private String searchTerm;

    public MovieSearch(String searchTerm)
    {
        this.searchTerm = searchTerm;
    }

    @Override
    public String getEndpoint() {

        URLBuilder urlBuilder = new MovieNameURLBuilder(searchTerm);
        Log.d("URLBUILDER",urlBuilder.bulidURL());
        return urlBuilder.bulidURL();
    }

    @Override
    public Object getResult(Object object) {

        ResponseMapper responseMapper = new MovieListMap();
        try {
            responseMapper.mapResponse(object);
            return responseMapper.objectMapped();
        }
        catch (JSONException e)
        {

        }

        return null;
    }

    @Override
    public void processComplete(Object o) {
        Intent i = new Intent();
        i.setAction(Search.SEARCH_FINISHED);
        i.putExtra("PEOPLE SEARCH", "MOVIE");
        ArrayList<Movie> movies = (ArrayList<Movie>)o;
        // PeopleDetail map = (PeopleDetail)o;
        i.putExtra(Search.RESULT, movies);
        Log.d("ORIENTEDS", "BROADCAST SENT"+(movies.size()));
        context.sendBroadcast(i);

    }
}
