package com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl;

/**
 * Created by Illuminati on 6/24/2017.
 */
public class MovieIDURLBuilder extends SearchIDURLBuilder{

    private String seachTerm;

    public MovieIDURLBuilder(String searchTerm)
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
