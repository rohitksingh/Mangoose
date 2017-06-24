package com.omdb.rohksin.omdb;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.omdb.CustomViews.HalfHomeImage;
import com.omdb.rohksin.omdb.CustomViews.HalfHomePage;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.PeopleNameURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.URLBuilder;
import com.omdb.rohksin.omdb.QueryBuilder.QueryBuilder;

import org.json.JSONObject;

/**
 * Created by Illuminati on 5/14/2017.
 */
public class Test extends AppCompatActivity {

    private TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceStste) {
        super.onCreate(savedInstanceStste);
        setContentView(R.layout.home);
        responseText = (TextView)findViewById(R.id.responseCheck);
        RequestQueue queue = Volley.newRequestQueue(this);

        URLBuilder builder = new PeopleNameURLBuilder("Sunny Leone");
        String endPoint = builder.bulidURL();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, endPoint, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseText.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);


    }
}


