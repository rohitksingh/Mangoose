package com.omdb.rohksin.Mangoose.Redesign.URLBuilders.Impl;

/**
 * Created by Illuminati on 6/29/2017.
 */
public class TVShowIDURLBuilder extends SearchIDURLBuilder {

    private String tv_id;

    public TVShowIDURLBuilder(String tv_id)
    {
        this.tv_id = tv_id;
    }

    @Override
    public String getSpecific() {
        return "tv/";
    }

    @Override
    public String getSeachTerm() {
        return tv_id+"?";
    }

    @Override
    public String getQueryParams() {
        return "&language=en-US&append_to_response=images,credits,videos,similar,recommendations";
    }

}

