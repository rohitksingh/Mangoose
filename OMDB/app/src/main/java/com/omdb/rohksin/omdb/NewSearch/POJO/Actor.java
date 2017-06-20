package com.omdb.rohksin.omdb.NewSearch.POJO;

import java.io.Serializable;

/**
 * Created by Illuminati on 6/17/2017.
 */
public class Actor implements Serializable{

    private String name;
    private String id;
    private String characterName;
    private String profileImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
