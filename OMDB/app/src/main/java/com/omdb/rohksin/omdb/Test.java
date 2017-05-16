package com.omdb.rohksin.omdb;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Illuminati on 5/14/2017.
 */
public class Test extends AppCompatActivity {

    private  LayoutInflater inflater;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceStste)
    {
        super.onCreate(savedInstanceStste);
        setContentView(R.layout.test);

        inflater = (LayoutInflater) Test.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         layout = (LinearLayout)findViewById(R.id.test);

        LinearLayout tab = provideTab(R.drawable.rottentomatoes);
        LinearLayout tab1 = provideTab(R.drawable.rottentomatoes);
        LinearLayout tab2 = provideTab(R.drawable.imdb);

        layout.addView(tab);
        layout.addView(tab1);
        layout.addView(tab2);

    }

    public LinearLayout provideTab(int id)
    {

        LinearLayout cardView = (LinearLayout)inflater.inflate(R.layout.test_image,layout,false);

        ImageView imageView = (ImageView)cardView.findViewById(R.id.testImage);
        imageView.setImageResource(id);

        return cardView;
    }
}
