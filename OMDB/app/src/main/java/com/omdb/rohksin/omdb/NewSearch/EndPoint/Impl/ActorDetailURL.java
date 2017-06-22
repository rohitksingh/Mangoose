package com.omdb.rohksin.omdb.NewSearch.EndPoint.Impl;

import com.omdb.rohksin.omdb.Constants.AppConstants;
import com.omdb.rohksin.omdb.NewSearch.EndPoint.EndPoint;
/**
 * Created by Illuminati on 6/22/2017.
 */
public class ActorDetailURL implements EndPoint {
    @Override
    public String buildEndPoint(Object object) {

        String peole_Id = (String)object;
        return AppConstants.PEOPLE_SEARCH_WITH_ID_BASEURL+peole_Id+"?api_key="+AppConstants.TMDB_KEY+AppConstants.PEOPLE_SEARCH_WITH_ID_BASEURL_SUFFIX;
    }
}
