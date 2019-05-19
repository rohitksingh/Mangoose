package com.omdb.rohksin.Mangoose.Redesign.URLBuilders.Impl;

/**
 * Created by Illuminati on 6/24/2017.
 */
public class MovieNameURLBuilder extends SearchNameURLBuilder {

    private String searchTerm;

    public MovieNameURLBuilder(String searchTerm)
    {
        this.searchTerm = searchTerm;
    }

    @Override
    public String getSpecific() {
        return "search/movie?";
    }

    @Override
    public String getSeachTerm() {

        String paddedString = searchTerm.replace(" ","%20");
        return "&query="+paddedString;

    }

    @Override
    public String getQueryParams() {
        return "&page=1";
    }


}
