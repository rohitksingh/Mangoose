package com.omdb.rohksin.Mangoose.NewSearch.ResponseMapper.Impl;

import android.util.Log;

import com.omdb.rohksin.Mangoose.NewSearch.POJO.ActorDetail;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.MovieRole;
import com.omdb.rohksin.Mangoose.NewSearch.ResponseMapper.ResponseMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 6/22/2017.
 */
public class ActorDetailMapper implements ResponseMapper {

    private ActorDetail actorDetail;
    public static String ObjectMapped="com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ObjectMapped";

    @Override
    public void mapResponse(Object object) throws JSONException {

        actorDetail = new ActorDetail();

        JSONObject jsonObject = (JSONObject)object;

        Log.d("CRASH", "INSIDE1 MAPPER");

        actorDetail.setName(jsonObject.getString("name"));
        actorDetail.setId(jsonObject.getInt("id") + "");
        actorDetail.setBirthday(jsonObject.getString("birthday"));
        actorDetail.setBirthPlace(jsonObject.getString("place_of_birth"));
        actorDetail.setDeathday(jsonObject.getString("deathday"));
        actorDetail.setGender(jsonObject.getInt("gender") + "");
        actorDetail.setBiography(jsonObject.getString("biography"));
        actorDetail.setProfileImage(jsonObject.getString("profile_path"));

        try {
            actorDetail.setWebSite(jsonObject.getString("homepage"));
        }
        catch (JSONException e){
            actorDetail.setWebSite("");
        }
        JSONObject movieCredits = jsonObject.getJSONObject("movie_credits");
        JSONArray movieRoles = movieCredits.getJSONArray("cast");

        List<MovieRole> roles = new ArrayList<MovieRole>();



        for(int i=0;i<movieRoles.length();i++)
        {


            try {
                JSONObject movie = movieRoles.getJSONObject(i);
                MovieRole role = new MovieRole();

                role.setCharacterName(movie.getString("character"));
                role.setMovieId(movie.getInt("id") + "");
                role.setMovieName(movie.getString("title"));
                role.setReleaseDate(movie.getString("release_date"));
                role.setMoviePosterPath(movie.getString("poster_path"));
                roles.add(role);
            }
            catch (JSONException e)
            {
                Log.d("CRASH", "caught inside");
            }

        }

        actorDetail.setMovieRoles(roles);


        //actorDetail.setImages();
        //actorDetail.setMovieRoles();
        //actorDetail.setTaggedImages();

    }

    @Override
    public Object objectMapped() {
        return actorDetail;
    }
}
