package com.omdb.rohksin.omdb.NewSearch.POJO;

import com.omdb.rohksin.omdb.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class PeopleDetail implements Serializable{

    private String name;
    private String id;
    private String peofileImage;
    private List<Movie> knownFor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeofileImage() {
        return peofileImage;
    }

    public void setPeofileImage(String peofileImage) {
        this.peofileImage = peofileImage;
    }

    public List<Movie> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<Movie> knownFor) {
        this.knownFor = knownFor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
