package com.omdb.rohksin.omdb.Adaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omdb.rohksin.omdb.NewSearch.POJO.Actor;
import com.omdb.rohksin.omdb.NewSearch.Utility.MovieUtils;
import com.omdb.rohksin.omdb.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class ActorsListAdapter  extends RecyclerView.Adapter<ActorsListAdapter.ActorViewHolder> {


        List<Actor> list;
        Context context;

        public ActorsListAdapter(List<Actor> list, Context context) {
        this.list = list;
        this.context = context;

        }


@Override
public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_landing_single_item, parent, false);
        ActorViewHolder actorViewHolder = new ActorViewHolder(view);
        return actorViewHolder;
        }

@Override
public void onBindViewHolder(ActorViewHolder holder, int position) {

        Actor actor = list.get(position);

        Log.d("ImagePICASSO", actor.toString());

        holder.actorName.setText(actor.getName() + "\n As");
        holder.characterName.setText(actor.getCharacterName());


    Picasso.with(context)
        .load(MovieUtils.imageURL(actor.getProfileImage()))
        .into(holder.actorImage);

        }

@Override
public int getItemCount() {
        //return list.size();
        return list.size();
        }

public class ActorViewHolder extends RecyclerView.ViewHolder{

    private ImageView actorImage;
    private TextView actorName;
    private TextView characterName;

    public ActorViewHolder(View itemView) {
        super(itemView);
        //mediaImage = (ImageView)itemView.findViewById(R.id.MediaImage);
        actorImage = (ImageView)itemView.findViewById(R.id.actorImage);
        actorName =(TextView)itemView.findViewById(R.id.actorName);
        characterName =(TextView)itemView.findViewById(R.id.characterName);

    }
}
}
