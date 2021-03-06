package com.omdb.rohksin.Mangoose.Redesign.Adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Cast;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.AppUtility;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;
import com.omdb.rohksin.Mangoose.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class ActorsListAdapter  extends RecyclerView.Adapter<ActorsListAdapter.ActorViewHolder> {


        public static final String ACTOR_ID = "com.omdb.rohksin.omdb.Adaters.ActorsListAdapter.ACTOR_ID";
        private List<Cast> list;
        private Context context;

        public ActorsListAdapter(List<Cast> list, Context context) {
        this.list = list;
        this.context = context;

        }


@Override
public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_actor, parent, false);
        ActorViewHolder actorViewHolder = new ActorViewHolder(view);
        return actorViewHolder;
        }

@Override
public void onBindViewHolder(final ActorViewHolder holder, int position) {

        final Cast actor = list.get(position);

        Log.d("ImagePICASSO", actor.toString());

        holder.actorName.setText(actor.name);
        holder.characterName.setText(actor.character);

        holder.actorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppUtility.startPeopleDetailActivityWithAnim(context, actor.id+"", holder.actorImage);
            }
        });

    Picasso.with(context)
        .load(MovieUtils.imageURL(actor.profile_path))
            .error(R.drawable.placeholder)
        .into(holder.actorImage);

        }

@Override
public int getItemCount() {
        return list.size();
        }

public class ActorViewHolder extends RecyclerView.ViewHolder{

    private CircleImageView actorImage;
    private TextView actorName;
    private TextView characterName;

    public ActorViewHolder(View itemView) {
        super(itemView);
        //mediaImage = (ImageView)itemView.findViewById(R.id.MediaImage);
        actorImage = (CircleImageView)itemView.findViewById(R.id.actorImage);
        actorName =(TextView)itemView.findViewById(R.id.actorName);
        characterName =(TextView)itemView.findViewById(R.id.characterName);

        if(Build.VERSION.SDK_INT>20)
        actorImage.setTransitionName("ACTOR");

    }
}
}
