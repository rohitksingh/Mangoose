package com.omdb.rohksin.Mangoose.Redesign.Models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Illuminati on 6/26/2017.
 */
public class TvShow implements Serializable {

    private String id;
    private String name;
    private String overview;
    private String posterPath;
    private String air_date;
    private List<Genre> genres;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
