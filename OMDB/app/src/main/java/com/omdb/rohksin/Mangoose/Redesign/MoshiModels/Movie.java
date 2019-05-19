package com.omdb.rohksin.Mangoose.Redesign.MoshiModels;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {

    public boolean adult;
    public String backdrop_path;
    public List<Genre> genres;
    public String homepage;
    public int id;
    public String imdb_id;
    public String original_language;
    public String original_title;
    public String overview;
    public String poster_path;
    public String release_date;
    public String runtime;
    public String status;
    public String tagline;
    public String title;
    public Casts casts;
    public Images images;

    @Override
    public String toString()
    {
        return adult +" \n"+ backdrop_path;
    }
}
