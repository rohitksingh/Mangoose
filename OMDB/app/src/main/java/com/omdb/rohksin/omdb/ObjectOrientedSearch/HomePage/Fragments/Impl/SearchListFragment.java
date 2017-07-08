package com.omdb.rohksin.omdb.ObjectOrientedSearch.HomePage.Fragments.Impl;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.omdb.rohksin.omdb.ObjectOrientedSearch.HomePage.Fragments.BaseFragment;
import com.omdb.rohksin.omdb.R;

/**
 * Created by Illuminati on 7/7/2017.
 */
public class SearchListFragment extends BaseFragment {

    @Override
    public BaseFragment provideYourFragment() {
        return new SearchListFragment();
    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View SearchListFragment = inflater.inflate(R.layout.search_list_fragment,parent,false);
        RecyclerView searchList = (RecyclerView)SearchListFragment.findViewById(R.id.searchList);
        return SearchListFragment;
    }

}
