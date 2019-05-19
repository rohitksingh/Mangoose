package com.omdb.rohksin.Mangoose.Redesign.Activities;

import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.R;

/**
 * Created by Illuminati on 7/10/2017.
 */
public class BiographyActivity extends AppCompatActivity{

    TextView bioGraphy;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_biography);
        bioGraphy = (TextView)findViewById(R.id.bioGraphy);
        String bio = (String)getIntent().getStringExtra("Biography");
        String Name = (String)getIntent().getStringExtra("Name");

        getSupportActionBar().setTitle("Biography: "+Name);
        bioGraphy.setText(bio);

    }

    public void setAnimation()
    {
        if(Build.VERSION.SDK_INT>20) {
            Fade fade = new Fade();
            fade.setDuration(1000);
            getWindow().setExitTransition(fade);
            getWindow().setEnterTransition(fade);
        }
    }
}
