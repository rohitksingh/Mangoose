package com.omdb.rohksin.Mangoose.NewSearch.Utility;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;

import com.omdb.rohksin.Mangoose.Constants.AppConstants;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.Actor;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.Crew;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.Genre;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.Video;
import com.omdb.rohksin.Mangoose.PreviewImageActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Illuminati on 6/17/2017.
 */
public class MovieUtils {


    public static final String PREVIEW_IMAGE = "com.omdb.rohksin.omdb.NewSearch.Utility.MovieUtils.PREVIEW_IMAGE";
    public static final String JSON_DATE_FORMAT = "yyyy-MM-dd";
    public static final String OUTPUT_DATE_FORMAT = "d MMM yyyy";
    public static final String SORTABLE_DATE_FORMAT = "yyyy-MM-dd";

    public static Map<String,String> genres = null;

    public static String imageURL(String name)
    {
        return "http://image.tmdb.org/t/p/w185"+name;
    }

    public static String imageHighURL(String name)
    {
        return "http://image.tmdb.org/t/p/w500"+name;
    }

    public static void previewImage(Context context,String imagePath)
    {
        Intent i = new Intent(context, PreviewImageActivity.class);
        i.putExtra(PREVIEW_IMAGE, imagePath);
        context.startActivity(i);

    }

    /// TODO Merge This function with the above one
    public static void previewImageWithAnimation(Context context,String imagePath,View view,String sharedElement)
    {
        Intent i = new Intent(context, PreviewImageActivity.class);
        i.putExtra(PREVIEW_IMAGE, imagePath);
        if(Build.VERSION.SDK_INT>20)
        {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)context,view,sharedElement);
            context.startActivity(i,options.toBundle());
        }
        else
        {
            context.startActivity(i);
        }

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
                video.setId((String) videoObject.get("id"));
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
                genre.setId(object.getInt("id") + "");
                genre.setName(object.getString("name"));
                genres.add(genre);
            }
        }
        catch (JSONException e)
        {

        }

        return genres;
    }

    public static String getFormattedDate(String fromJson){

        if(fromJson.length()<5)
        {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(JSON_DATE_FORMAT);
        Date d=null;
        try {
             d = sdf.parse(fromJson);
        }
        catch (ParseException e)
        {

        }
        sdf = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
        String ouput = sdf.format(d);
        return ouput;
    }

    public static String getSortedDate(String fromJson){

        //Log.d("DATE NULL ?", (fromJson==null)+"");
        if(fromJson==null || fromJson.equalsIgnoreCase("null"))
        {
            return "0";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(JSON_DATE_FORMAT);
        Date d=null;
        try {
            d = sdf.parse(fromJson);
        }
        catch (ParseException e)
        {

        }

        sdf = new SimpleDateFormat(SORTABLE_DATE_FORMAT);

        String ouput = sdf.format(d);
        return ouput;
    }

    public static void openInBrowser(Context context,String movieUrl)
    {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse(movieUrl));
        context.startActivity(i);

    }


    public static String getGenre(String[] genreIds)
    {
        StringBuilder builder = new StringBuilder();
        for(String id:genreIds)
        {
            builder.append(genres.get(id)+",");
        }

        String genres = new String(builder);
        return genres;
    }

    public static void shareThisApp(Context context)
    {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Mangoose");
            String shareMessage = "\nSearch about your favorite actors,movies and tv shows with Mangoose\n\n";
            shareMessage = shareMessage + AppConstants.APPSTORE_LINK;
            i.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(i, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }
    }


    public static void buildGenres()
    {
        genres = new LinkedHashMap<String,String>();
        genres.put("28","Action");
        genres.put("12","Adventure");
        genres.put("16","Animation");
        genres.put("35","Comedy");
        genres.put("80","Crime");
        genres.put("99","Documentary");
        genres.put("18","Drama");
        genres.put("10751","Family");
        genres.put("14","Fantasy");
        genres.put("36","History");
        genres.put("27","Horror");
        genres.put("10402","Music");
        genres.put("9648","Mystery");
        genres.put("10749","Romance");
        genres.put("878","Science Fiction");
        genres.put("10770","TV Movie");
        genres.put("53","Thriller");
        genres.put("10752","War");
        genres.put("37","Western");

    }


}
