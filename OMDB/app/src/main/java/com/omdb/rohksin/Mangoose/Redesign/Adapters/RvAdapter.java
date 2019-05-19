package com.omdb.rohksin.Mangoose.Redesign.Adapters;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.Movie;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.AppUtility;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;
import com.omdb.rohksin.Mangoose.R;
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie,parent,false);
        MovieViewHolder pvh = new MovieViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {

        final String movieId = list.get(position).getMovieId();
        holder.title.setText(list.get(position).getName());
        holder.releaseYear.setText(MovieUtils.getFormattedDate(list.get(position).getReleaseYear()));
        holder.overView.setText(list.get(position).getOverview());


        String thumb = list.get(position).getPosterThumbnail();
        thumb = MovieUtils.imageURL(thumb);
        Picasso.with(context)
                .load(thumb).error(R.drawable.placeholder)
                .into(holder.posterThumbnail);

        holder.mainCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppUtility.startMovieDetailActiivtyWithAnim(context, movieId, holder.posterThumbnail);

            }
        });

        holder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT>20) {
                    int x = holder.moreInfoCard.getRight();
                    int y = holder.moreInfoCard.getBottom();
                    int radius = (int) Math.hypot(holder.moreInfoCard.getWidth(), holder.moreInfoCard.getHeight());
                    Animator animator = ViewAnimationUtils.createCircularReveal(holder.moreInfoCard, x, y, 0, radius);
                    animator.setDuration(500);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    holder.moreInfoCard.setVisibility(View.VISIBLE);

                    if(list.get(position).getGenres().length!=0)
                    {
                        holder.genres.setText(MovieUtils.getGenre(list.get(position).getGenres()));
                        holder.genres.setVisibility(View.VISIBLE);
                    }

                    holder.originalLanguage.setText(list.get(position).getOriginalLanguage());

                    animator.start();
                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            holder.mainCard.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

                }
                else
                {
                    holder.mainCard.setVisibility(View.INVISIBLE);
                    holder.moreInfoCard.setVisibility(View.VISIBLE);
                }

            }
        });

        holder.moreInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>20) {
                    int x = holder.moreInfoCard.getRight();
                    int y = holder.moreInfoCard.getBottom();
                    int radius = (int) Math.hypot(holder.moreInfoCard.getWidth(), holder.moreInfoCard.getHeight());
                    Animator animator = ViewAnimationUtils.createCircularReveal(holder.moreInfoCard, x, y, radius, 0);
                    animator.setDuration(500);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    holder.mainCard.setVisibility(View.VISIBLE);

                    animator.start();

                    animator.addListener(new Animator.AnimatorListener() {

                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {

                            holder.moreInfoCard.setVisibility(View.INVISIBLE);

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

                }
                else
                {
                    holder.moreInfoCard.setVisibility(View.INVISIBLE);
                    holder.mainCard.setVisibility(View.VISIBLE);
                }
            }
        });

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
        TextView viewMore;
        View mainCard;
        View moreInfoCard;
        ImageView moreInfoBack;
        TextView genres;
        TextView originalLanguage;

        public MovieViewHolder(View itemView) {
            super(itemView);
            posterThumbnail = (ImageView)itemView.findViewById(R.id.posterThumbnail);
            title = (TextView)itemView.findViewById(R.id.title);
            releaseYear = (TextView)itemView.findViewById(R.id.release_date);
            overView = (TextView)itemView.findViewById(R.id.overview);
            viewMore = (TextView)itemView.findViewById(R.id.moreInfo);

            mainCard = (View)itemView.findViewById(R.id.mainCard);
            moreInfoCard = (View)itemView.findViewById(R.id.viewMoreMovieCard);
            moreInfoBack = (ImageView)moreInfoCard.findViewById(R.id.backToCard);

            genres = (TextView)moreInfoCard.findViewById(R.id.genres);
            originalLanguage = (TextView)moreInfoCard.findViewById(R.id.original_language);

            if(Build.VERSION.SDK_INT>20) {
                posterThumbnail.setTransitionName("ImageView");
                title.setTransitionName("title");
            }
        }
    }
}