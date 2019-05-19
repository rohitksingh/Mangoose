package com.omdb.rohksin.Mangoose.Redesign.URLBuilders.Impl;

import com.omdb.rohksin.Mangoose.Redesign.URLBuilders.URLBuilder;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.AppConstants;

/**
 * Created by Illuminati on 6/24/2017.
 */
public abstract class SearchNameURLBuilder implements URLBuilder{

    @Override
    public String bulidURL()
    {
        return getBaseURL()+ getSpecific() + getAPIKEY() + getSeachTerm() +getQueryParams();
    }

    @Override
    public String getBaseURL()
    {
        return AppConstants.BASE_URL;
    }

    @Override
    public String getAPIKEY()
    {
        return "api_key="+AppConstants.TMDB_KEY;
    }

}
