package com.omdb.rohksin.omdb.ObjectOrientedSearch;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.omdb.rohksin.omdb.NewSearch.POJO.PeopleDetail;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.Receivers.PeopleListReceiver;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Impl.PeopleSearch;
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
    private TextView responseObjserver;
    private int SearchType =3;
    //public View parent;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seach_page_layout);

        seachEditText = (EditText)findViewById(R.id.search_movies);
        peopleButton = (TextView)findViewById(R.id.personSearch);
        moviesButton = (TextView)findViewById(R.id.movieSearch);
        TvShow = (TextView)findViewById(R.id.TVShowSearch);
        responseObjserver = (TextView)findViewById(R.id.responseObjserver);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Search.SEARCH_FINISHED);
        View parent = (View)findViewById(R.id.seachLayout);
        BroadcastReceiver receiver =  new PeopleListReceiver(parent);  //new OrientedReceiver();
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


    private class OrientedReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context,Intent i)
        {
            if(i.getAction().equalsIgnoreCase(Search.SEARCH_FINISHED))
            {

                PeopleDetail peopleDetail = (PeopleDetail)i.getSerializableExtra(Search.RESULT);
                Toast.makeText(context,"Success"+peopleDetail.getName(),Toast.LENGTH_LONG).show();
                responseObjserver.setText(peopleDetail.getName());

            }
        }
    }

}
