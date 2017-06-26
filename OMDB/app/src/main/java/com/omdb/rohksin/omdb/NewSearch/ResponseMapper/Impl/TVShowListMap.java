package com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl;

import android.util.Log;

import com.omdb.rohksin.omdb.Movie;
import com.omdb.rohksin.omdb.NewSearch.POJO.TvShow;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 6/26/2017.
 */
public class TVShowListMap implements ResponseMapper {

   private ArrayList<TvShow> tvshowList;

    @Override
    public void mapResponse(Object object) throws JSONException {

        Log.d("TV",(object==null)+"");
        tvshowList = new ArrayList<TvShow>();

        try{

            JSONObject jsonObject = (JSONObject)object;

            JSONArray tvShowObjects = jsonObject.getJSONArray("results");

            Log.d("TV1",(tvShowObjects.length())+"");


            for(int i=0;i<tvShowObjects.length();i++)
            {
                JSONObject tvShowObject = tvShowObjects.getJSONObject(i);

                TvShow tvShow = new TvShow();

                tvShow.setId(tvShowObject.getInt("id")+"");
                tvShow.setName(tvShowObject.getString("name"));
                tvShow.setAir_date(tvShowObject.getString("first_air_date"));
                tvShow.setOverview(tvShowObject.getString("overview"));
                tvShow.setPosterPath(tvShowObject.getString("poster_path"));

                tvshowList.add(tvShow);
                //TO DO INTEGRATE GENRES
            }

            Log.d("TVSHOWLIST",tvshowList.size()+" ");




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object objectMapped() {
        Log.d("OBJECLIST",tvshowList.size()+"");
        return tvshowList;
    }
}
