package com.omdb.rohksin.omdb;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.omdb.NetworkRequests.MovieRequest;
import com.omdb.rohksin.omdb.ObjectMaps.MovieResponse;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.MovieNameURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.URLBuilder;

/**
 * Created by Illuminati on 5/22/2017.
 */
public class SearchActivity extends AppCompatActivity{


    private EditText search;
    private RecyclerView moviesRecyclerView;


    private JsonObjectRequest request;
    private RequestQueue requestQueue;
    public static Movie movie;
    private MovieResponse movieResponse;

    public static final String RESPONSE_RECEIVED ="com.omdb.rohksin.omdb.Response";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.movie_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        IntentFilter filter = new IntentFilter();
        filter.addAction(RESPONSE_RECEIVED);
        View v = (View)findViewById(R.id.parent);
        Log.d("Response", "reg");
        registerReceiver(new SearchResponseObserver(v), filter);
        Log.d("Response", "after reg");

        //desc = (TextView)findViewById(R.id.description);
        search = (EditText)findViewById(R.id.search_movies);
        moviesRecyclerView = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        moviesRecyclerView.setLayoutManager(llm);
        //Data data =new Data();
        //provide list


        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId== EditorInfo.IME_ACTION_SEARCH)
                {
                    Log.d("Response", "Search");
                   // Search searchMovie =new Search(search.getText()+"");
                   //// searchMovie.formUrl();
                   // searchMovie.makeRequest();

                    /*
                          Pass text
                          Make Endpoint
                          Send a Volley Request

                     */

                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(search.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);

                    //QueryBuilder builder = new SimpleMovieQuery(search.getText()+"");
                    //String endpoint = builder.formUrl();
                    URLBuilder urlBuilder = new MovieNameURLBuilder(search.getText()+"");
                    String endpoint = urlBuilder.bulidURL();

                    Log.d("endpoint", endpoint);
                    new MovieRequest(endpoint,SearchActivity.this).makeRequest();

                    Log.d("MovieRes","Should be at end");
                   // RvAdapter adapter = new RvAdapter(data.getData());
                   // moviesRecyclerView.setAdapter(adapter);




                    return true;
                }
                return false;
            }
        });

        requestQueue= Volley.newRequestQueue(this);



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




}
