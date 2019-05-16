package com.omdb.rohksin.Mangoose.Redesign.Activities;

import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.Mangoose.PeopleDetailActivity;
import com.omdb.rohksin.Mangoose.R;
import com.omdb.rohksin.Mangoose.Redesign.Adapters.ActorsListAdapter;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Backdrop;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Cast;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Crew;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Genre;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Movie;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.AppConstants;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;
import com.omdb.rohksin.Mangoose.ObjectOrientedSearch.URLBuilders.Impl.MovieIDURLBuilder;
import com.omdb.rohksin.Mangoose.ObjectOrientedSearch.URLBuilders.URLBuilder;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Illuminati on 6/17/2017.
 *
 * Common Tasks in Activity
 * 1) Getting Movie Id
 * 2) Creating url
 * 3) Make VolleyRequest
 * 4) Parse Response
 * 5) Listen for Response
 * 6) Map to POJO
 *
 */



public class MovieDetailActivity extends AppCompatActivity {

    private Movie movie;
    public static String OBJECTMAPPED ="com.omdb.rohksin.omdb.MovieDetailActivity.ObjectMapped";
    public static String MOVIE_LIST ="com.omdb.rohksin.omdb.MovieDetailActivity.MovieList";

    private CollapsingToolbarLayout layout;

    private static final String TAG = "MovieDetailActivity";

    @Override
    protected void onCreate(Bundle saveBundleInstance)
    {
        super.onCreate(saveBundleInstance);
        setContentView(R.layout.blank_activity);
        Intent i = getIntent();
        final String movieId = i.getStringExtra(AppConstants.MOVIE_ID);

        CoordinatorLayout parent = (CoordinatorLayout)findViewById(R.id.parent);
        layout = (CollapsingToolbarLayout)findViewById(R.id.title);

        URLBuilder urlBuilder = new MovieIDURLBuilder(movieId);
        final String end = urlBuilder.bulidURL();
        BroadcastReceiver receiver = new DetailMovieReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(OBJECTMAPPED);
        registerReceiver(receiver, filter);

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, end,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d(TAG, "onResponse: "+ end);
                        Log.d(TAG, "onResponse: "+ response.toString() );




                        //ResponseMapper mapper = new DetailMovieMapper();
                        //mapper.mapResponse(response);
                        //movie = (DetailMovie)mapper.objectMapped();
                        getMovieObject(response.toString());
                        sendBroadcast();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );

        queue.add(request);

    }

    public void sendBroadcast(){
        Intent i = new Intent();
        i.setAction(OBJECTMAPPED);
        sendBroadcast(i);
    }

    private void getMovieObject(String jsonString) {

        Log.d(TAG, "IN: ");
        Moshi moshi = new Moshi.Builder()
                .build();

        JsonAdapter<Movie> jsonAdapter = moshi.adapter(Movie.class);

        try {
            movie = jsonAdapter.fromJson(jsonString);

        }catch (IOException e)
        {
            Log.d(TAG, "EXCEPTION");
        }

    }



    private class DetailMovieReceiver extends BroadcastReceiver
    {

        private Context context;

        @Override
        public void onReceive(Context context,Intent intent)
        {
            if(intent.getAction().equalsIgnoreCase(MovieDetailActivity.OBJECTMAPPED))
            {

                this.context  = context;

                hideTitle(movie.title);

                ImageView imageView = (ImageView)findViewById(R.id.moviePoster);
                final ImageView poster = (ImageView)layout.findViewById(R.id.moviePosterThumbnail);

                if(Build.VERSION.SDK_INT>20) {
                    poster.setTransitionName("ImageView");
                }

                String imgSrc = MovieUtils.imageHighURL(movie.backdrop_path);
                String imgSrc1 = MovieUtils.imageURL(movie.poster_path);


                Picasso.with(context)
                        .load(imgSrc)
                        .into(imageView);

                Picasso.with(context)
                        .load(imgSrc1)
                        .into(poster);

                poster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MovieUtils.previewImageWithAnimation(MovieDetailActivity.this,movie.poster_path ,poster,"ImageView");
                    }
                });

                createOverViewSection();
                createImageSection();
                createActorsSection();
                createCrewSection();
                createAboutSection();

            }
        }

        public void hideTitle(final String title)
        {
            AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
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


        public void createOverViewSection()
        {
            CardView overViewCard = (CardView)findViewById(R.id.overview);
            TextView movieName = (TextView)overViewCard.findViewById(R.id.movie_name);
            TextView OverViewText = (TextView)overViewCard.findViewById(R.id.OverViewText);
            movieName.setText(movie.title);
            if(!movie.overview.equalsIgnoreCase("null")) {
                overViewCard.setVisibility(View.VISIBLE);
                OverViewText.setText(movie.overview);
            }


        }


        public void createImageSection()
        {

            FrameLayout top3Images = (FrameLayout)findViewById(R.id.top3Images);
            LinearLayout imageContainer = (LinearLayout)top3Images.findViewById(R.id.imageContainer);

            List<Backdrop> backdrops = movie.images.backdrops;

            ImageView image1 = (ImageView)imageContainer.findViewById(R.id.image1);
            LinearLayout imageContainer1 = (LinearLayout)imageContainer.findViewById(R.id.imageContainer1);
            ImageView image2 = (ImageView)imageContainer1.findViewById(R.id.image2);
            ImageView image3 = (ImageView)imageContainer1.findViewById(R.id.image3);


            if(backdrops.size()>2) {
                String imgsrc1 = MovieUtils.imageHighURL(backdrops.get(0).file_path);
                String imgsrc2 = MovieUtils.imageHighURL(backdrops.get(1).file_path);
                String imgsrc3 = MovieUtils.imageHighURL(backdrops.get(2).file_path);

                Picasso.with(context)
                        .load(imgsrc1)
                        .into(image1);
                Picasso.with(context)
                        .load(imgsrc2)
                        .into(image2);

                Picasso.with(context)
                        .load(imgsrc3)
                        .into(image3);
            }
            else {
                top3Images.setVisibility(View.GONE);
            }
            TextView viewMore = (TextView)top3Images.findViewById(R.id.viewMore);
            viewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(MovieDetailActivity.this, AllImageActivity.class);
                    i.putExtra(MOVIE_LIST, (Serializable)movie.images.backdrops);
                    if(Build.VERSION.SDK_INT>20)
                    {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MovieDetailActivity.this);
                        startActivity(i,options.toBundle());
                    }
                    else {
                        startActivity(i);
                    }



                }

            });



        }

        public void createActorsSection()
        {


            final List<Cast> actors = movie.casts.cast;

            CardView layout = (CardView)findViewById(R.id.top3Actors);

            if(actors.size()>2) {

                layout.setVisibility(View.VISIBLE);

                LinearLayout layout1 = (LinearLayout) layout.findViewById(R.id.movie_cast);

                LinearLayout actorCard1 = (LinearLayout) layout1.findViewById(R.id.actor1);
                LinearLayout actorCard2 = (LinearLayout) layout1.findViewById(R.id.actor2);
                LinearLayout actorCard3 = (LinearLayout) layout1.findViewById(R.id.actor3);

                //LinearLayout actorHolder1 = (LinearLayout)actorCard1.findViewById(R.id.actorHolder);
                final ImageView actorImage1 = (ImageView) actorCard1.findViewById(R.id.actorImage);
                TextView actorName1 = (TextView) actorCard1.findViewById(R.id.actorName);
                TextView charaterName1 = (TextView) actorCard1.findViewById(R.id.characterName);

                //LinearLayout actorHolder2 = (LinearLayout)actorCard2.findViewById(R.id.actorHolder);
                final ImageView actorImage2 = (ImageView) actorCard2.findViewById(R.id.actorImage);
                TextView actorName2 = (TextView) actorCard2.findViewById(R.id.actorName);
                TextView charaterName2 = (TextView) actorCard2.findViewById(R.id.characterName);

                // LinearLayout actorHolder3 = (LinearLayout)actorCard3.findViewById(R.id.actorHolder);
                final ImageView actorImage3 = (ImageView) actorCard3.findViewById(R.id.actorImage);
                TextView actorName3 = (TextView) actorCard3.findViewById(R.id.actorName);
                TextView charaterName3 = (TextView) actorCard3.findViewById(R.id.characterName);


                final Cast actor1 = actors.get(0);
                Picasso.with(context)
                        .load(MovieUtils.imageURL(actor1.profile_path))
                        .into(actorImage1);
                Picasso.with(context);
                actorName1.setText(actor1.name);
                charaterName1.setText(actor1.character);

                final Cast actor2 = actors.get(1);
                Picasso.with(context)
                        .load(MovieUtils.imageURL(actor2.profile_path))
                        .into(actorImage2);
                Picasso.with(context);
                actorName2.setText(actor2.name);
                charaterName2.setText(actor2.character);

                final Cast actor3 = actors.get(2);
                Picasso.with(context)
                        .load(MovieUtils.imageURL(actor3.profile_path))
                        .into(actorImage3);
                Picasso.with(context);
                actorName3.setText(actor3.name);
                charaterName3.setText(actor3.character);


                actorCard1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MovieDetailActivity.this, PeopleDetailActivity.class);
                        i.putExtra(ActorsListAdapter.ACTOR_ID, actor1.id);
                        startActivity(i);
                    }
                });
                actorCard2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MovieDetailActivity.this, PeopleDetailActivity.class);
                        i.putExtra(ActorsListAdapter.ACTOR_ID, actor2.id);
                        startActivity(i);
                    }
                });

                actorCard3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MovieDetailActivity.this, PeopleDetailActivity.class);
                        i.putExtra(ActorsListAdapter.ACTOR_ID, actor3.id);
                        startActivity(i);
                    }
                });


                TextView view = (TextView) layout.findViewById(R.id.viewMoreText);

                if (actors.size() < 3) {
                    view.setVisibility(View.GONE);
                } else {

                    view.setText("View " + (actors.size() - 3) + " +");


                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MovieDetailActivity.this, AllActorsActivity.class);

                            i.putExtra(MovieDetailActivity.MOVIE_LIST, (Serializable) movie.casts.cast);
                            if(Build.VERSION.SDK_INT>20)
                            {
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MovieDetailActivity.this);
                                startActivity(i,options.toBundle());
                            }
                            else {
                                startActivity(i);
                            }
                        }
                    });


                }

            }
            else {
                layout.setVisibility(View.GONE);
            }

        }

        public void createCrewSection()
        {

            List<com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Crew> crews = movie.casts.crew;

            CardView layout = (CardView)findViewById(R.id.top3crew);

            if(crews.size()>2) {

                layout.setVisibility(View.VISIBLE);
                LinearLayout crewCard1 = (LinearLayout) layout.findViewById(R.id.crew1);
                ImageView crewImage1 = (ImageView) crewCard1.findViewById(R.id.crewImage);
                TextView crewName1 = (TextView) crewCard1.findViewById(R.id.crewName);
                TextView crewRole1 = (TextView) crewCard1.findViewById(R.id.crewRole);

                LinearLayout crewCard2 = (LinearLayout) layout.findViewById(R.id.crew2);
                ImageView crewImage2 = (ImageView) crewCard2.findViewById(R.id.crewImage);
                TextView crewName2 = (TextView) crewCard2.findViewById(R.id.crewName);
                TextView crewRole2 = (TextView) crewCard2.findViewById(R.id.crewRole);

                LinearLayout crewCard3 = (LinearLayout) layout.findViewById(R.id.crew3);
                ImageView crewImage3 = (ImageView) crewCard3.findViewById(R.id.crewImage);
                TextView crewName3 = (TextView) crewCard3.findViewById(R.id.crewName);
                TextView crewRole3 = (TextView) crewCard3.findViewById(R.id.crewRole);

                Crew crew1 = crews.get(0);
                Picasso.with(context)
                        .load(MovieUtils.imageURL(crew1.profile_path))
                        .into(crewImage1);

                crewName1.setText(crew1.name);
                crewRole1.setText(crew1.job);

                Crew crew2 = crews.get(1);
                Picasso.with(context)
                        .load(MovieUtils.imageURL(crew2.profile_path))
                        .into(crewImage2);

                crewName2.setText(crew2.name);
                crewRole2.setText(crew2.job);

                Crew crew3 = crews.get(2);
                Picasso.with(context)
                        .load(MovieUtils.imageURL(crew3.profile_path))
                        .into(crewImage3);

                crewName3.setText(crew3.name);
                crewRole3.setText(crew3.job);

                TextView textView = (TextView) layout.findViewById(R.id.viewMoreText);

                if(crews.size()<4)
                {
                    textView.setVisibility(View.GONE);
                }
                else {

                    textView.setText("View " + (crews.size() - 3) + " +");
                    textView.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {


                            Intent i = new Intent(MovieDetailActivity.this, AllCrewActivity.class);
                            i.putExtra(MovieDetailActivity.MOVIE_LIST, (Serializable) movie.casts.crew);
                            if(Build.VERSION.SDK_INT>20)
                            {
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MovieDetailActivity.this);
                                startActivity(i,options.toBundle());
                            }
                            else {
                                startActivity(i);
                            }
                        }
                    });
                }

            }
            else
            {
                layout.setVisibility(View.GONE);
            }

        }


        public void createAboutSection()
        {
            CardView aboutSectionCard = (CardView)findViewById(R.id.aboutSection);

            aboutSectionCard.setVisibility(View.VISIBLE);

            TextView releaseDate = (TextView)aboutSectionCard.findViewById(R.id.release_date);
            releaseDate.setText(MovieUtils.getFormattedDate(movie.release_date));

            TextView runtime = (TextView)aboutSectionCard.findViewById(R.id.runtime);
            runtime.setText(movie.runtime+" mins");

            TextView language = (TextView)aboutSectionCard.findViewById(R.id.original_language);
            language.setText(movie.original_language);

            // TODO FILTER MOVIE ACTIVITY INTEGRATION

            /*
            language.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MovieDetailActivity.this,FilterMovieActivity.class);
                    if(Build.VERSION.SDK_INT>20)
                    {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MovieDetailActivity.this);
                        startActivity(i,options.toBundle());
                    }
                    else {
                        startActivity(i);
                    }
                }
            });
            */


            final TextView website = (TextView)aboutSectionCard.findViewById(R.id.website);

            LinearLayout genres = (LinearLayout)aboutSectionCard.findViewById(R.id.genres);
            buildGenre(genres);

            /*
             TODO Genres Integration IN TableLayout

            List<Genre> genres = movie.getGenres();
            String genall ="";
            for(int i=0;i<genres.size();i++)
            {
                genall = genall+ genres.get(i).getName()+",";
            }
            //website.setText(movie.getHomePage());
            website.setText(genall);
            */

            if(!movie.homepage.equalsIgnoreCase("")) {
                website.setText(movie.homepage);
                website.setVisibility(View.VISIBLE);

            }

            website.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MovieUtils.openInBrowser(MovieDetailActivity.this,movie.homepage);
                }
            });



        }

        public void buildGenre(View v)
        {
            TextView genre1 = (TextView)v.findViewById(R.id.genre1);
            TextView genre2 = (TextView)v.findViewById(R.id.genre2);
            TextView genre3 = (TextView)v.findViewById(R.id.genre3);

            List<Genre> genres = movie.genres;
            Log.d("GENRES SIZE", genres.size() + "");

            if(genres.size()>0) {

                genre1.setText(genres.get(0).name);
                genre1.setVisibility(View.VISIBLE);
            }
            if(genres.size()>1) {
                genre2.setText(genres.get(1).name);
                genre2.setVisibility(View.VISIBLE);
            }
            if(genres.size()>2) {
                genre3.setText(genres.get(2).name);
                genre3.setVisibility(View.VISIBLE);
            }
        }

    }



}