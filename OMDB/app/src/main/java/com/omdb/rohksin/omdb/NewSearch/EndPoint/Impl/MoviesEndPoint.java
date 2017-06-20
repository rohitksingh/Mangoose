package com.omdb.rohksin.omdb.NewSearch.EndPoint.Impl;

import com.omdb.rohksin.omdb.Constants.AppConstants;
import com.omdb.rohksin.omdb.NewSearch.EndPoint.EndPoint;

/**
 * Created by Illuminati on 6/12/2017.
 */
public class MoviesEndPoint implements EndPoint {
    @Override
    public String buildEndPoint(Object object) {
        String searchText = (String)object;
        String endPointUrl = AppConstants.BASE_URL+"search/movie?api_key="+AppConstants.TMDB_KEY+"&query="+searchText;
        return endPointUrl;
    }
}
