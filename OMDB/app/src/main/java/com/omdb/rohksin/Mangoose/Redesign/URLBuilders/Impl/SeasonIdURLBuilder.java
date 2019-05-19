package com.omdb.rohksin.Mangoose.Redesign.URLBuilders.Impl;

/**
 * Created by Illuminati on 6/29/2017.
 */
public class SeasonIdURLBuilder extends SearchIDURLBuilder {

    private String tv_id;
    private String season_num;

    public SeasonIdURLBuilder(String tv_id,String season_num)
    {
        this.tv_id = tv_id;
        this.season_num = season_num;
    }

    @Override
    public String getSpecific() {
        return "tv/"+tv_id+"/season/";
    }

    @Override
    public String getSeachTerm() {
        return season_num+"?";
    }

    @Override
    public String getQueryParams() {
        return "&language=en-US&append_to_response=images,credits";
    }
}
