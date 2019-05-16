package com.omdb.rohksin.Mangoose.Redesign.Models;

/**
 * Created by Illuminati on 6/22/2017.
 */
public class Language {

    private String id;
    private String name;

    public Language(String name,String id) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
