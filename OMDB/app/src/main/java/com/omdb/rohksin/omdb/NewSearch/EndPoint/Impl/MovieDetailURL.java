package com.omdb.rohksin.omdb.NewSearch.EndPoint.Impl;

import com.omdb.rohksin.omdb.Constants.AppConstants;
import com.omdb.rohksin.omdb.NewSearch.EndPoint.EndPoint;

/**
 * Created by Illuminati on 6/17/2017.
 */
public class MovieDetailURL implements EndPoint {

    @Override
    public String buildEndPoint(Object object) {

        String movie_Id = (String)object;
        return "https://api.themoviedb.org/3/movie/"+movie_Id+"?api_key="+ AppConstants.TMDB_KEY+"&append_to_response=images,casts,videos";
    }
}
