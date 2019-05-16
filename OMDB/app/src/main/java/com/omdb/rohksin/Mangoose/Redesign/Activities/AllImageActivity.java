package com.omdb.rohksin.Mangoose.Redesign.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;

import com.omdb.rohksin.Mangoose.Redesign.Adapters.ListAdaper;
import com.omdb.rohksin.Mangoose.BlankActivity;
import com.omdb.rohksin.Mangoose.R;

import java.util.List;

/**
 * Created by Illuminati on 6/20/2017.
 */
public class AllImageActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstaceState)
    {
        super.onCreate(savedInstaceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setAnimation();
        setContentView(R.layout.image_landing_layout);

        List<String> moviesList = getIntent().getStringArrayListExtra(BlankActivity.MOVIE_LIST);
        RecyclerView recyclerImageView = (RecyclerView)findViewById(R.id.image_list_landing);
        GridLayoutManager llm = new GridLayoutManager(this,2);
        ListAdaper adapter = new ListAdaper(moviesList,this);
        recyclerImageView.setLayoutManager(llm);
        recyclerImageView.setAdapter(adapter);

        //Toast.makeText(this,"kjdksjd",Toast.LENGTH_LONG);

    }

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
}
