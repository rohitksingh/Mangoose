package com.omdb.rohksin.Mangoose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Illuminati on 5/23/2017.
 */
public class SearchResponse implements Serializable {

    private List<Movie> list;

    public SearchResponse()
    {

    }

    public void setList(List<Movie> list)
    {
        this.list = list;
    }

    public List<Movie> getList()
    {
        return list;
    }
}
