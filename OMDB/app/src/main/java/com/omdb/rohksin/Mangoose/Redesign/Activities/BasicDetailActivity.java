package com.omdb.rohksin.Mangoose.Redesign.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.Mangoose.ObjectOrientedSearch.URLBuilders.URLBuilder;

import org.json.JSONObject;

public abstract class BasicDetailActivity extends AppCompatActivity {



    public URLBuilder urlBuilder;
    public String endPoint;
    public String objectId;

    private static final String TAG = "BasicDetailActivity";


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getMainLayout());
        createObjectID();
        createEndPoint();
        makeRequest();
    }

    private void createEndPoint()
    {
        urlBuilder = getUrlBuilder();
        endPoint = urlBuilder.bulidURL();
    }

    private void createObjectID()
    {
         String key = getObjectId();
         objectId = getIntent().getStringExtra(key);
    }

    private void makeRequest()
    {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, endPoint,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d(TAG, "onResponse: "+ endPoint);
                        Log.d(TAG, "onResponse: "+ response.toString() );

                        parseObject(response.toString());
                        //sendBroadcast();
                        createUI();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );

        queue.add(request);
    }


    public abstract URLBuilder getUrlBuilder();

    public abstract String getObjectId();

    public abstract void parseObject(String jsonString);

    public abstract void createUI();

    public abstract int getMainLayout();

}
