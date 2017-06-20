package com.omdb.rohksin.omdb.NewSearch.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.omdb.rohksin.omdb.BlankActivity;
import com.omdb.rohksin.omdb.NewSearch.POJO.DetailMovie;
import com.omdb.rohksin.omdb.NewSearch.POJO.Video;
import com.omdb.rohksin.omdb.NewSearch.Utility.MovieUtils;
import com.omdb.rohksin.omdb.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Illuminati on 6/17/2017.
 */
public class DetailMovieReceiver extends BroadcastReceiver {

    private View view;
    private DetailMovie movie;

    public DetailMovieReceiver(View v)
    {
        this.view = view;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equalsIgnoreCase(BlankActivity.OBJECTMAPPED))
        {
            movie = (DetailMovie)intent.getSerializableExtra("BlankActivityMovie");

            Log.d("BR", "2 view" + (view == null) +movie.getTitle());
            CollapsingToolbarLayout layout = (CollapsingToolbarLayout)view.findViewById(R.id.title);
            layout.setTitle(movie.getTitle());

            ImageView imageView = (ImageView)layout.findViewById(R.id.moviePoster);


            String imgSrc = MovieUtils.imageURL(movie.getBackDropImage());

            Picasso.with(context)
                    .load(imgSrc)
                    .into(imageView);



        }
    }
}
