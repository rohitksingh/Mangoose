package com.omdb.rohksin.omdb;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Illuminati on 5/7/2017.
 */
public class Search {

    String searchText;
    String endPoint;
    private RequestQueue requestQueue;

    public Search(String searchText) {
        this.searchText = searchText;
    }
}
