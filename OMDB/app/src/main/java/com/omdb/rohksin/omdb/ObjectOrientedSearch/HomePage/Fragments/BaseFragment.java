package com.omdb.rohksin.omdb.ObjectOrientedSearch.HomePage.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Illuminati on 7/7/2017.
 */


public abstract class BaseFragment extends Fragment {

    public BaseFragment newInstance()
    {
        Log.d("Rohit", "new Instance");
        Bundle args = new Bundle();
        // args.putInt(ARG_PAGE, page);
        BaseFragment fragment = provideYourFragment();
        fragment.setArguments(args);
        return fragment;

    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup parent, Bundle savedInstanseState)
    {
        View view = provideYourFragmentView(inflater,parent,savedInstanseState);
        return view;
    }

    public abstract BaseFragment provideYourFragment();

    public abstract View provideYourFragmentView(LayoutInflater inflater,ViewGroup parent, Bundle savedInstanceState);

}



