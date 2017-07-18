package com.omdb.rohksin.Mangoose.ObjectOrientedSearch.URLBuilders.Impl;

import com.omdb.rohksin.Mangoose.Constants.Config;
import com.omdb.rohksin.Mangoose.ObjectOrientedSearch.URLBuilders.URLBuilder;

/**
 * Created by Illuminati on 6/24/2017.
 */
public  abstract class SearchIDURLBuilder implements URLBuilder {


    @Override
    public String bulidURL() {
        return getBaseURL() + getSpecific() + getSeachTerm() +getAPIKEY() + getQueryParams();
    }

    @Override
    public String getBaseURL() {
        return Config.BASE_URL;
    }

    @Override
    public String getAPIKEY() {
        return "api_key="+ Config.MAIN_API_KEY;
    }

}
