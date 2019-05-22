package com.omdb.rohksin.Mangoose.Redesign.Parsers;

import android.util.Log;

import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Actor;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

public class PeopleDetailParser implements BasicParser {

    private static final String TAG = "PeopleDetailParser";

    private Actor actor = null;

    @Override
    public Object parse(String jsonString) {

        Moshi moshi = new Moshi.Builder()
                .build();

        JsonAdapter<Actor> jsonAdapter = moshi.adapter(Actor.class);

        try {
            actor = jsonAdapter.fromJson(jsonString);
            Log.d(TAG, "Parsing success: ");
        }
        catch (IOException e)
        {
            Log.d(TAG, "Parsing Failed: ");
        }
        return actor;
    }
}
