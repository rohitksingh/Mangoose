package com.omdb.rohksin.Mangoose.Redesign.Search.ResponseMapper;

import org.json.JSONException;

/**
 * Created by Illuminati on 6/17/2017.
 */
public interface ResponseMapper {

    public void mapResponse(Object object) throws JSONException;

    public Object objectMapped();


}
