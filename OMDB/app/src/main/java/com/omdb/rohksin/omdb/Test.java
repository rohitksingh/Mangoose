package com.omdb.rohksin.omdb;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.omdb.CustomViews.HalfHomeImage;
import com.omdb.rohksin.omdb.CustomViews.HalfHomePage;
import com.omdb.rohksin.omdb.NetworkRequests.MovieRequest;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.MovieNameURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.PeopleNameURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.URLBuilder;
import com.omdb.rohksin.omdb.QueryBuilder.QueryBuilder;

import org.json.JSONObject;

/**
 * Created by Illuminati on 5/14/2017.
 */
public class Test extends AppCompatActivity {

    private TextView responseText;


    @Override
    protected void onCreate(Bundle savedInstanceStste) {
        super.onCreate(savedInstanceStste);
        ///setContentView(R.layout.home);  USE THIS TO SEE RESPONSE

        setContentView(R.layout.main_search_page);
        hideTitle("Te");

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.searchBox);

        final EditText search = (EditText)layout.findViewById(R.id.search_movies);

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.d("Response", "Search");

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(search.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);


                    return true;
                }
                return false;
            }
        });













        /*
        responseText = (TextView)findViewById(R.id.responseCheck);
        RequestQueue queue = Volley.newRequestQueue(this);

        URLBuilder builder = new PeopleNameURLBuilder("Sunny Leone");
        String endPoint = builder.bulidURL();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, endPoint, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseText.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
     */

    }


    public void hideTitle(final String title)
    {
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        final CollapsingToolbarLayout layout = (CollapsingToolbarLayout)findViewById(R.id.title);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    layout.setTitle(title);
                    isShow = true;
                } else if (isShow) {
                    layout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }
}


