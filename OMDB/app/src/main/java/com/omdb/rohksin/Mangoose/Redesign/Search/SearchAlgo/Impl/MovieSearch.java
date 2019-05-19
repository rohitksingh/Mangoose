package com.omdb.rohksin.Mangoose.Redesign.Search.SearchAlgo.Impl;

import android.content.Intent;

import com.omdb.rohksin.Mangoose.Movie;
import com.omdb.rohksin.Mangoose.Redesign.Search.ResponseMapper.Impl.MovieListMap;
import com.omdb.rohksin.Mangoose.Redesign.Search.ResponseMapper.ResponseMapper;
import com.omdb.rohksin.Mangoose.Redesign.Search.SearchAlgo.Search;
import com.omdb.rohksin.Mangoose.Redesign.URLBuilders.Impl.MovieNameURLBuilder;
import com.omdb.rohksin.Mangoose.Redesign.URLBuilders.URLBuilder;

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
        i.putExtra(Search.SEARCH_TYPE, "MOVIE");
        ArrayList<Movie> movies = (ArrayList<Movie>)o;
        i.putExtra(Search.RESULT, movies);
        context.sendBroadcast(i);

    }
}
