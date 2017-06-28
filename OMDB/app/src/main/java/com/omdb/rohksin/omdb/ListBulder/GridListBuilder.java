package com.omdb.rohksin.omdb.ListBulder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Illuminati on 6/28/2017.
 */
public abstract class GridListBuilder  implements ListBuilder{

    private Context context;
    private int colums;
    private RecyclerView recyclerView=null;

    @Override
    public void build()
    {

        GridLayoutManager glm = new GridLayoutManager(context,colums);
        recyclerView.setLayoutManager(glm);
        setAdapter();
    }


}
