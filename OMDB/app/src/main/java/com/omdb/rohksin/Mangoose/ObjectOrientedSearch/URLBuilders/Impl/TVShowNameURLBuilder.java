package com.omdb.rohksin.Mangoose.ObjectOrientedSearch.URLBuilders.Impl;

/**
 * Created by Illuminati on 6/26/2017.
 */
public class TVShowNameURLBuilder extends SearchNameURLBuilder{

    private String searchTerm;

    public TVShowNameURLBuilder(String searchTerm)
    {
        this.searchTerm = searchTerm;
    }

    @Override
    public String getSpecific() {
        return "search/tv?";
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
