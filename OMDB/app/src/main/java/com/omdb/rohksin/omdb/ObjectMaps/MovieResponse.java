package com.omdb.rohksin.omdb.ObjectMaps;

import java.util.Map;

/**
 * Created by Illuminati on 5/13/2017.
 */
public class MovieResponse {

    private static String title;
    private static String year;
    private static String type;
    private static String releasedDate;
    private static String genre;
    private static String rated;
    private static String runtime;
    private static String director;
    private static String writer;
    private static String[] actors;
    private static String poster;
    private static String imdbRating;
    private static Map<String,String> ratings;
    private static String awards;
    private static String boxoffice;
    private static String production;
    private static String fullPlot;
    private static String website;
    private static String country;
    private static String languages;

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        MovieResponse.title = title;
    }

    public static String getYear() {
        return year;
    }

    public static void setYear(String year) {
        MovieResponse.year = year;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        MovieResponse.type = type;
    }

    public static String getReleasedDate() {
        return releasedDate;
    }

    public static void setReleasedDate(String releasedDate) {
        MovieResponse.releasedDate = releasedDate;
    }

    public static String getGenre() {
        return genre;
    }

    public static void setGenre(String genre) {
        MovieResponse.genre = genre;
    }

    public static String getRated() {
        return rated;
    }

    public static void setRated(String rated) {
        MovieResponse.rated = rated;
    }

    public static String getRuntime() {
        return runtime;
    }

    public static void setRuntime(String runtime) {
        MovieResponse.runtime = runtime;
    }

    public static String getDirector() {
        return director;
    }

    public static void setDirector(String director) {
        MovieResponse.director = director;
    }

    public static String getWriter() {
        return writer;
    }

    public static void setWriter(String writer) {
        MovieResponse.writer = writer;
    }

    public static String[] getActors() {
        return actors;
    }

    public static void setActors(String[] actors) {
        MovieResponse.actors = actors;
    }

    public static String getPoster() {
        return poster;
    }

    public static void setPoster(String poster) {
        MovieResponse.poster = poster;
    }

    public static Map<String, String> getRatings() {
        return ratings;
    }

    public static void setRatings(Map<String, String> ratings) {
        MovieResponse.ratings = ratings;
    }

    public static String getAwards() {
        return awards;
    }

    public static void setAwards(String awards) {
        MovieResponse.awards = awards;
    }

    public static String getBoxoffice() {
        return boxoffice;
    }

    public static void setBoxoffice(String boxoffice) {
        MovieResponse.boxoffice = boxoffice;
    }

    public static String getProduction() {
        return production;
    }

    public static void setProduction(String production) {
        MovieResponse.production = production;
    }

    public static String getFullPlot() {
        return fullPlot;
    }

    public static void setFullPlot(String fullPlot) {
        MovieResponse.fullPlot = fullPlot;
    }

    public static String getWebsite() {
        return website;
    }

    public static void setWebsite(String website) {
        MovieResponse.website = website;
    }

    public static String getCountry() {
        return country;
    }

    public static String getImdbRating() {
        return imdbRating;
    }

    public static void setImdbRating(String imdbRating) {
        MovieResponse.imdbRating = imdbRating;
    }

    public static void setCountry(String country) {
        MovieResponse.country = country;

    }

    public static String getLanguages() {
        return languages;
    }

    public static void setLanguages(String languages) {
        MovieResponse.languages = languages;
    }
}
