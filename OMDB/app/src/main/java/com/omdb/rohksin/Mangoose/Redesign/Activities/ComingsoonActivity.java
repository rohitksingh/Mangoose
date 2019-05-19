package com.omdb.rohksin.Mangoose.Redesign.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.omdb.rohksin.Mangoose.R;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.AppConstants;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;

/**
 * Created by Illuminati on 7/9/2017.
 */
public class ComingsoonActivity extends AppCompatActivity {

    private Button share;
    private Button checkLatest;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comingsoon);
        getSupportActionBar().setTitle("Coming Soon :)");

        share = (Button)findViewById(R.id.share);
        checkLatest = (Button)findViewById(R.id.checkLatest);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieUtils.shareThisApp(ComingsoonActivity.this);
            }
        });

        checkLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieUtils.openInBrowser(ComingsoonActivity.this, AppConstants.APPSTORE_LINK);
            }
        });

    }
}
