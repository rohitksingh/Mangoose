package com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl;

import com.omdb.rohksin.omdb.Constants.Config;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.URLBuilder;

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
        return Config.BASE_URL;
    }

    @Override
    public String getAPIKEY()
    {
        return "api_key="+Config.MAIN_API_KEY;
    }

}
