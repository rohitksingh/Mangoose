package com.omdb.rohksin.Mangoose;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.omdb.rohksin.Mangoose.Redesign.Utilities.AppConstants;
import com.omdb.rohksin.Mangoose.Redesign.Utilities.MovieUtils;

/**
 * Created by Illuminati on 7/9/2017.
 */
public class ComingSoon extends AppCompatActivity {

    private Button share;
    private Button checkLatest;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coming_soon);
        getSupportActionBar().setTitle("Coming Soon :)");

        share = (Button)findViewById(R.id.share);
        checkLatest = (Button)findViewById(R.id.checkLatest);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieUtils.shareThisApp(ComingSoon.this);
            }
        });

        checkLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieUtils.openInBrowser(ComingSoon.this, AppConstants.APPSTORE_LINK);
            }
        });

    }
}
