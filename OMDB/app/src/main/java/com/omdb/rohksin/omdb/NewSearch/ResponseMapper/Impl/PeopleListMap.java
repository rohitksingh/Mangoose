package com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl;

import android.util.Log;

import com.omdb.rohksin.omdb.NewSearch.POJO.PeopleDetail;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class PeopleListMap implements ResponseMapper {

    private PeopleDetail peopleDetail;
    private List<PeopleDetail> peopleDetails;

    public PeopleListMap()
    {
        peopleDetail = new PeopleDetail();

    }

    @Override
    public void mapResponse(Object object) throws JSONException {

        Log.d("OBJECT",(object==null)+"1");

        JSONObject jsonObject = (JSONObject)object;

        JSONArray peoplelist = jsonObject.getJSONArray("results");

        JSONObject actor = (JSONObject)peoplelist.get(0);

        Log.d("OBJECT",(actor==null)+"2");
        Log.d("OBJECT1","ABC");
        peopleDetail.setName(actor.getString("name"));
        Log.d("OBJECT1", "DEF");

        Log.d("OBJECT", (actor.getString("name")) + "xxx");
    }

    @Override
    public Object objectMapped() {
        Log.d("AAMIR",peopleDetail.getName());
        return peopleDetail;
    }
}
