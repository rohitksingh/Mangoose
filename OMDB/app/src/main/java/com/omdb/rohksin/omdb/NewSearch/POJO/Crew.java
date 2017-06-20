package com.omdb.rohksin.omdb.NewSearch.POJO;

import java.io.Serializable;

/**
 * Created by Illuminati on 6/17/2017.
 */
public class Crew implements Serializable{

    private String name;
    private String job;
    private String id;
    private String profileImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
