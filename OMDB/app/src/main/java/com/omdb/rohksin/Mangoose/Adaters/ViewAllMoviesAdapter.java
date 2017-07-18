package com.omdb.rohksin.Mangoose.Adaters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.BlankActivity;
import com.omdb.rohksin.Mangoose.NewSearch.POJO.MovieRole;
import com.omdb.rohksin.Mangoose.NewSearch.Utility.MovieUtils;
import com.omdb.rohksin.Mangoose.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Illuminati on 6/28/2017.
 */
public class ViewAllMoviesAdapter extends RecyclerView.Adapter<ViewAllMoviesAdapter.PosterHolder> {

    private List<MovieRole> list;
    private Context context;

    public ViewAllMoviesAdapter(List<MovieRole> list,Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public PosterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_poster_card, parent, false);
        PosterHolder pvh = new PosterHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PosterHolder holder, int position) {

        final MovieRole movieRole= list.get(position);
        //setFadeAnimation(holder.itemView);
        final String imagePath = movieRole.getMoviePosterPath();
        final String thumb = MovieUtils.imageURL(imagePath);
        Log.d("Thumb", thumb);
        Picasso.with(context)
                .load(thumb).error(R.drawable.placeholder)
                .into(holder.moviePoster);

        holder.movieName.setText(movieRole.getMovieName());
        holder.releaseDate.setText(MovieUtils.getFormattedDate(movieRole.getReleaseDate()));
        holder.characterName.setText(movieRole.getCharacterName());

        holder.movieName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, BlankActivity.class);
                i.putExtra("blankActivityText",movieRole.getMovieId());

                if(Build.VERSION.SDK_INT>20)
                {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, holder.moviePoster, "ImageView");
                    context.startActivity(i, options.toBundle());
                }
                else {
                    context.startActivity(i);
                }
            }
        });

        holder.moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MovieUtils.previewImage(context,movieRole.getMoviePosterPath());
                MovieUtils.previewImageWithAnimation(context,movieRole.getMoviePosterPath(),holder.moviePoster,"ImageView");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class PosterHolder extends RecyclerView.ViewHolder{

        ImageView moviePoster;
        TextView movieName;
        TextView releaseDate;
        TextView characterName;

        public PosterHolder(View itemView) {
            super(itemView);
            moviePoster = (ImageView)itemView.findViewById(R.id.posterThumbnail);
            movieName = (TextView)itemView.findViewById(R.id.title);
            releaseDate = (TextView)itemView.findViewById(R.id.release_date);
            characterName = (TextView)itemView.findViewById(R.id.characterName);
            if(Build.VERSION.SDK_INT>20)
            moviePoster.setTransitionName("ImageView");
        }

    }


    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }


}