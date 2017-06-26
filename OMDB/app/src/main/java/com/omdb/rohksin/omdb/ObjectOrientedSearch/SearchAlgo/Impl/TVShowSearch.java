package com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Impl;

import android.content.Intent;
import android.util.Log;

import com.omdb.rohksin.omdb.NewSearch.POJO.PeopleDetail;
import com.omdb.rohksin.omdb.NewSearch.POJO.TvShow;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl.PeopleListMap;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl.TVShowListMap;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.TVShowNameURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.URLBuilder;

import java.util.ArrayList;

/**
 * Created by Illuminati on 6/26/2017.
 */
public class TVShowSearch extends Search{

    private String searchTerm;

    public TVShowSearch(String  searchTerm)
    {
        this.searchTerm = searchTerm;
    }

    @Override
    public String getEndpoint() {
        URLBuilder urlBuilder = new TVShowNameURLBuilder(searchTerm);
        Log.d("URL",urlBuilder.bulidURL());
        return urlBuilder.bulidURL();
    }

    @Override
    public Object getResult(Object object) {

        ResponseMapper responseMapper = new TVShowListMap();
        try {
            responseMapper.mapResponse(object);

            return responseMapper.objectMapped();
        }
        catch (Exception e)
        {

        }


        return null;
    }

    @Override
    public void processComplete(Object o) {

        Intent i = new Intent();
        i.setAction(Search.SEARCH_FINISHED);
        i.putExtra("PEOPLE SEARCH", "TVSHOW");
        ArrayList<TvShow> tvShows = (ArrayList<TvShow>)o;
        // PeopleDetail map = (PeopleDetail)o;
        i.putExtra(Search.RESULT, tvShows);
        Log.d("ORIENTED", "BROADCAST SENT");
        context.sendBroadcast(i);

    }
}
