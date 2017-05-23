package com.omdb.rohksin.omdb.NetworkRequests;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.omdb.MainActivity;
import com.omdb.rohksin.omdb.Movie;
import com.omdb.rohksin.omdb.ObjectMaps.MovieResponse;
import com.omdb.rohksin.omdb.SearchResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Illuminati on 5/22/2017.
 */
public class MovieRequest {

    public String endpoint;
    private JsonObjectRequest request;
    private RequestQueue requestQueue;
    private Context context;

    public MovieRequest(String endpoint,Context context)
    {
        this.endpoint = endpoint;
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public void makeRequest()
    {

        request=new JsonObjectRequest(Request.Method.GET, endpoint, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response)
            {

                //movie = new Movie();
                JSONObject object = response;

                Log.d("Res", object.toString());

                SearchResponse searchResponse = new SearchResponse();
                List<Movie> movies=null;

                try {


                    String responseSting = object.toString();

                    JSONArray movieList = object.getJSONArray("results");
                    movies = new ArrayList<Movie>();

                    for(int i=0;i<movieList.length();i++)
                    {
                        JSONObject movieObject = movieList.getJSONObject(i);

                        Movie movie = new Movie();
                        movie.setName((String) movieObject.get("title"));
                        movie.setReleaseYear((String) movieObject.get("release_date"));
                        movie.setPosterThumbnail( (movieObject.get("poster_path")).toString());
                        movies.add(movie);

                        //nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
                    }


                    for(int i=0;i<movies.size();i++)
                    {
                        Movie m = movies.get(i);
                        Log.d("Rohit ",(m.getPosterThumbnail()+"\n"));
                    }


                  Log.d("MovieRes",object.toString());

                    searchResponse.setList(movies);



                    //MovieResponse.setRatings();


                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    Log.d("Response", "inside finally");
                    Intent i = new Intent();
                    i.setAction(MainActivity.RESPONSE_RECEIVED);
                    //Log.d("Response", "mres is null? "+(movieResponse==null));
                    //i.putExtra("bak", movies);

                    i.putExtra("movieResponse",searchResponse);


                    context.sendBroadcast(i);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);

    }

}



