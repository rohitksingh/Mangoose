package com.omdb.rohksin.omdb.Adaters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omdb.rohksin.omdb.BlankActivity;
import com.omdb.rohksin.omdb.Movie;
import com.omdb.rohksin.omdb.NewSearch.Utility.MovieUtils;
import com.omdb.rohksin.omdb.R;
import com.omdb.rohksin.omdb.SearchActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Illuminati on 5/23/2017.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MovieViewHolder> {




    private List<Movie> list;
    private Context context;

    public RvAdapter(List<Movie> list,Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
        MovieViewHolder pvh = new MovieViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
       // holder.personName.setText(list.get(position).getName());
        final String movieId = list.get(position).getMovieId();
        holder.title.setText(list.get(position).getName());
        holder.releaseYear.setText(MovieUtils.getFormattedDate(list.get(position).getReleaseYear()));
        holder.overView.setText(list.get(position).getOverview());


        String thumb = list.get(position).getPosterThumbnail();
        //thumb = "http://image.tmdb.org/t/p/w185"+thumb;
        thumb = MovieUtils.imageURL(thumb);
        Log.d("Thumb",thumb);
        Picasso.with(context)
                .load(thumb)
                .into(holder.posterThumbnail);

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, BlankActivity.class);
                i.putExtra("blankActivityText", movieId);



                if(Build.VERSION.SDK_INT>20)
                {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)context,holder.posterThumbnail,"ImageView");

                context.startActivity(i, options.toBundle());
                }
                else
                {

                }

            }
        });
       // holder.posterThumbnail.setImageResource();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        ImageView posterThumbnail;
        TextView title;
        TextView releaseYear;
        TextView overView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            posterThumbnail = (ImageView)itemView.findViewById(R.id.posterThumbnail);
            title = (TextView)itemView.findViewById(R.id.title);
            releaseYear = (TextView)itemView.findViewById(R.id.release_date);
            overView = (TextView)itemView.findViewById(R.id.overview);

            if(Build.VERSION.SDK_INT>20) {
                posterThumbnail.setTransitionName("ImageView");
                title.setTransitionName("title");
            }
        }
    }
}