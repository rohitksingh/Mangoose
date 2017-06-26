package com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl;

import android.util.Log;

import com.omdb.rohksin.omdb.Movie;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;

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
