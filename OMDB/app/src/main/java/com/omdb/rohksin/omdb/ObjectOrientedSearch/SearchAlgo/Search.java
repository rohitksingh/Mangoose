package com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.MappedObjects.MappedObject;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.URLBuilder;
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

    public void search(Context context)
    {
        this.context = context;
        String endpoint = getEndpoint();
        getResults();
        //JSONObject response = getResults();
        Log.d("OBJECT RESULT",(context == null)+"");

    }

    public abstract String  getEndpoint();


    public void getResults()
    {
         JSONObject jsonObject;

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, getEndpoint(), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response){
                Log.d("ZZZ",response.toString());

                //////Start checking from here
                Log.d("RESPONSE","1 "+(response==null));
                Object o = getResult(response);
                Log.d("RESPONSE","2 "+(o==null));
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
