package com.omdb.rohksin.Mangoose.Redesign.Models;

/**
 * Created by Illuminati on 6/26/2017.
 */
public class Cast {

    private String characteName;
    private String movieName;
    private String moviePoster;
    private String releaseDate;
    private String movieId;

    public String getCharacteName() {
        return characteName;
    }

    public void setCharacteName(String characteName) {
        this.characteName = characteName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
