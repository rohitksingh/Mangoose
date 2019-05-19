package com.omdb.rohksin.Mangoose.Redesign.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.omdb.rohksin.Mangoose.R;
import com.omdb.rohksin.Mangoose.Redesign.Activities.ListActivities.BasicListActivity;
import com.omdb.rohksin.Mangoose.Redesign.Adapters.ViewAllMoviesAdapter;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Role;

import java.util.ArrayList;

/**
 * Created by Illuminati on 6/28/2017.
 */

//R.layout.list_layout
public class RoleListActivity extends BasicListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public RecyclerView.Adapter createAdapter() {
        ArrayList<Role> movieRoles = (ArrayList<Role>)getIntent().getSerializableExtra("allMoviesListActivity");
        return new ViewAllMoviesAdapter(movieRoles,RoleListActivity.this);
    }

    @Override
    public void createUI() {
        getSupportActionBar().setTitle("Roles");
    }

}
