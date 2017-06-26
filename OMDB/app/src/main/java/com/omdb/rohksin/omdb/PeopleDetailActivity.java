package com.omdb.rohksin.omdb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.omdb.Adaters.ActorsListAdapter;
import com.omdb.rohksin.omdb.NewSearch.POJO.ActorDetail;
import com.omdb.rohksin.omdb.NewSearch.POJO.MovieRole;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl.ActorDetailMapper;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl.DetailMovieMapper;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;
import com.omdb.rohksin.omdb.NewSearch.Utility.MovieUtils;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.Impl.PeopleIDURLBuilder;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.URLBuilders.URLBuilder;
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
                createAllMovies();

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


        public void createAllMovies()
        {
            LinearLayout layout = (LinearLayout)findViewById(R.id.all_movies);
            ImageView movie1 = (ImageView)layout.findViewById(R.id.image1);
            ImageView movie2 = (ImageView)layout.findViewById(R.id.image2);
            ImageView movie3 = (ImageView)layout.findViewById(R.id.image3);

            List<MovieRole> roles = actorDetail.getMovieRoles();

            int upperLimit = 3;
            if(roles.size()<3)
                upperLimit = roles.size();


            Log.d("SIZE",roles.size()+"");
            for(int i=0;i<3;i++)
            {
                Log.d("POSTERPATHS",roles.get(i).getMovieName()+"");
                Log.d("POSTERPATHS",roles.get(i).getMoviePosterPath()+"");
            }

            Picasso.with(context)
                    .load(MovieUtils.imageURL(roles.get(0).getMoviePosterPath()))
                    .into(movie1);

            Picasso.with(context)
                    .load(MovieUtils.imageURL(roles.get(1).getMoviePosterPath()))
                    .into(movie2);

            Picasso.with(context)
                    .load(MovieUtils.imageURL(roles.get(2).getMoviePosterPath()))
                    .into(movie3);

        }






    }

}
