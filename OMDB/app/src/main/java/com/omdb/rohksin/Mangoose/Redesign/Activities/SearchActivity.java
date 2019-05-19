package com.omdb.rohksin.Mangoose.Redesign.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Slide;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;
import com.omdb.rohksin.Mangoose.Redesign.Search.Receivers.SearchListReceiver;
import com.omdb.rohksin.Mangoose.Redesign.Search.SearchAlgo.Impl.MovieSearch;
import com.omdb.rohksin.Mangoose.Redesign.Search.SearchAlgo.Impl.PeopleSearch;
import com.omdb.rohksin.Mangoose.Redesign.Search.SearchAlgo.Impl.TVShowSearch;
import com.omdb.rohksin.Mangoose.Redesign.Search.SearchAlgo.Search;
import com.omdb.rohksin.Mangoose.R;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class SearchActivity extends AppCompatActivity {

    private TextView peopleButton;
    private TextView moviesButton;
    private TextView TvShowButton;
    private EditText seachEditText;

    private ImageView cancel;

    private int ACTIVE = Color.WHITE;
    private int INACTIVE = Color.LTGRAY;

    private String movieName="";
    private String tvShowName="";
    private String peopleName="";

    private static final int PEOPLE_SEARCH=1;
    private static final int TV_SHOW_SEARCH=2;
    private static final int MOVIE_SEARCH=3;

    private int previousSearchType=MOVIE_SEARCH;


    private int SearchType =MOVIE_SEARCH;

    private TextView shareThis;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.seach_page_layout);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_search);

        setAnimation();

        //BuildGenresListOnce Later Update it From Service Call
        MovieUtils.buildGenres();


        CardView layout = (CardView)findViewById(R.id.searchBox);
        RelativeLayout container =(RelativeLayout)layout.findViewById(R.id.edittextContainer);


        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
            }
        });


        seachEditText = (EditText)layout.findViewById(R.id.search_movies);

        shareThis = (TextView)findViewById(R.id.Share);

        shareThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieUtils.shareThisApp(SearchActivity.this);
            }
        });


        cancel = (ImageView)container.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seachEditText.setText("");
            }
        });
        peopleButton = (TextView)layout.findViewById(R.id.personSearch);
        moviesButton = (TextView)layout.findViewById(R.id.movieSearch);
        TvShowButton = (TextView)findViewById(R.id.TVShowSearch);

        seachEditText.setHintTextColor(Color.BLACK);

        defalutSearchType();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Search.SEARCH_FINISHED);
        View parent = (View)findViewById(R.id.parent);
        BroadcastReceiver receiver =  new SearchListReceiver(parent);
        registerReceiver(receiver, filter);


        seachEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId== EditorInfo.IME_ACTION_SEARCH)
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(seachEditText.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);

                    com.omdb.rohksin.Mangoose.Redesign.Search.SearchAlgo.Search search ;
                    if(SearchType==PEOPLE_SEARCH) {
                        search = new PeopleSearch(seachEditText.getText() + "");
                        startLoadingAnimation();
                        search.search(SearchActivity.this);
                    }
                    else if(SearchType==MOVIE_SEARCH)
                    {
                        search = new MovieSearch(seachEditText.getText() +"");
                        startLoadingAnimation();
                        search.search(SearchActivity.this);
                    }
                    else
                    {
                        search = new TVShowSearch(seachEditText.getText() +"");
                        startLoadingAnimation();
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
                seachEditText.setHint("Search People");
                SearchType = PEOPLE_SEARCH;
                indicateSearchType(SearchType);

                if(previousSearchType!=PEOPLE_SEARCH)
                {
                    saveSearchText(previousSearchType);
                    previousSearchType = PEOPLE_SEARCH;
                    seachEditText.setText(peopleName);
                    seachEditText.setSelection(seachEditText.getText().length());
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

            }
        });
        moviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seachEditText.setHint("Search Movies");
                SearchType = MOVIE_SEARCH;
                indicateSearchType(SearchType);

                if(previousSearchType!=MOVIE_SEARCH)
                {
                    saveSearchText(previousSearchType);
                    previousSearchType = MOVIE_SEARCH;
                    seachEditText.setText(movieName);
                    seachEditText.setSelection(seachEditText.getText().length());
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        });
        TvShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seachEditText.setHint("Search Tv Shows");
                SearchType = TV_SHOW_SEARCH;
                indicateSearchType(SearchType);

                if(previousSearchType!=TV_SHOW_SEARCH)
                {
                    saveSearchText(previousSearchType);
                    previousSearchType = TV_SHOW_SEARCH;
                    seachEditText.setText(tvShowName);
                    seachEditText.setSelection(seachEditText.getText().length());
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);


            }
        });
    }

    public void defalutSearchType()
    {
        seachEditText.setHint("Search Movies");
        SearchType = MOVIE_SEARCH;
        peopleButton.setTextColor(INACTIVE);
        TvShowButton.setTextColor(INACTIVE);
        moviesButton.setTextColor(ACTIVE);

    }

    public void startLoadingAnimation()
    {
        Animation loadingAnimation = new RotateAnimation(0.0f,360.0f,cancel.getPivotX(),cancel.getPivotY());
        loadingAnimation.setDuration(500);
        loadingAnimation.setRepeatCount(-1);
        cancel.setAnimation(loadingAnimation);
    }



    public void indicateSearchType(int SearchType)
    {
        if(SearchType==PEOPLE_SEARCH)
        {
            peopleButton.setTextColor(ACTIVE);
            TvShowButton.setTextColor(INACTIVE);
            moviesButton.setTextColor(INACTIVE);


        }else if(SearchType==TV_SHOW_SEARCH)
        {
            peopleButton.setTextColor(INACTIVE);
            TvShowButton.setTextColor(ACTIVE);
            moviesButton.setTextColor(INACTIVE);
        }
        else
        {
            peopleButton.setTextColor(INACTIVE);
            TvShowButton.setTextColor(INACTIVE);
            moviesButton.setTextColor(ACTIVE);
        }
    }


    public void setAnimation()
    {
        if(Build.VERSION.SDK_INT>20) {
            Slide slide = new Slide();
            slide.setDuration(2000);
            getWindow().setEnterTransition(slide);
        }
    }


    public void saveSearchText(int searchType)
    {
        if(searchType==MOVIE_SEARCH)
        {
            movieName = seachEditText.getText()+"";
        }
        else if(searchType==TV_SHOW_SEARCH)
        {
            tvShowName = seachEditText.getText()+"";
        }
        else {
            peopleName = seachEditText.getText()+"";
        }
    }



}
