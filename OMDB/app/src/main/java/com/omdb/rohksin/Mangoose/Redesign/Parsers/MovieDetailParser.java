package com.omdb.rohksin.Mangoose.Redesign.Parsers;

import android.util.Log;

import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Movie;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

public class MovieDetailParser implements BasicParser {

    private static final String TAG = "MovieDetailParser";

    @Override
    public Object parse(String jsonString) {

        Log.d(TAG, "IN: ");

        Movie movie = null;

        Moshi moshi = new Moshi.Builder()
                .build();

        JsonAdapter<Movie> jsonAdapter = moshi.adapter(Movie.class);

        try {
            movie = jsonAdapter.fromJson(jsonString);

        }catch (IOException e)
        {
            Log.d(TAG, "EXCEPTION");
        }


        return movie;
    }
}
