package com.omdb.rohksin.Mangoose.Redesign.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 6/22/2017.
 */
public class ActorDetail {

    private String name;
    private String id;
    private String biography;
    private String birthday;
    private String deathday;
    private String birthPlace;
    private String profileImage;
    private String gender;
    private String webSite;
    private List<MovieRole> movieRoles;
    private ArrayList<String> images;
    private ArrayList<String> taggedImages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public List<MovieRole> getMovieRoles() {
        return movieRoles;
    }

    public void setMovieRoles(List<MovieRole> movieRoles) {
        this.movieRoles = movieRoles;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public List<String> getTaggedImages() {
        return taggedImages;
    }

    public void setTaggedImages(ArrayList<String> taggedImages) {
        this.taggedImages = taggedImages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString()
    {
        return  this.name +"\n"+this.id+"\n"+this.birthday+"\n"+this.deathday+"\n"+this.birthPlace+"\n"+this.biography;
    }
}
