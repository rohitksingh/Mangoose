package com.omdb.rohksin.Mangoose.NewSearch.ResponseMapper.Impl;

import android.util.Log;

import com.omdb.rohksin.Mangoose.Movie;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.PeopleDetail;
import com.omdb.rohksin.Mangoose.NewSearch.ResponseMapper.ResponseMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class PeopleListMap implements ResponseMapper {

    private ArrayList<PeopleDetail> peopleDetails;

    @Override
    public void mapResponse(Object object) throws JSONException {

        JSONObject jsonObject = (JSONObject)object;

        JSONArray peoplelist = jsonObject.getJSONArray("results");

        peopleDetails = new ArrayList<PeopleDetail>();

        for(int i=0;i<peoplelist.length();i++)
        {
            PeopleDetail detail = new PeopleDetail();
            JSONObject actor = (JSONObject)peoplelist.get(i);

            detail.setName(actor.getString("name"));
            detail.setId(actor.getInt("id") + "");
            detail.setPeofileImage(actor.getString("profile_path"));
            JSONArray knownfor = actor.getJSONArray("known_for");

            Log.d("KNOWN FOR",detail.getName() +knownfor.length()+"");

            int top3 = 3;
            if(knownfor.length()<3)
            top3 = knownfor.length();

            List<Movie> movies = new ArrayList<Movie>();

            StringBuilder builder = new StringBuilder();

            for(int j=0;j<knownfor.length();j++)
            {

                if(knownfor.getJSONObject(j)!=null) {


                    JSONObject movieObject = knownfor.getJSONObject(j);

                    if(movieObject.getString("media_type").equals("tv")) {
                        builder.append(movieObject.getString("original_name") + ",");
                    }
                    else {
                        builder.append(movieObject.getString("original_title") + ",");
                    }
                }
            }

            detail.setKnownForMovies(new String(builder));

            /*
        //TODO INTEGRATE KNOWN FOR



            for(int j=0;j<top3;j++)
            {
                Log.d("ACTORS DETAIL",i+"");
                JSONObject movieObject = (JSONObject)knownfor.get(j);

                Log.d("ACTORS DETAIL null",(movieObject==null)+"");

                Movie movie = new Movie();
                movie.setName(movieObject.getString("title"));
                movie.setMovieId(movieObject.getInt("id") + "");
                movie.setPosterThumbnail(movieObject.getString("poster_path"));
                movies.add(movie);
            }

            */

            Log.d("OBJECT DETAIL",detail.getName()+"\n"+detail.getId()+"\n"+detail.getPeofileImage()+"\n"+detail.getKnownForMovies());

            peopleDetails.add(detail);
        }

    }

    @Override
    public Object objectMapped() {
        Log.d("LISTSIZE",peopleDetails.size()+"");
        return peopleDetails;
    }
}
