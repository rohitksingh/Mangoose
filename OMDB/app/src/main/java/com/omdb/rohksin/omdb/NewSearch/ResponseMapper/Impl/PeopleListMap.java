package com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl;

import android.util.Log;

import com.omdb.rohksin.omdb.Movie;
import com.omdb.rohksin.omdb.NewSearch.POJO.PeopleDetail;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class PeopleListMap implements ResponseMapper {

    //private PeopleDetail peopleDetail;
    private ArrayList<PeopleDetail> peopleDetails;

    @Override
    public void mapResponse(Object object) throws JSONException {

        Log.d("OBJECT",(object==null)+"1");

        JSONObject jsonObject = (JSONObject)object;

        JSONArray peoplelist = jsonObject.getJSONArray("results");

        Log.d("PEOPLELIST",peoplelist.length()+"");

        peopleDetails = new ArrayList<PeopleDetail>();

        for(int i=0;i<peoplelist.length();i++)
        {
            PeopleDetail detail = new PeopleDetail();
            JSONObject actor = (JSONObject)peoplelist.get(i);

            detail.setName(actor.getString("name"));
            detail.setId(actor.getInt("id") + "");
            detail.setPeofileImage(actor.getString("profile_path"));

            Log.d("ACTORS DETAL",detail.getName());
            JSONArray knownfor = actor.getJSONArray("known_for");

            int top3 = 3;
            if(knownfor.length()<3)
            top3 = knownfor.length();

            List<Movie> movies = new ArrayList<Movie>();
/*
            for(int j=0;j<top3;j++)
            {
                Log.d("ACTORS DETAL",);
                JSONObject movieObject = (JSONObject)knownfor.get(j);

                Movie movie = new Movie();
                movie.setName(movieObject.getString("title"));
                movie.setMovieId(movieObject.getInt("id") + "");
                movie.setPosterThumbnail(movieObject.getString("poster_path"));
                movies.add(movie);
            }
            */
            peopleDetails.add(detail);
        }

    }

    @Override
    public Object objectMapped() {
        Log.d("LISTSIZE",peopleDetails.size()+"");
        return peopleDetails;
    }
}
