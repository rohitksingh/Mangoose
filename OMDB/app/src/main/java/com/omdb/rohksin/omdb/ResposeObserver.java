package com.omdb.rohksin.omdb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by Illuminati on 5/7/2017.
 */
public class ResposeObserver extends BroadcastReceiver{

    View parent;

    public ResposeObserver(View parent)
    {
        this.parent = parent;
    }

    @Override
    public void onReceive(Context context ,Intent intent)
    {
        if(intent.getAction().equals(MainActivity.RESPONSE_RECEIVED)) {
            // do something
            Toast.makeText(context, "Received", Toast.LENGTH_LONG).show();


            ImageView posterThumbnail = (ImageView) parent.findViewById(R.id.moviePosterThumbnail);
            Movie movie = MainActivity.movie;

            Log.d("Rohit","movieThumb"+movie.getPosterThumbnail()+"");

            Picasso.with(context)
                    .load(movie.getPosterThumbnail())
                    .into(posterThumbnail);
        }

    }
}
