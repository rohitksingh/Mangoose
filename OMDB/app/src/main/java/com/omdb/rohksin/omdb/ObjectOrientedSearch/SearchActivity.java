package com.omdb.rohksin.omdb.ObjectOrientedSearch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.transition.Slide;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.omdb.rohksin.omdb.NewSearch.Utility.MovieUtils;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.Receivers.SearchListReceiver;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Impl.MovieSearch;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Impl.PeopleSearch;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Impl.TVShowSearch;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search;
import com.omdb.rohksin.omdb.R;

import java.util.LinkedHashMap;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class SearchActivity extends AppCompatActivity {

    private TextView peopleButton;
    private TextView moviesButton;
    private TextView TvShowButton;
    private EditText seachEditText;

    private ImageView cancel;

    private int SearchType =3;

    private int ACTIVE = Color.WHITE;
    private int INACTIVE = Color.LTGRAY;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.seach_page_layout);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.main_search_page);

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

                    com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search search ;
                    if(SearchType==1) {
                        search = new PeopleSearch(seachEditText.getText() + "");
                        startLoadingAnimation();
                        search.search(SearchActivity.this);
                    }
                    else if(SearchType==3)
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
                SearchType = 1;
                indicateSearchType(SearchType);
            }
        });
        moviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seachEditText.setHint("Search Movies");
                SearchType = 3;
                indicateSearchType(SearchType);
            }
        });
        TvShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seachEditText.setHint("Search Tv Shows");
                SearchType = 2;
                indicateSearchType(SearchType);
            }
        });
    }

    public void defalutSearchType()
    {
        seachEditText.setHint("Search Movies");
        SearchType = 3;
        peopleButton.setTextColor(INACTIVE);
        TvShowButton.setTextColor(INACTIVE);
        moviesButton.setTextColor(ACTIVE);

    }

    public void startLoadingAnimation()
    {
        Animation anim = new RotateAnimation(0.0f,360.0f,cancel.getPivotX(),cancel.getPivotY());
        anim.setDuration(500);
        anim.setRepeatCount(-1);
        cancel.setAnimation(anim);
    }

    public void indicateSearchType(int SearchType)
    {
        if(SearchType==1)
        {
            peopleButton.setTextColor(ACTIVE);
            TvShowButton.setTextColor(INACTIVE);
            moviesButton.setTextColor(INACTIVE);

        }else if(SearchType==2)
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

}
