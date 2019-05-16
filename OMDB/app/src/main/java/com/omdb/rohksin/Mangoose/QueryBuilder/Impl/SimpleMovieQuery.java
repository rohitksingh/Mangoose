package com.omdb.rohksin.Mangoose.QueryBuilder.Impl;

import com.omdb.rohksin.Mangoose.Redesign.Utilities.AppConstants;
import com.omdb.rohksin.Mangoose.QueryBuilder.QueryBuilder;

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
