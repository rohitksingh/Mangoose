package com.omdb.rohksin.Mangoose.SerializableCarriers;

import com.omdb.rohksin.Mangoose.Redesign.Models.Crew;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class SerializableCrewList implements Serializable{

    private List<Crew> objectList;

    public SerializableCrewList(List<Crew> objectList)
    {
        this.objectList = objectList;
    }

    public List<Crew> getObjectList()
    {
        return objectList;
    }

    public void setObjectList(List<Crew> objectList)
    {
        this.objectList = objectList;
    }

}
