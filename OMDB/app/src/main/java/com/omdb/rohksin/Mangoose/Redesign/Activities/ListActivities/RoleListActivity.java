package com.omdb.rohksin.Mangoose.Redesign.Activities.ListActivities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.omdb.rohksin.Mangoose.Redesign.Adapters.RoleListAdapter;
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
        return new RoleListAdapter(movieRoles,RoleListActivity.this);
    }

    @Override
    public void createUI() {
        getSupportActionBar().setTitle("Roles");
    }

}
