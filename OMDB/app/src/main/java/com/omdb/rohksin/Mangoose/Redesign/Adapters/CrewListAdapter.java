package com.omdb.rohksin.Mangoose.Redesign.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.Redesign.MoshiModels.Crew;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;
import com.omdb.rohksin.Mangoose.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class CrewListAdapter  extends RecyclerView.Adapter<CrewListAdapter.CrewViewHolder> {


    List<Crew> list;
    Context context;

    public CrewListAdapter(List<Crew> list, Context context) {
        this.list = list;
        this.context = context;

    }


    @Override
    public CrewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_crew, parent, false);
        CrewViewHolder actorViewHolder = new CrewViewHolder(view);
        return actorViewHolder;
    }

    @Override
    public void onBindViewHolder(CrewViewHolder holder, int position) {

        Crew crew = list.get(position);

        Log.d("ImagePICASSO", crew.toString());

        holder.crewName.setText(crew.name);
        holder.roleName.setText(crew.job);


        Picasso.with(context)
                .load(MovieUtils.imageURL(crew.profile_path)).error(R.drawable.placeholder)
                .into(holder.crewImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CrewViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView crewImage;
        private TextView crewName;
        private TextView roleName;

        public CrewViewHolder(View itemView) {
            super(itemView);
            //mediaImage = (ImageView)itemView.findViewById(R.id.MediaImage);
            crewImage = (CircleImageView)itemView.findViewById(R.id.crewImage);
            crewName =(TextView)itemView.findViewById(R.id.crewName);
            roleName =(TextView)itemView.findViewById(R.id.crewRole);

        }
    }
}
