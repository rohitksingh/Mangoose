package com.omdb.rohksin.omdb.QueryBuilder;

import com.omdb.rohksin.omdb.NewSearch.POJO.Actor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class SerializableObject implements Serializable {

    private List<Actor> objectList;

    public SerializableObject(List<Actor> objectList)
    {
        this.objectList = objectList;
    }

    public List<Actor> getObjectList()
    {
        return objectList;
    }

    public void setObjectList(List<Actor> objectList)
    {
        this.objectList = objectList;
    }

}
