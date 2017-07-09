package com.omdb.rohksin.omdb;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Illuminati on 5/7/2017.
 */
public class Movie implements Serializable{

    private String name;
    private String releaseYear;
    private String PosterThumbnail;
    private String movieId;
    private String overview;

    private String[] genres;

    private String originalLanguage;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPosterThumbnail() {
        return PosterThumbnail;
    }

    public void setPosterThumbnail(String posterThumbnail) {
        PosterThumbnail = posterThumbnail;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
}
