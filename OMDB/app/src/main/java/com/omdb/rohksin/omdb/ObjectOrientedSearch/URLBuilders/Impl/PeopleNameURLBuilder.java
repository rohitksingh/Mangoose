package com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl;

/**
 * Created by Illuminati on 6/24/2017.
 */
public class PeopleNameURLBuilder extends SearchNameURLBuilder {

    private String name;

    public PeopleNameURLBuilder(String name)
    {
        this.name = name;
    }

    @Override
    public String getSpecific() {
        return "search/person?";
    }

    @Override
    public String getSeachTerm() {
        String paddedString = name.replace(" ","%20");
        return "&query="+paddedString;
    }

    @Override
    public String getQueryParams() {
        return "&page=1&include_adult=true";
    }
}
