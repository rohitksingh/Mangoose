package com.omdb.rohksin.Mangoose.Redesign.Activities.ListActivities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.omdb.rohksin.Mangoose.R;

public abstract class BasicListActivity extends AppCompatActivity{

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    public RecyclerView.Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        configure();
    }

    /****************************************************************************
     *
     *            Abstract methods. Each subclass must provide implementation
     *
     ****************************************************************************/
    public abstract RecyclerView.Adapter createAdapter();
    public abstract void createUI();


    /****************************************************************************
     *
     *            Protected methods are optional to override
     *
     ****************************************************************************/

    // Default Slide from left animation. Can be overriden for different animation
    protected void setAnimation()
    {
        if(Build.VERSION.SDK_INT>20) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setDuration(400);
            slide.setInterpolator(new AccelerateDecelerateInterpolator());
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
        }
    }

    // Default mainLayout
    protected void createMainLayout()
    {
        setContentView(R.layout.activity_list_basic);
        recyclerView = (RecyclerView)findViewById(R.id.image_list_landing);
    }

    // Fefault layout manager Linear vertical
    protected RecyclerView.LayoutManager provideLayoutManager() {
        return layoutManager = new LinearLayoutManager(BasicListActivity.this);
    }

    /****************************************************************************
     *
     *            Private method to internal use of superclass.
     *            Can not be overriden
     *
     ****************************************************************************/
    private void configure() {
        setAnimation();
        createMainLayout();
        createUI();
        layoutManager = provideLayoutManager();
        adapter = createAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

}
