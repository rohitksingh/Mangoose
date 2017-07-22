package com.omdb.rohksin.Mangoose.ObjectOrientedSearch.SearchAlgo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.omdb.rohksin.Mangoose.ObjectOrientedSearch.URLBuilders.URLBuilder;
import org.json.JSONObject;

/**
 * Created by Illuminati on 6/25/2017.
 */
public abstract class Search {

    public URLBuilder builder;
    public Context context;
    private JSONObject response;
    public static final String SEARCH_FINISHED ="com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search";
    public static final String RESULT ="com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search.RESULT";
    public static final String SEARCH_TYPE ="com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search.SEARCH_TYPE";

    public void search(Context context)
    {
        this.context = context;
        String endpoint = getEndpoint();
        getResults();
    }

    public abstract String  getEndpoint();


    public void getResults()
    {
        JSONObject jsonObject;

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, getEndpoint(), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response){


                Object o = getResult(response);
                processComplete(o);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    public abstract Object getResult(Object object);

    public abstract void processComplete(Object o);






}
