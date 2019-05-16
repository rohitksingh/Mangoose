package com.omdb.rohksin.Mangoose.Redesign.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;
import com.omdb.rohksin.Mangoose.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageLandingListAdapter extends RecyclerView.Adapter<ImageLandingListAdapter.ImageHolder> {




    private List<String> list;
    private Context context;

    public ImageLandingListAdapter(List<String> list,Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.landing_image,parent,false);
        ImageHolder pvh = new ImageHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        // holder.personName.setText(list.get(position).getName());

        final String imagePath = list.get(position);

        final String thumb = MovieUtils.imageURL(imagePath);
        Log.d("Thumb", thumb);
        Picasso.with(context)
                .load(thumb)
                .into(holder.landingImage);

        holder.landingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ABC","ABC");
                Toast.makeText(context, "Image Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder{

        ImageView landingImage;

        public ImageHolder(View itemView) {
            super(itemView);
            landingImage = (ImageView)itemView.findViewById(R.id.landing_image);
        }
    }
}