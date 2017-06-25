package com.omdb.rohksin.omdb.ObjectOrientedSearch.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.omdb.rohksin.omdb.Adaters.PeopleListAdapter;
import com.omdb.rohksin.omdb.NewSearch.POJO.PeopleDetail;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search;
import com.omdb.rohksin.omdb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class PeopleListReceiver extends BroadcastReceiver {


    private View view;

    public PeopleListReceiver(View v)
    {
        view = v;
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        //View view = View.inflate(context, R.layout.seach_page_layout, null);


        if (intent.getAction().equalsIgnoreCase(Search.SEARCH_FINISHED)) {

            ArrayList<PeopleDetail> peopleDetails = (ArrayList<PeopleDetail>) intent.getSerializableExtra(Search.RESULT);
            Toast.makeText(context, "Success" + (peopleDetails.size()), Toast.LENGTH_LONG).show();
            //Log.d("VIEW NULL ",(peopleDetail==null)+"");
            TextView response = (TextView)view.findViewById(R.id.responseObjserver);

            RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rv);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            PeopleListAdapter adapter = new PeopleListAdapter(peopleDetails,context);
            recyclerView.setAdapter(adapter);



            //responseObjserver.setText(peopleDetail.getName());
        }
    }
  }

