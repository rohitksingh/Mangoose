package com.omdb.rohksin.omdb.ObjectOrientedSearch.HomePage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toolbar;

import com.omdb.rohksin.omdb.R;

/**
 * Created by Illuminati on 7/7/2017.
 */
public class MainSearchActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main_search);
        ViewPager viewPager = (ViewPager)findViewById(R.id.mediaViewPager);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),new String[]{"People","TV Shows","Movies"} );
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.searchTabs);
        tabLayout.setupWithViewPager(viewPager);

    }

}
