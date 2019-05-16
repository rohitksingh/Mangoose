package com.omdb.rohksin.Mangoose.NewSearch.ResponseMapper.Impl;

import com.omdb.rohksin.Mangoose.Redesign.Models.TvShow;
import com.omdb.rohksin.Mangoose.NewSearch.ResponseMapper.ResponseMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Illuminati on 6/26/2017.
 */
public class TVShowListMap implements ResponseMapper {

   private ArrayList<TvShow> tvshowList;

    @Override
    public void mapResponse(Object object) throws JSONException {

        tvshowList = new ArrayList<TvShow>();

        try{

            JSONObject jsonObject = (JSONObject)object;
            JSONArray tvShowObjects = jsonObject.getJSONArray("results");

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

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object objectMapped() {
        return tvshowList;
    }
}
