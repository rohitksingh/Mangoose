package com.omdb.rohksin.omdb.NewSearch.Utility;

import android.util.Log;

import com.omdb.rohksin.omdb.NewSearch.POJO.Actor;
import com.omdb.rohksin.omdb.NewSearch.POJO.Crew;
import com.omdb.rohksin.omdb.NewSearch.POJO.Genre;
import com.omdb.rohksin.omdb.NewSearch.POJO.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Illuminati on 6/17/2017.
 */
public class MovieUtils {

    public static String imageURL(String name)
    {
        return "http://image.tmdb.org/t/p/w185"+name;
    }

    public static String imageHighURL(String name)
    {
        return "http://image.tmdb.org/t/p/w500"+name;
    }

    public static String videoPath(String name)
    {
        return null;
    }

    public static ArrayList<String> getAllImages(Object object)
    {
        ArrayList<String> images = new ArrayList<String>();

        try {
        JSONObject jsonObject = (JSONObject)object;


        JSONArray array = jsonObject.getJSONArray("backdrops");

        for(int i=0;i<array.length();i++)
        {

                JSONObject imageObject = (JSONObject)array.get(i);

                String name = imageObject.get("file_path").toString();
                images.add(name);
        }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return images;
    }

    public static ArrayList<Video> getAllVideos(Object object)
    {
        ArrayList<Video> videos = new ArrayList<Video>();


        try {
            JSONObject jsonObject = (JSONObject) object;

            JSONArray array = jsonObject.getJSONArray("results");

            Log.d("ARRAY SIZE",array.length()+"");

            Video video;
            for(int i=0;i<array.length();i++)
            {
                 video = new Video();

                JSONObject videoObject = array.getJSONObject(i);
                video.setId((String)videoObject.get("id"));
                video.setKey((String) videoObject.get("key"));
                video.setName((String) videoObject.get("name"));
                video.setType((String) videoObject.get("type"));
                videos.add(video);
            }

        }
        catch (JSONException e)
        {

        }

        return videos;
    }

    public static ArrayList<Actor> getAllActors(JSONArray array)
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();

        try{
            for(int i=0;i<array.length();i++)
            {
                Actor actor = new Actor();
                JSONObject actorObject = (JSONObject)array.get(i);

                actor.setName((String) actorObject.get("name"));
                actor.setCharacterName((String) actorObject.get("character"));
                actor.setProfileImage(actorObject.get("profile_path") + "");
                actor.setId(actorObject.get("id").toString());

                actors.add(actor);
            }
        }
        catch (JSONException e)
        {

        }

        return actors;
    }

    public static ArrayList<Crew> getAllCrews(JSONArray array)
    {
        ArrayList<Crew> crews = new ArrayList<Crew>();

        try{
            for(int i=0;i<array.length();i++)
            {

                Crew crew = new Crew();
                JSONObject crewObject = (JSONObject)array.get(i);
                crew.setName((String)crewObject.get("name"));
                crew.setId(crewObject.get("id").toString());
                crew.setJob((String) crewObject.get("job"));
                crew.setProfileImage(crewObject.get("profile_path") + "");

                crews.add(crew);
            }
        }
        catch (JSONException e)
        {

        }

        return crews;
    }

    public static List<Genre> getAllGenres(JSONArray jsonArray)
    {
        Log.d("ARRAYJSON",(jsonArray==null)+" "+jsonArray.length());
        List<Genre> genres = new ArrayList<Genre>();

        try{
            for(int i=0;i<jsonArray.length();i++)
            {
                Genre genre = new Genre();
                JSONObject object = (JSONObject)jsonArray.get(i);
                genre.setId(object.getInt("id")+"");
                genre.setName(object.getString("name"));
                genres.add(genre);
            }
        }
        catch (JSONException e)
        {

        }

        return genres;
    }
}
