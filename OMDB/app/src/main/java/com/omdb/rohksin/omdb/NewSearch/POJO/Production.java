package com.omdb.rohksin.omdb.NewSearch.POJO;

import java.io.Serializable;

/**
 * Created by Illuminati on 6/17/2017.
 */
public class Production implements Serializable{

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
