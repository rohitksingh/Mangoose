package com.omdb.rohksin.omdb.QueryBuilder.Impl;

import android.provider.SyncStateContract;

import com.omdb.rohksin.omdb.Constants.AppConstants;
import com.omdb.rohksin.omdb.QueryBuilder.QueryBuilder;

/**
 * Created by Illuminati on 5/22/2017.
 */
public class SimpleMovieQuery implements QueryBuilder {

    private String text;

    public SimpleMovieQuery(String text)
    {
        this.text = text;
    }

    @Override
    public String formUrl() {

        text = text.replace(" ","%20");
        String endpount = AppConstants.BASE_URL+"search/movie?api_key="+AppConstants.TMDB_KEY+"&query="+text;
        return endpount;
    }
}
