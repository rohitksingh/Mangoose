package com.omdb.rohksin.omdb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.omdb.rohksin.omdb.ObjectMaps.MovieResponse;
import com.squareup.picasso.Picasso;

/**
 * Created by Illuminati on 5/7/2017.
 */
public class ResposeObserver extends BroadcastReceiver{

    View parent;
    public final static String RESPONSE ="com.omdb.rohksin.omdb.RESPONSE";
    private MovieResponse response;

    public ResposeObserver(View parent)
    {
        this.parent = parent;
    }

    @Override
    public void onReceive(final Context context ,Intent intent)
    {
        if(intent.getAction().equals(MainActivity.RESPONSE_RECEIVED)) {
            // do something
            Toast.makeText(context, "Received", Toast.LENGTH_LONG).show();


            ImageView posterThumbnail = (ImageView) parent.findViewById(R.id.moviePosterThumbnail);
            Movie movie = MainActivity.movie;

            //Log.d("Rohit","movieThumb"+movie.getPosterThumbnail()+"");
            this.response = (MovieResponse)intent.getSerializableExtra("bak");

            Picasso.with(context)
                    .load(response.getPoster())
                    .into(posterThumbnail);

            posterThumbnail.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context,MovieActivity.class);
                    i.putExtra(RESPONSE,response);
                    context.startActivity(i);
                }
            });
        }

    }
}
