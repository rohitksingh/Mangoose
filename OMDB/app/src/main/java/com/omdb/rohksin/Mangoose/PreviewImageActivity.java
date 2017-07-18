package com.omdb.rohksin.Mangoose;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.github.chrisbanes.photoview.PhotoView;
import com.omdb.rohksin.Mangoose.NewSearch.Utility.MovieUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by Illuminati on 6/28/2017.
 */
public class PreviewImageActivity extends AppCompatActivity{

    private PhotoView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.preview_image_layout);
        String imagePath = (String )getIntent().getStringExtra(MovieUtils.PREVIEW_IMAGE);
        imageView = (PhotoView)findViewById(R.id.previewImage);

        if(Build.VERSION.SDK_INT>20)
        {
            imageView.setTransitionName("ImageView");
        }

        Picasso.with(this)
                .load(MovieUtils.imageHighURL(imagePath))
                .fit()
                .into(imageView);



    }
}
