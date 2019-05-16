package com.omdb.rohksin.Mangoose.Redesign.Models;

import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;

import java.io.Serializable;

/**
 * Created by Illuminati on 6/22/2017.
 */
public class MovieRole implements Serializable,Comparable<MovieRole>{

    private String movieName;
    private String characterName;
    private String releaseDate;
    private String movieId;
    private String moviePosterPath;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
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

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }

    @Override
    public int compareTo(MovieRole movieRole)
    {
        // Sorting In desending order of Date;


            String date1 = MovieUtils.getSortedDate(this.getReleaseDate());

            String date2 = MovieUtils.getSortedDate(movieRole.getReleaseDate());

            return date2.compareTo(date1);


    }
}
