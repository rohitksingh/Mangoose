package com.omdb.rohksin.Mangoose.Redesign.Search.SearchAlgo.Impl;

import android.content.Intent;

import com.omdb.rohksin.Mangoose.Redesign.Models.PeopleDetail;
import com.omdb.rohksin.Mangoose.Redesign.Search.ResponseMapper.Impl.PeopleListMap;
import com.omdb.rohksin.Mangoose.Redesign.Search.ResponseMapper.ResponseMapper;
import com.omdb.rohksin.Mangoose.Redesign.Search.SearchAlgo.Search;
import com.omdb.rohksin.Mangoose.Redesign.URLBuilders.Impl.PeopleNameURLBuilder;
import com.omdb.rohksin.Mangoose.Redesign.URLBuilders.URLBuilder;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class PeopleSearch extends Search {

    private String searchTerm;

    public PeopleSearch(String searchTerm)
    {
        this.searchTerm = searchTerm;
    }

    @Override
    public String getEndpoint() {

        URLBuilder builder = new PeopleNameURLBuilder(searchTerm);
        return builder.bulidURL();

    }

    @Override
    public Object getResult(Object object) {

        ResponseMapper responseMapper = new PeopleListMap();

        try {

            responseMapper.mapResponse(object);
            return responseMapper.objectMapped();
        }
        catch (JSONException e)
        {

        }
        return null;
    }

    @Override
    public void processComplete(Object o) {
        Intent i = new Intent();
        i.setAction(Search.SEARCH_FINISHED);
        i.putExtra(Search.SEARCH_TYPE, "PEOPLE");
        ArrayList<PeopleDetail> peopleDetails = (ArrayList<PeopleDetail>)o;
        i.putExtra(Search.RESULT, peopleDetails);
        context.sendBroadcast(i);

    }
}
