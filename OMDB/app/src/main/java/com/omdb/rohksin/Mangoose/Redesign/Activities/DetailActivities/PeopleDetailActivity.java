package com.omdb.rohksin.Mangoose.Redesign.Activities.DetailActivities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.Redesign.Activities.ListActivities.RoleListActivity;
import com.omdb.rohksin.Mangoose.R;
import com.omdb.rohksin.Mangoose.Redesign.Activities.BiographyActivity;
import com.omdb.rohksin.Mangoose.Redesign.Models.MovieRole;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Actor;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Role;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.AppConstants;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.AppUtility;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;
import com.omdb.rohksin.Mangoose.ObjectOrientedSearch.URLBuilders.Impl.PeopleIDURLBuilder;
import com.omdb.rohksin.Mangoose.ObjectOrientedSearch.URLBuilders.URLBuilder;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Illuminati on 6/22/2017.
 */
public class PeopleDetailActivity extends BasicDetailActivity {

    private ImageView backDrop;
    private ImageView profileImage;
    private CardView aboutSection;

    private Actor actorDetail;

    private static final String TAG = "PeopleDetailActivity";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public URLBuilder getUrlBuilder() {
        return new PeopleIDURLBuilder(objectId);
    }

    @Override
    public String getObjectId() {
        return AppConstants.PERSON_ID;
    }

    @Override
    public void parseObject(String jsonString) {
       parse(jsonString);
    }

    @Override
    public void createUI() {

        backDrop = (ImageView)findViewById(R.id.backDrop);
        aboutSection = (CardView)findViewById(R.id.aboutSection);
        profileImage = (ImageView)aboutSection.findViewById(R.id.actorImage);

        if(Build.VERSION.SDK_INT>20)
        {
            profileImage.setTransitionName("ACTOR");
        }

        createBioGraphy();
        createMainContent();
        createActorsSection();
        createNewAboutSetion();
    }

    @Override
    public int getMainLayout() {
        return R.layout.activity_people_detail;
    }

    private void createMainContent() {

        CollapsingToolbarLayout title= (CollapsingToolbarLayout)findViewById(R.id.title);
        title.setTitle(actorDetail.name);

        List<Role> roles =actorDetail.movie_credits.cast;

        if(roles!=null) {

            String poster_path = roles.get(0).poster_path;
            Picasso.with(this)
                        .load(MovieUtils.imageURL(poster_path))
                        .into(backDrop);

        }
    }

    private void createBioGraphy() {

        CardView cardView = (CardView)findViewById(R.id.biography);
        TextView actorName = (TextView)cardView.findViewById(R.id.movie_name);
        TextView biography = (TextView)cardView.findViewById(R.id.OverViewText);
        TextView readMore = (TextView)cardView.findViewById(R.id.readMore);

        if(!actorDetail.biography.equalsIgnoreCase("null")) {
                cardView.setVisibility(View.VISIBLE);
                actorName.setText("Biography");
                biography.setText(actorDetail.biography);
        }

        readMore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(PeopleDetailActivity.this, BiographyActivity.class);
                i.putExtra("Biography",actorDetail.biography);
                i.putExtra("Name",actorDetail.name);

                if(Build.VERSION.SDK_INT>20)
                {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PeopleDetailActivity.this);
                    startActivity(i,options.toBundle());
                }
                else {
                    startActivity(i);
                }

            }
        });

    }

    private void createAboutSetion() {

        CardView cardView = (CardView)findViewById(R.id.aboutSection);
        TextView birthday = (TextView)findViewById(R.id.birthday);
        //TextView deathdate = (TextView)findViewById(R.id.deathdate);
        TextView birthplace = (TextView)findViewById(R.id.birthplace);
        TextView website = (TextView)findViewById(R.id.website);

        if(!actorDetail.birthday.equalsIgnoreCase("null")) {
            cardView.setVisibility(View.VISIBLE);
            birthday.setText(MovieUtils.getFormattedDate(actorDetail.birthday));
            birthday.setVisibility(View.VISIBLE);
        }

        if(!actorDetail.deathday.equalsIgnoreCase("null")) {
           // deathdate.setText(MovieUtils.getFormattedDate(actorDetail.deathday));
            //deathdate.setVisibility(View.VISIBLE);
        }

        if(!actorDetail.place_of_birth.equalsIgnoreCase("null")) {
            birthplace.setText(actorDetail.place_of_birth);
            birthplace.setVisibility(View.VISIBLE);
        }

        if(!actorDetail.homepage.equalsIgnoreCase("null")) {
            website.setText(actorDetail.homepage);
            website.setVisibility(View.GONE);
        }

    }

    public void createActorsSection() {

        final List<Role> roles = actorDetail.movie_credits.cast;
        CardView layout = (CardView)findViewById(R.id.top3Actors);

        Log.d("CRASH","ROLES SIZE"+(roles==null));

        Log.d("CRASH","ROLES SIZE == "+roles.size());

        if(roles.size()>2) {

            layout.setVisibility(View.VISIBLE);
            LinearLayout layout1 = (LinearLayout) layout.findViewById(R.id.movie_cast);

            LinearLayout actorCard1 = (LinearLayout) layout1.findViewById(R.id.actor1);
            LinearLayout actorCard2 = (LinearLayout) layout1.findViewById(R.id.actor2);
            LinearLayout actorCard3 = (LinearLayout) layout1.findViewById(R.id.actor3);

            final ImageView actorImage1 = (ImageView) actorCard1.findViewById(R.id.actorImage);
            TextView actorName1 = (TextView) actorCard1.findViewById(R.id.actorName);
            TextView charaterName1 = (TextView) actorCard1.findViewById(R.id.characterName);


                final ImageView actorImage2 = (ImageView) actorCard2.findViewById(R.id.actorImage);
                TextView actorName2 = (TextView) actorCard2.findViewById(R.id.actorName);
                TextView charaterName2 = (TextView) actorCard2.findViewById(R.id.characterName);

                final ImageView actorImage3 = (ImageView) actorCard3.findViewById(R.id.actorImage);
                TextView actorName3 = (TextView) actorCard3.findViewById(R.id.actorName);
                TextView charaterName3 = (TextView) actorCard3.findViewById(R.id.characterName);


                final Role actor1 = roles.get(0);

                Picasso.with(this)
                        .load(MovieUtils.imageURL(actor1.poster_path))
                        .into(actorImage1);
                Picasso.with(this);
                actorName1.setText(actor1.title);
                charaterName1.setText(actor1.character);

                final Role actor2 = roles.get(1);


                Picasso.with(this)
                        .load(MovieUtils.imageURL(actor2.poster_path))
                        .into(actorImage2);
                Picasso.with(this);
                actorName2.setText(actor2.title);
                charaterName2.setText(actor2.character);

                final Role actor3 = roles.get(2);


                Picasso.with(this)
                        .load(MovieUtils.imageURL(actor3.poster_path))
                        .into(actorImage3);

                Picasso.with(this);
                actorName3.setText(actor3.title);
                charaterName3.setText(actor3.character);


                actorCard1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppUtility.startMovieDetailActivity(PeopleDetailActivity.this, actor1.id+"");
                    }
                });
                actorCard2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppUtility.startMovieDetailActivity(PeopleDetailActivity.this, actor2.id+"");
                    }
                });

                actorCard3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       AppUtility.startMovieDetailActivity(PeopleDetailActivity.this, actor3.id+"");
                    }
                });


                TextView title = (TextView)layout.findViewById(R.id.titleAndImage);
                title.setText("Movies");

                TextView view = (TextView) layout.findViewById(R.id.viewMoreText);

                if (roles.size() < 3) {
                    view.setVisibility(View.GONE);
                } else {

                    view.setText("View " + (roles.size() - 3) + " +");

                    Collections.sort(roles);

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(PeopleDetailActivity.this, RoleListActivity.class);
                            ArrayList<MovieRole> roles1 = (ArrayList) roles;

                            i.putExtra("allMoviesListActivity", roles1);

                            if (Build.VERSION.SDK_INT > 20) {

                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PeopleDetailActivity.this);
                                startActivity(i, options.toBundle());
                            } else {
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

        private void createNewAboutSetion() {

            TextView birthDay= (TextView)aboutSection.findViewById(R.id.birthday);
            TextView birtPlace = (TextView)aboutSection.findViewById(R.id.birthplace);
            TextView website = (TextView)aboutSection.findViewById(R.id.website);

            Picasso.with(this)
                    .load(MovieUtils.imageURL(actorDetail.profile_path))
                    .into(profileImage);

            profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MovieUtils.previewImageWithAnimation(PeopleDetailActivity.this,actorDetail.profile_path,profileImage,"ImageView");
                }
            });

            if(!actorDetail.birthday.equalsIgnoreCase("null")) {
                aboutSection.setVisibility(View.VISIBLE);
                birthDay.setText(MovieUtils.getFormattedDate(actorDetail.birthday));
                birthDay.setVisibility(View.VISIBLE);
            }

            if(!actorDetail.place_of_birth.equalsIgnoreCase("null")) {
                birtPlace.setText(actorDetail.place_of_birth);
                birtPlace.setVisibility(View.VISIBLE);
            }


            if(actorDetail.homepage!=null){
                if(!actorDetail.homepage.equalsIgnoreCase("null")) {
                    website.setText(actorDetail.homepage);
                    website.setVisibility(View.GONE);
                }
            }

            website.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MovieUtils.openInBrowser(PeopleDetailActivity.this,actorDetail.homepage);
                }
            });
    }

    private void parse(String jsonString) {

        Moshi moshi = new Moshi.Builder()
                .build();

        JsonAdapter<Actor> jsonAdapter = moshi.adapter(Actor.class);

        try {
            actorDetail = jsonAdapter.fromJson(jsonString);
            Log.d(TAG, "Parsing success: ");
        }
        catch (IOException e)
        {
            Log.d(TAG, "Parsing Failed: ");
        }

    }

}