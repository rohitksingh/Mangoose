package com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl;

import com.omdb.rohksin.omdb.NewSearch.POJO.ActorDetail;
import com.omdb.rohksin.omdb.NewSearch.POJO.DetailMovie;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Illuminati on 6/22/2017.
 */
public class ActorDetailMapper implements ResponseMapper {

    private ActorDetail actorDetail;
    public static String ObjectMapped="com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ObjectMapped";

    @Override
    public void mapResponse(Object object) throws JSONException {

        actorDetail = new ActorDetail();

        JSONObject jsonObject = (JSONObject)object;

        actorDetail.setName(jsonObject.getString("name"));
        actorDetail.setId(jsonObject.getInt("id") + "");
        actorDetail.setBirthday(jsonObject.getString("birthday"));
        actorDetail.setBirthPlace(jsonObject.getString("place_of_birth"));
        actorDetail.setDeathday(jsonObject.getString("deathday"));
        actorDetail.setGender(jsonObject.getInt("gender") + "");
        actorDetail.setBiography(jsonObject.getString("biography"));
        actorDetail.setProfileImage(jsonObject.getString("profile_path"));
        actorDetail.setWebSite(jsonObject.getString("homepage"));

        //actorDetail.setImages();
        //actorDetail.setMovieRoles();
        //actorDetail.setTaggedImages();

    }

    @Override
    public Object objectMapped() {
        return actorDetail;
    }
}
