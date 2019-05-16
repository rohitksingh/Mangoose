package com.omdb.rohksin.Mangoose.Redesign.Models;

import java.io.Serializable;

/**
 * Created by Illuminati on 6/17/2017.
 */
public class Video implements Serializable{

    private String id;
    private String key;
    private String name;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
