package com.omdb.rohksin.Mangoose;

import com.android.volley.RequestQueue;

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
