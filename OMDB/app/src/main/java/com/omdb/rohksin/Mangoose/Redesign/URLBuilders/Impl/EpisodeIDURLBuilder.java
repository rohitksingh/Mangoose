package com.omdb.rohksin.Mangoose.Redesign.URLBuilders.Impl;

/**
 * Created by Illuminati on 6/29/2017.
 */
public class EpisodeIDURLBuilder extends SearchIDURLBuilder {

    private String tv_id;
    private String season_num;
    private String episode_num;

    public EpisodeIDURLBuilder(String tv_id,String season_num,String episode_num)
    {
        this.tv_id = tv_id;
        this.season_num = season_num;
        this.episode_num =episode_num;
    }
    @Override
    public String getSpecific() {
        return "tv/"+tv_id+"/season/"+season_num+"/episode/";
    }

    @Override
    public String getSeachTerm() {
        return episode_num+"?";
    }

    @Override
    public String getQueryParams() {
        return "&language=en-US&append_to_response=images,credits";
    }
}

