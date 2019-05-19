package com.omdb.rohksin.Mangoose.Redesign.Activities.ListActivities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;

import com.omdb.rohksin.Mangoose.Redesign.Activities.DetailActivities.MovieDetailActivity;
import com.omdb.rohksin.Mangoose.Redesign.Adapters.ListAdaper;
import com.omdb.rohksin.Mangoose.R;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Backdrop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 6/20/2017.
 */
public class ImageListActivity extends BasicListActivity {

    @Override
    public void onCreate(Bundle savedInstaceState)
    {
        super.onCreate(savedInstaceState);
    }

    @Override
    public RecyclerView.LayoutManager provideLayoutManager()
    {
        return new GridLayoutManager(this,2);
    }

    @Override
    public RecyclerView.Adapter createAdapter() {

        List<Backdrop> backdrops = (List<Backdrop>) getIntent().getSerializableExtra(MovieDetailActivity.MOVIE_LIST);
        List<String> moviesList = getMovieList(backdrops);
        return new ListAdaper(moviesList,this);
    }

    @Override
    public void createUI() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    @Override
    public void setAnimation()
    {
        if(Build.VERSION.SDK_INT>20) {
            Explode explode = new Explode();
            explode.setDuration(1000);
            explode.setInterpolator(new DecelerateInterpolator());
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
        }
    }

    private List<String> getMovieList(List<Backdrop> backdrops)
    {
        List<String> imagePaths = new ArrayList<String>();
        for(Backdrop backdrop: backdrops)
        {
            imagePaths.add(backdrop.file_path);
        }
        return imagePaths;
    }
}
