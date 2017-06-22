package com.omdb.rohksin.omdb;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omdb.rohksin.omdb.Adaters.ActorsListAdapter;
import com.omdb.rohksin.omdb.NewSearch.EndPoint.EndPoint;
import com.omdb.rohksin.omdb.NewSearch.EndPoint.Impl.ActorDetailURL;
import com.omdb.rohksin.omdb.NewSearch.POJO.ActorDetail;
import com.omdb.rohksin.omdb.NewSearch.POJO.DetailMovie;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl.ActorDetailMapper;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.Impl.DetailMovieMapper;
import com.omdb.rohksin.omdb.NewSearch.ResponseMapper.ResponseMapper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Illuminati on 6/22/2017.
 */
public class PeopleDetailActivity extends AppCompatActivity {

    private ActorDetail actorDetail;
    private TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_detail_activity);
        res = (TextView)findViewById(R.id.response);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ActorDetailMapper.ObjectMapped);
        registerReceiver(new ActorDetailReceiver(),filter);

        String people_id = getIntent().getStringExtra(ActorsListAdapter.ACTOR_ID);

        EndPoint endPoint = new ActorDetailURL();
        String endPointURL =  endPoint.buildEndPoint(people_id);
        Log.d("PEOPLE_URL", endPointURL);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, endPointURL,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        res.setText(response.toString());
                        //Log.d("ENDPOINT", response.toString());
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

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equalsIgnoreCase(DetailMovieMapper.ObjectMapped))
            {
                Toast.makeText(context,actorDetail.toString(),Toast.LENGTH_LONG).show();
                res.setText(actorDetail.getName()+actorDetail.getBiography());
            }
        }
    }

}
