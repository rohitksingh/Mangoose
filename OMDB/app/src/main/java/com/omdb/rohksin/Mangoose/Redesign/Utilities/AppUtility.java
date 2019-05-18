package com.omdb.rohksin.Mangoose.Redesign.Utilities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import com.omdb.rohksin.Mangoose.Redesign.Activities.MovieDetailActivity;
import com.omdb.rohksin.Mangoose.Redesign.Activities.PeopleDetailActivity;
import com.omdb.rohksin.Mangoose.Redesign.Adapters.ActorsListAdapter;


public class AppUtility {

    private static final String TAG = "AppUtility";

    public static void startMovieDetailActivity(Context context, String id)
    {
        Log.d(TAG, "startMovieDetailActivity: "+id);
        Intent i = new Intent(context, PeopleDetailActivity.class);
        i.putExtra(ActorsListAdapter.ACTOR_ID, id);
        context.startActivity(i);
    }


    public static void startMovieDetailActiivtyWithAnim(Context context, String id, ImageView posterThumbnail)
    {
        Log.d(TAG, "startMovieDetailActivity: "+id);
        Intent i = new Intent(context, MovieDetailActivity.class);
        i.putExtra(AppConstants.MOVIE_ID, id);

        if (Build.VERSION.SDK_INT > 20) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, posterThumbnail, "ImageView");
            context.startActivity(i, options.toBundle());
        } else {
            context.startActivity(i);
        }
    }

}



