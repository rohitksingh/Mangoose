package com.omdb.rohksin.omdb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.omdb.Adaters.ActorsListAdapter;
import com.omdb.rohksin.omdb.LandingActivities.AllActorsActivity;
import com.omdb.rohksin.omdb.NewSearch.POJO.Actor;
import com.omdb.rohksin.omdb.NewSearch.POJO.ActorDetail;
import com.omdb.rohksin.omdb.NewSearch.POJO.MovieRole;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl.ActorDetailMapper;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl.DetailMovieMapper;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;
import com.omdb.rohksin.omdb.NewSearch.Utility.MovieUtils;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.PeopleIDURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.URLBuilder;
import com.omdb.rohksin.omdb.SerializableCarriers.SerializableObject;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Illuminati on 6/22/2017.
 */
public class PeopleDetailActivity extends AppCompatActivity {

    private ActorDetail actorDetail;
    private ImageView backDrop;
    private CircleImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_detail_activity);

        backDrop = (ImageView)findViewById(R.id.backDrop);
        profileImage =(CircleImageView)findViewById(R.id.profileImage);



        IntentFilter filter = new IntentFilter();
        filter.addAction(ActorDetailMapper.ObjectMapped);
        registerReceiver(new ActorDetailReceiver(),filter);

        String people_id = getIntent().getStringExtra(ActorsListAdapter.ACTOR_ID);

        //EndPoint endPoint = new ActorDetailURL();
        //String endPointURL =  endPoint.buildEndPoint(people_id);
        URLBuilder urlBuilder = new PeopleIDURLBuilder(people_id);
        String endPointURL = urlBuilder.bulidURL();
        Log.d("PEOPLE_URL", endPointURL);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, endPointURL,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //res.setText(response.toString());
                        Log.d("ENDPOINT", response.toString());
                        try {
                            ResponseMapper mapper = new ActorDetailMapper();
                            mapper.mapResponse(response);
                            actorDetail = (ActorDetail)mapper.objectMapped();
                            sendBroadcast();
                        }
                        catch (JSONException e)
                        {

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );

        requestQueue.add(request);


    }

    public void sendBroadcast()
    {
        Intent i = new Intent();
        i.setAction(ActorDetailMapper.ObjectMapped);
        sendBroadcast(i);
    }


    private class  ActorDetailReceiver extends BroadcastReceiver{

        private Context context;

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equalsIgnoreCase(DetailMovieMapper.ObjectMapped))
            {
                Toast.makeText(context,actorDetail.toString(),Toast.LENGTH_LONG).show();
                this.context = context;

                createBioGraphy();
                createAboutSetion();
                createMainContent();
                //createAllMovies();
                createActorsSection();

            }
        }


        public void createMainContent()
        {
            Picasso.with(context)
                    .load(MovieUtils.imageURL(actorDetail.getProfileImage()))
                    .into(profileImage);
        }

        public void createBioGraphy()
        {
            CardView cardView = (CardView)findViewById(R.id.biography);
            TextView actorName = (TextView)cardView.findViewById(R.id.movie_name);
            TextView biography = (TextView)cardView.findViewById(R.id.OverViewText);

            actorName.setText("Biography");
            biography.setText(actorDetail.getBiography());
        }

        public void createAboutSetion()
        {
            CardView cardView = (CardView)findViewById(R.id.aboutSection);
            TextView birthday = (TextView)findViewById(R.id.birthday);
            TextView deathdate = (TextView)findViewById(R.id.deathdate);
            TextView birthplace = (TextView)findViewById(R.id.birthplace);
            TextView website = (TextView)findViewById(R.id.website);

            birthday.setText(actorDetail.getBirthday());
            deathdate.setText(actorDetail.getDeathday());
            birthplace.setText(actorDetail.getBirthPlace());
            website.setText(actorDetail.getWebSite());

        }

/*
        public void createAllMovies()
        {
            CardView layout = (CardView)findViewById(R.id.all_movies);

            TextView viewMore = (TextView)layout.findViewById(R.id.viewMoreText);
            viewMore.setText("Hi");

            RelativeLayout movie_card1 = (RelativeLayout)layout.findViewById(R.id.movie1);
            RelativeLayout movie_card2 = (RelativeLayout)layout.findViewById(R.id.movie2);
            RelativeLayout movie_card3 = (RelativeLayout)layout.findViewById(R.id.movie3);


            ImageView movie1 = (ImageView)movie_card1.findViewById(R.id.image1);
            ImageView movie2 = (ImageView)movie_card2.findViewById(R.id.image2);
            ImageView movie3 = (ImageView)movie_card3.findViewById(R.id.image3);

            movie1.setImageResource(R.drawable.back);
            TextView movie_name = (TextView)movie_card1.findViewById(R.id.movie_name);
            movie_name.setText("skdjsd");


            Log.d("NULL CHECK",(layout==null)+" "+(movie_card1==null)+" " + (movie1==null));

            List<MovieRole> roles = actorDetail.getMovieRoles();

            int upperLimit = 3;
            if(roles.size()<3)
                upperLimit = roles.size();


            Log.d("SIZE",roles.size()+"");

            if(roles.size()>0)
            Picasso.with(context)
                    .load(MovieUtils.imageURL(roles.get(0).getMoviePosterPath()))
                    .into(movie1);

            if(roles.size()>1)
            Picasso.with(context)
                    .load(MovieUtils.imageURL(roles.get(1).getMoviePosterPath()))
                    .into(movie2);

            if(roles.size()>2)
            Picasso.with(context)
                    .load(MovieUtils.imageURL(roles.get(2).getMoviePosterPath()))
                    .into(movie3);



        }
*/

        public void createActorsSection()
        {
            final List<MovieRole> roles = actorDetail.getMovieRoles();

            CardView layout = (CardView)findViewById(R.id.top3Actors);

            if(roles.size()>2) {
                LinearLayout layout1 = (LinearLayout) layout.findViewById(R.id.movie_cast);

                LinearLayout actorCard1 = (LinearLayout) layout1.findViewById(R.id.actor1);
                LinearLayout actorCard2 = (LinearLayout) layout1.findViewById(R.id.actor2);
                LinearLayout actorCard3 = (LinearLayout) layout1.findViewById(R.id.actor3);


                //LinearLayout actorHolder1 = (LinearLayout)actorCard1.findViewById(R.id.actorHolder);
                ImageView actorImage1 = (ImageView) actorCard1.findViewById(R.id.actorImage);
                TextView actorName1 = (TextView) actorCard1.findViewById(R.id.actorName);
                TextView charaterName1 = (TextView) actorCard1.findViewById(R.id.characterName);

                //LinearLayout actorHolder2 = (LinearLayout)actorCard2.findViewById(R.id.actorHolder);
                ImageView actorImage2 = (ImageView) actorCard2.findViewById(R.id.actorImage);
                TextView actorName2 = (TextView) actorCard2.findViewById(R.id.actorName);
                TextView charaterName2 = (TextView) actorCard2.findViewById(R.id.characterName);

                // LinearLayout actorHolder3 = (LinearLayout)actorCard3.findViewById(R.id.actorHolder);
                ImageView actorImage3 = (ImageView) actorCard3.findViewById(R.id.actorImage);
                TextView actorName3 = (TextView) actorCard3.findViewById(R.id.actorName);
                TextView charaterName3 = (TextView) actorCard3.findViewById(R.id.characterName);


                final MovieRole actor1 = roles.get(0);
                Picasso.with(context)
                        .load(MovieUtils.imageURL(actor1.getMoviePosterPath()))
                        .into(actorImage1);
                Picasso.with(context);
                actorName1.setText(actor1.getMovieName());
                charaterName1.setText(actor1.getCharacterName());

                final MovieRole actor2 = roles.get(1);
                Picasso.with(context)
                        .load(MovieUtils.imageURL(actor2.getMoviePosterPath()))
                        .into(actorImage2);
                Picasso.with(context);
                actorName2.setText(actor2.getMovieName());
                charaterName2.setText(actor2.getCharacterName());

                final MovieRole actor3 = roles.get(2);
                Picasso.with(context)
                        .load(MovieUtils.imageURL(actor3.getMoviePosterPath()))
                        .into(actorImage3);
                Picasso.with(context);
                actorName3.setText(actor3.getMovieName());
                charaterName3.setText(actor3.getCharacterName());


                actorCard1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(PeopleDetailActivity.this, BlankActivity.class);
                        i.putExtra("blankActivityText", actor1.getMovieId());
                        startActivity(i);
                    }
                });
                actorCard2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(PeopleDetailActivity.this, BlankActivity.class);
                        i.putExtra("blankActivityText", actor2.getMovieId());
                        startActivity(i);
                    }
                });

                actorCard3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(PeopleDetailActivity.this, BlankActivity.class);
                        i.putExtra("blankActivityText", actor3.getMovieId());
                        startActivity(i);
                    }
                });


                TextView title = (TextView)layout.findViewById(R.id.titleAndImage);
                title.setText("Movies");

                TextView view = (TextView) layout.findViewById(R.id.viewMoreText);


                if (roles.size() < 3) {
                    view.setVisibility(View.GONE);
                } else {

                    view.setText("View " + (roles.size() - 3) + " +");

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           /* Intent i = new Intent(.this, AllActorsActivity.class);
                            SerializableObject serializableObject = new SerializableObject(actors);
                            i.putExtra(BlankActivity.MOVIE_LIST, serializableObject);
                            startActivity(i);
                            */
                        }
                    });

                }

            }
            else {
                layout.setVisibility(View.GONE);
            }

        }








    }

}
