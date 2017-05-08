package com.omdb.rohksin.omdb;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //private TextView desc;
    private EditText search;
    private JsonObjectRequest request;
    private RequestQueue requestQueue;
    public static Movie movie;

    public static final String RESPONSE_RECEIVED ="com.omdb.rohksin.omdb.Response";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        IntentFilter filter = new IntentFilter();
        filter.addAction(RESPONSE_RECEIVED);
        View v = (View)findViewById(R.id.parent);
        registerReceiver(new ResposeObserver(v), filter);

        //desc = (TextView)findViewById(R.id.description);
        search = (EditText)findViewById(R.id.search);

        requestQueue= Volley.newRequestQueue(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


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
            endPoint = "http://www.omdbapi.com/?t="+properSearchText;

        }

        public void makeRequest()
        {


            String response1;

            Log.d("Rohit",endPoint);


            request=new JsonObjectRequest(Request.Method.GET, endPoint, new Response.Listener<JSONObject>(){

                @Override
                public void onResponse(JSONObject response)
                {

                    movie = new Movie();
                    JSONObject object = response;

                    try {
                        movie.setName((String) response.get("Title"));
                        movie.setReleaseYear((String) response.get("Year"));
                        movie.setPosterThumbnail((String) response.get("Poster"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finally {
                        sendBroadcast(new Intent().setAction(RESPONSE_RECEIVED));
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
