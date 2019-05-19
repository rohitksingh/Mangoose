package com.omdb.rohksin.Mangoose.Redesign.Search.ResponseMapper.Impl;

import com.omdb.rohksin.Mangoose.Movie;
import com.omdb.rohksin.Mangoose.Redesign.Search.ResponseMapper.ResponseMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Illuminati on 6/25/2017.
 */

public class MovieListMap implements ResponseMapper {

    private ArrayList<Movie> movies ;

    @Override
    public void mapResponse(Object object) throws JSONException {


        movies = new ArrayList<Movie>();

        try{

        JSONObject jsonObject = (JSONObject)object;
        JSONArray movieList = jsonObject.getJSONArray("results");

        for(int i=0;i<movieList.length();i++)
        {
            JSONObject movieObject = movieList.getJSONObject(i);

            Movie movie = new Movie();
            movie.setName((String) movieObject.get("title"));
            movie.setReleaseYear((String) movieObject.get("release_date"));
            movie.setPosterThumbnail((movieObject.get("poster_path")).toString());
            movie.setMovieId(((Integer) movieObject.get("id")) + "");
            String overView = movieObject.getString("overview");
            if(overView!=null)
                movie.setOverview(overView);

            movie.getGenres();


            JSONArray genres = movieObject.getJSONArray("genre_ids");
            String[] allGenres= new String[genres.length()];

            for (int j =0;j<genres.length();j++)
            {
                allGenres[j]= genres.getString(j);
            }

            movie.setGenres(allGenres);
            movie.setOriginalLanguage(movieObject.getString("original_language"));
            movies.add(movie);

        }


    } catch (JSONException e) {
        e.printStackTrace();
    }

    }

    @Override
    public Object objectMapped() {
        return movies;
    }
}
