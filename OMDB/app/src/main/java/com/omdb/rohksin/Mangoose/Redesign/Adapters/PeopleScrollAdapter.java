package com.omdb.rohksin.Mangoose.Redesign.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.R;
import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Cast;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PeopleScrollAdapter extends RecyclerView.Adapter<PeopleScrollAdapter.PeopleViewHolder> {

    private Context context;
    private List<Cast> casts;

    public PeopleScrollAdapter(Context context, List<Cast> casts) {
        this.context = context;
        this.casts = casts;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.crew_card, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {

        Cast cast = casts.get(position);
        holder.name.setText(cast.name);
        holder.characterName.setText(cast.character);

        Picasso.with(context)
                .load(MovieUtils.imageURL(cast.profile_path))
                .error(R.drawable.placeholder)
                .into(holder.profileImage);

    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder{

        private ImageView profileImage;
        private TextView name;
        private TextView characterName;

        public PeopleViewHolder(View itemView) {
            super(itemView);
            profileImage = (ImageView)itemView.findViewById(R.id.crewImage);
            name = (TextView)itemView.findViewById(R.id.crewName);
            characterName = (TextView)itemView.findViewById(R.id.crewRole);
        }
    }

}
