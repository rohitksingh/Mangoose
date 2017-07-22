package com.omdb.rohksin.Mangoose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.omdb.rohksin.Mangoose.Constants.AppConstants;
import com.omdb.rohksin.Mangoose.NewSearch.Utility.MovieUtils;

/**
 * Created by Illuminati on 7/9/2017.
 */
public class ComingSoon extends AppCompatActivity {

    private TextView checkUpdates;
    private TextView share;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coming_soon);
        getSupportActionBar().setTitle("Coming Soon :)");

        checkUpdates = (TextView)findViewById(R.id.checkUpdates);
        share = (TextView)findViewById(R.id.share);

        checkUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieUtils.openInBrowser(ComingSoon.this, AppConstants.APPSTORE_LINK);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MovieUtils.shareThisApp(ComingSoon.this);
            }
        });

    }
}
