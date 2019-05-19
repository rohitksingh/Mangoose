package com.omdb.rohksin.Mangoose.Redesign.URLBuilders;

/**
 * Created by Illuminati on 6/24/2017.
 */
public interface URLBuilder {

    public String bulidURL();

    public String getBaseURL();

    public String getSpecific();

    public String getAPIKEY();

    public String getSeachTerm();

    public String getQueryParams();

}
