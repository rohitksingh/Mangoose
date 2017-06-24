package com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl;

/**
 * Created by Illuminati on 6/24/2017.
 */
public class SeachMovieIDURLBuilder extends SearchIDURLBuilder{

    private String seachTerm;

    public SeachMovieIDURLBuilder(String searchTerm)
    {
        this.seachTerm = searchTerm;
    }

    @Override
    public String getSpecific() {
        return "movie/";
    }

    @Override
    public String getSeachTerm() {
        return seachTerm+"?";
    }

    @Override
    public String getQueryParams() {
        return "&append_to_response=images,casts,videos";
    }
}
