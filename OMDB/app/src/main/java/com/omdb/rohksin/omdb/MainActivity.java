package com.omdb.rohksin.omdb;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.omdb.ObjectMaps.MovieResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    //private TextView desc;
    private EditText search;
    private JsonObjectRequest request;
    private RequestQueue requestQueue;
    public static Movie movie;
    private MovieResponse movieResponse;

    public static final String RESPONSE_RECEIVED ="com.omdb.rohksin.omdb.Response";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        IntentFilter filter = new IntentFilter();
        filter.addAction(RESPONSE_RECEIVED);
        View v = (View)findViewById(R.id.parent);
        Log.d("Response","reg");
        registerReceiver(new ResposeObserver(v), filter);
        Log.d("Response", "after reg");

        //desc = (TextView)findViewById(R.id.description);
        search = (EditText)findViewById(R.id.search);

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId== EditorInfo.IME_ACTION_SEARCH)
                {
                    Log.d("Response", "Search");
                    Search searchMovie =new Search(search.getText()+"");
                    searchMovie.formUrl();
                    searchMovie.makeRequest();
                    return true;
                }
                return false;
            }
        });

        requestQueue= Volley.newRequestQueue(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                Log.d("Response", "Search");
                Search searchMovie =new Search(search.getText()+"");
                searchMovie.formUrl();
                searchMovie.makeRequest();




            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public class Search {

        String searchText;
        String endPoint;


        public Search(String searchText)
        {
            this.searchText = searchText;

        }

        public void formUrl()
        {
            String properSearchText = searchText.replace(" ","%20");
            endPoint = "http://www.omdbapi.com/?t="+properSearchText;//+"&plot=full";

        }

        public void makeRequest()
        {


            String response1;

            Log.d("Rohit",endPoint);


            request=new JsonObjectRequest(Request.Method.GET, endPoint, new Response.Listener<JSONObject>(){

                @Override
                public void onResponse(JSONObject response)
                {

                    //movie = new Movie();
                    JSONObject object = response;

                    Log.d("Res",object.toString());

                    try {
                       /* movie.setName((String) response.get("Title"));
                        movie.setReleaseYear((String) response.get("Year"));
                        movie.setPosterThumbnail((String) response.get("Poster"));
                        */

                        Log.d("Response","json res rec");
                        movieResponse = new MovieResponse();

                        Log.d("Response", "mres obj created");
                        movieResponse.setTitle((String) response.get("Title"));
                        movieResponse.setYear((String) response.get("Year"));
                        movieResponse.setPoster((String) response.get("Poster"));
                        movieResponse.setReleasedDate((String) response.get("Released"));
                        movieResponse.setGenre((String) response.get("Genre"));
                        movieResponse.setRated((String) response.get("Rated"));
                        movieResponse.setRuntime((String) response.get("Runtime"));
                        movieResponse.setImdbRating((String) response.get("imdbRating"));
                        movieResponse.setDirector((String) response.get("Director"));
                        movieResponse.setWriter((String) response.get("Writer"));
                        movieResponse.setProduction((String) response.get("Production"));
                        movieResponse.setBoxoffice((String) response.get("BoxOffice"));
                        movieResponse.setWebsite((String) response.get("Website"));
                        movieResponse.setAwards((String) response.get("Awards"));
                        movieResponse.setCountry((String) response.get("Country"));
                        movieResponse.setLanguages((String) response.get("Language"));
                        movieResponse.setFullPlot((String) response.get("Plot"));


                        JSONArray jsonArray = (JSONArray)response.get("Ratings");
                        Log.d("Rohit", "jsonArray" + (jsonArray == null));

                        Map<String,String> ratingMap = new LinkedHashMap<String, String>();

                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                            ratingMap.put((String)jsonObject.get("Source"),(String)jsonObject.get("Value"));
                        }

                        movieResponse.setRatings(ratingMap);

                        //ArrayList<String> actors= getAll((String)response.get("Actors"));
                        movieResponse.setActors((String)response.get("Actors"));










                        //MovieResponse.setRatings();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finally {
                        Log.d("Response","inside finally");
                        Intent i = new Intent();
                        i.setAction(RESPONSE_RECEIVED);
                        Log.d("Response", "mres is null? "+(movieResponse==null));
                        i.putExtra("bak",movieResponse);
                        sendBroadcast(i);
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

}
