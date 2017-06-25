package com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Impl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.omdb.rohksin.omdb.NewSearch.POJO.DetailMovie;
import com.omdb.rohksin.omdb.NewSearch.POJO.PeopleDetail;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl.PeopleListMap;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.PeopleNameURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.URLBuilder;

import org.json.JSONException;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class PeopleSearch extends Search {


    private String searchTerm;
    //public Context context;


    public PeopleSearch(String searchTerm)
    {
        this.searchTerm = searchTerm;
        //this.context = context;
    }

    @Override
    public String getEndpoint() {
        URLBuilder builder = new PeopleNameURLBuilder(searchTerm);
        Log.d("URL",builder.bulidURL());
        return builder.bulidURL();
    }

    @Override
    public Object getResult(Object object) {

        ResponseMapper responseMapper = new PeopleListMap();

        Log.d("RESPONSE","3"+(object==null));
        try {
            responseMapper.mapResponse(object);
            Log.d("AAMIR",responseMapper.objectMapped()+"");
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
        i.putExtra("PEOPLE SEARCH", "PEOPLE");
        PeopleDetail map = (PeopleDetail)o;
        i.putExtra(Search.RESULT,map);
        Log.d("ORIENTED", "BROADCAST SENT");
        context.sendBroadcast(i);

    }
}
