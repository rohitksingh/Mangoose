package com.omdb.rohksin.omdb;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.omdb.rohksin.omdb.CustomViews.HalfHomeImage;
import com.omdb.rohksin.omdb.CustomViews.HalfHomePage;

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

       // inflater = (LayoutInflater) Test.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       // createHome();

        setContentView(R.layout.home);



        //Log.d("Dim", layout.getWidth() + " " + layout.getHeight());



        //LinearLayout HomePage = (LinearLayout)findViewById(R.id.)



        /*
        inflater = (LayoutInflater) Test.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         layout = (LinearLayout)findViewById(R.id.test);

        LinearLayout tab = provideTab(R.drawable.rottentomatoes);
        LinearLayout tab1 = provideTab(R.drawable.rottentomatoes);
        LinearLayout tab2 = provideTab(R.drawable.imdb);

        layout.addView(tab);
        layout.addView(tab1);
        layout.addView(tab2);
*/
    }

    public ImageView getImage(ViewGroup parent)
    {
        HalfHomeImage image = (HalfHomeImage)inflater.inflate(R.layout.half_image,parent,false);
        return  image;
    }


    public LinearLayout getHalfHomePage(ViewGroup parent)
    {
        HalfHomePage homePage = (HalfHomePage)inflater.inflate(R.layout.half_page,parent,false);
        ImageView imageView1 = getImage(homePage);
        ImageView imageView2 = getImage(homePage);
        homePage.addView(imageView1);
        homePage.addView(imageView2);
        return null;
    }

    public void createHome()
    {
        LinearLayout home = (LinearLayout) findViewById(R.id.homePage);
        LinearLayout first = getHalfHomePage(home);
        LinearLayout second = getHalfHomePage(home);
        home.addView(first);
        home.addView(second);
    }

    public LinearLayout provideTab(int id)
    {

        LinearLayout cardView = (LinearLayout)inflater.inflate(R.layout.test_image,layout,false);

        ImageView imageView = (ImageView)cardView.findViewById(R.id.testImage);
        imageView.setImageResource(id);

        return cardView;
    }
}
