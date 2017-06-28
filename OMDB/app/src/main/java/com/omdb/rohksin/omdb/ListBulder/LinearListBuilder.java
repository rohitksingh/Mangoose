package com.omdb.rohksin.omdb.ListBulder;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

/**
 * Created by Illuminati on 6/28/2017.
 */
public abstract class LinearListBuilder implements ListBuilder{

    private Context context;

    @Override
    public void build()
    {
        RecyclerView recyclerView=null;
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);
        setAdapter();
    }

}
