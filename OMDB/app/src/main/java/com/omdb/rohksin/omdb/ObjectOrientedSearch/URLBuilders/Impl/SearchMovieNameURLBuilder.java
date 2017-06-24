package com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl;

import com.omdb.rohksin.omdb.Search;

/**
 * Created by Illuminati on 6/24/2017.
 */
public class SearchMovieNameURLBuilder extends SearchNameURLBuilder {

    private String searchTerm;

    public SearchMovieNameURLBuilder(String searchTerm)
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
