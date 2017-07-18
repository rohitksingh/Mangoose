package com.omdb.rohksin.Mangoose.ObjectOrientedSearch.HomePage.Fragments.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omdb.rohksin.Mangoose.ObjectOrientedSearch.HomePage.Fragments.BaseFragment;
import com.omdb.rohksin.Mangoose.R;

/**
 * Created by Illuminati on 7/8/2017.
 */
public class ComingSoonFragment extends BaseFragment {
    @Override
    public BaseFragment provideYourFragment() {
        return new ComingSoonFragment();
    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.coming_soon,parent,false);
    }
}
