package com.omdb.rohksin.omdb.ObjectOrientedSearch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.omdb.rohksin.omdb.ObjectOrientedSearch.Receivers.SearchListReceiver;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Impl.MovieSearch;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Impl.PeopleSearch;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Impl.TVShowSearch;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search;
import com.omdb.rohksin.omdb.R;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class SearchActivity extends AppCompatActivity {

    private TextView peopleButton;
    private TextView moviesButton;
    private TextView TvShow;
    private EditText seachEditText;
    private int SearchType =3;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seach_page_layout);

        seachEditText = (EditText)findViewById(R.id.search_movies);
        peopleButton = (TextView)findViewById(R.id.personSearch);
        moviesButton = (TextView)findViewById(R.id.movieSearch);
        TvShow = (TextView)findViewById(R.id.TVShowSearch);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Search.SEARCH_FINISHED);
        View parent = (View)findViewById(R.id.seachLayout);
        BroadcastReceiver receiver =  new SearchListReceiver(parent);
        registerReceiver(receiver,filter);

        seachEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId== EditorInfo.IME_ACTION_SEARCH)
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(seachEditText.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);

                    com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search search ;
                    if(SearchType==1) {
                        search = new PeopleSearch(seachEditText.getText() + "");
                        search.search(SearchActivity.this);
                    }
                    else if(SearchType==3)
                    {
                        search = new MovieSearch(seachEditText.getText() +"");
                        search.search(SearchActivity.this);
                    }
                    else
                    {
                        search = new TVShowSearch(seachEditText.getText() +"");
                        search.search(SearchActivity.this);
                    }

                    return true;
                }
                return false;
            }
        });

        peopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seachEditText.setHint("PEOPLE");
                SearchType = 1;
            }
        });
        moviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seachEditText.setHint("MOVIES");
                SearchType = 3;
            }
        });
        TvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seachEditText.setHint("TV SHOW");
                SearchType = 2;
            }
        });
    }

}
