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

    @Override
    protected void onCreate(Bundle savedInstanceStste)
    {
        super.onCreate(savedInstanceStste);
        setContentView(R.layout.test);

        LinearLayout test = (LinearLayout)findViewById(R.id.rating_pane);

        inflater = (LayoutInflater) Test.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        CardView tab1 = provideTab("8.4");
        CardView tab2 = provideTab("9.4");


        test.addView(tab1);
        test.addView(tab2);

    }

    public CardView provideTab(String rating)
    {
        //LinearLayout tabs = (LinearLayout)inflater.inflate(R.layout.rating_tab,null);
        CardView cardView = (CardView)inflater.inflate(R.layout.rating_tab,null);
        LinearLayout linearLayout = (LinearLayout)cardView.findViewById(R.id.rating_tab);
        ImageView imageView = (ImageView)linearLayout.findViewById(R.id.rating_com_icon);
        TextView textView = (TextView)linearLayout.findViewById(R.id.rating_score);
        imageView.setImageResource(R.drawable.imdb);
        textView.setText(rating);
        return cardView;
    }
}
