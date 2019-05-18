package com.omdb.rohksin.Mangoose.Redesign.Adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.Redesign.Models.PeopleDetail;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.AppUtility;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;
import com.omdb.rohksin.Mangoose.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Illuminati on 6/25/2017.
 */
public class PeopleListAdapter extends RecyclerView.Adapter<PeopleListAdapter.PeopleViewHolder> {


    private ArrayList<PeopleDetail> list;
    private Context context;

    public PeopleListAdapter(ArrayList<PeopleDetail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_list_item, parent, false);

        PeopleViewHolder pvh = new PeopleViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PeopleViewHolder holder, int position) {
        // holder.personName.setText(list.get(position).getName());
        final String movieId = list.get(position).getId();
        holder.title.setText(list.get(position).getName());

        holder.knownFor.setText(list.get(position).getKnownForMovies());

        String thumb = list.get(position).getPeofileImage();
        //thumb = "http://image.tmdb.org/t/p/w185"+thumb;
        thumb = MovieUtils.imageURL(thumb);
        Log.d("Thumb", thumb);
        Picasso.with(context)
                .load(thumb).error(R.drawable.placeholder)
                .into(holder.posterThumbnail);


        holder.mainCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                Intent i = new Intent(context, PeopleDetailActivity.class);
                i.putExtra(ActorsListAdapter.ACTOR_ID, movieId);

                if(Build.VERSION.SDK_INT>20)
                {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)context,holder.posterThumbnail,"ACTOR");
                    context.startActivity(i,options.toBundle());
                }
                else {
                    context.startActivity(i);
                }
                */

                AppUtility.startPeopleDetailActivityWithAnim(context, movieId, holder.posterThumbnail);
            }
        });
        // holder.posterThumbnail.setImageResource();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder {

        ImageView posterThumbnail;
        TextView title;
        TextView knownFor;
        View mainCard;


        public PeopleViewHolder(View itemView) {
            super(itemView);
            posterThumbnail = (ImageView) itemView.findViewById(R.id.posterThumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            knownFor = (TextView)itemView.findViewById(R.id.overview);
            mainCard = (View)itemView.findViewById(R.id.peoplemainCard);

            if(Build.VERSION.SDK_INT>20)
            posterThumbnail.setTransitionName("ACTOR");


        }
    }

    {
    }
}
