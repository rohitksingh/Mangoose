package com.omdb.rohksin.Mangoose.NewSearch.ResponseMapper.Impl;

import android.util.Log;

import com.omdb.rohksin.Mangoose.NewSearch.POJO.Actor;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.Crew;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.DetailMovie;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.Video;
import com.omdb.rohksin.Mangoose.NewSearch.ResponseMapper.ResponseMapper;
import com.omdb.rohksin.Mangoose.NewSearch.Utility.MovieUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Illuminati on 6/17/2017.
 */
public class DetailMovieMapper implements ResponseMapper{

    private DetailMovie movie;
    public static String ObjectMapped="com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ObjectMapped";


    @Override
    public void mapResponse(Object object) {

        JSONObject movieResponse = (JSONObject)object;

        movie = new DetailMovie();

        try {
            movie.setTitle((String) movieResponse.get("original_title"));
            movie.setMovieId(movieResponse.get("id") + "");
            movie.setImdbId(movieResponse.get("imdb_id") + "");
            movie.setOriginalLanguage((String) movieResponse.get("original_language"));
            movie.setRunTime(movieResponse.get("runtime") + "");
            movie.setRelaseDate((String) movieResponse.get("release_date"));
            movie.setHomePage((String) movieResponse.get("homepage"));
            movie.setPosterPath(movieResponse.get("poster_path").toString());
            movie.setBackDropImage(movieResponse.get("backdrop_path").toString());
            movie.setOverView((String)movieResponse.get("overview"));
            movie.setVoteAgerage((movieResponse.getInt("vote_average")*10)+"% ");

            ArrayList<String> imageList = MovieUtils.getAllImages(movieResponse.get("images"));
            ArrayList<Video> videos = MovieUtils.getAllVideos(movieResponse.get("videos"));

            JSONObject jsonObject = (JSONObject)movieResponse.get("casts");

            ArrayList<Actor> actors = MovieUtils.getAllActors(jsonObject.getJSONArray("cast"));
            ArrayList<Crew> crews = MovieUtils.getAllCrews(jsonObject.getJSONArray("crew"));


            movie.setImages(imageList);
            movie.setVideos(videos);
            movie.setActors(actors);
            movie.setCrews(crews);
            movie.setGenres(MovieUtils.getAllGenres(movieResponse.getJSONArray("genres")));
            Log.d("ACTORS", actors.size() + "ACTOR");
            Log.d("ACTORS", crews.size() + "CREW");



       /* movie.setGenres();
        movie.setActors();
        movie.setCrews();
        movie.setProductions();

      */
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public Object objectMapped() {
        return movie;
    }
}
