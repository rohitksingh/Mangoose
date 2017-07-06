package com.omdb.rohksin.omdb.Adaters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.omdb.rohksin.omdb.BlankActivity;
import com.omdb.rohksin.omdb.Movie;
import com.omdb.rohksin.omdb.NewSearch.POJO.PeopleDetail;
import com.omdb.rohksin.omdb.NewSearch.POJO.TvShow;
import com.omdb.rohksin.omdb.NewSearch.Utility.MovieUtils;
import com.omdb.rohksin.omdb.ObjectOrientedSearch.SearchAlgo.Search;
import com.omdb.rohksin.omdb.R;
import com.omdb.rohksin.omdb.TVShowDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Illuminati on 6/26/2017.
 */
public class TVShowListAdapter extends RecyclerView.Adapter<TVShowListAdapter.TvViewHolder> {




    private List<TvShow> list;
    private Context context;

    public TVShowListAdapter(List<TvShow> list,Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
        TvViewHolder pvh = new TvViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(TvViewHolder holder, int position) {
        // holder.personName.setText(list.get(position).getName());
        final String movieId = list.get(position).getId();
        holder.title.setText(list.get(position).getName());
        holder.releaseYear.setText(MovieUtils.getFormattedDate(list.get(position).getAir_date()));
        holder.overView.setText(list.get(position).getOverview());


        String thumb = list.get(position).getPosterPath();
        //thumb = "http://image.tmdb.org/t/p/w185"+thumb;
        thumb = MovieUtils.imageURL(thumb);
        Log.d("Thumb", thumb);
        Picasso.with(context)
                .load(thumb).error(R.drawable.placeholder)
                .into(holder.posterThumbnail);

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"FOFO",Toast.LENGTH_LONG).show();
                Intent i = new Intent(context, TVShowDetailActivity.class);
                i.putExtra("tvshowdetailactivity", movieId);
                context.startActivity(i);
            }
        });
        // holder.posterThumbnail.setImageResource();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder{

        ImageView posterThumbnail;
        TextView title;
        TextView releaseYear;
        TextView overView;

        public TvViewHolder(View itemView) {
            super(itemView);
            posterThumbnail = (ImageView)itemView.findViewById(R.id.posterThumbnail);
            title = (TextView)itemView.findViewById(R.id.title);
            releaseYear = (TextView)itemView.findViewById(R.id.release_date);
            overView = (TextView)itemView.findViewById(R.id.overview);
        }
    }
}