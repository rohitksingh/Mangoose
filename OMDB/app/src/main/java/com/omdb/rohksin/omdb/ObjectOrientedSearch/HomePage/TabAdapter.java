package com.omdb.rohksin.omdb.ObjectOrientedSearch.HomePage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.omdb.rohksin.omdb.ObjectOrientedSearch.HomePage.Fragments.Impl.ComingSoonFragment;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.HomePage.Fragments.Impl.SearchListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 7/7/2017.
 */
public class TabAdapter extends FragmentPagerAdapter {

    private String[] tabNames;
    private List<Fragment> fragments;


    public TabAdapter(FragmentManager fm , String[] tabNames) {
        super(fm);
        this.tabNames = tabNames;
        fragments= new ArrayList<Fragment>();
        fragments.add(new SearchListFragment().newInstance());
        fragments.add(new ComingSoonFragment().newInstance());
        fragments.add(new SearchListFragment().newInstance());

    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        Fragment fragment = fragments.get(position);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabNames[position];
    }
}
