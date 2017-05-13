package com.omdb.rohksin.omdb;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.omdb.rohksin.omdb.ObjectMaps.MovieResponse;
import com.squareup.picasso.Picasso;


/**
 * Created by Illuminati on 5/8/2017.
 */
public class MovieActivity extends AppCompatActivity{

    private ImageView moviePoster;
    private CollapsingToolbarLayout title;
    private TextView moviePlot;

    private LinearLayout non_actors;
    private TextView director;
    private TextView writer;
    private TextView production;

    private LinearLayout movie_specs;
    private TextView genre;
    private TextView releaseDate;
    private TextView runtime;

    private LinearLayout records;
    private TextView awards;
    private TextView boxOffice;
    private TextView website;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.movie_cord_layout);

        moviePoster = (ImageView)findViewById(R.id.moviePoster);
        title =(CollapsingToolbarLayout)findViewById(R.id.title);
        moviePlot =(TextView)findViewById(R.id.movie_plot);

        non_actors =(LinearLayout)findViewById(R.id.brains);
        director =(TextView)non_actors.findViewById(R.id.director);
        writer =(TextView)non_actors.findViewById(R.id.writer);
        production =(TextView)non_actors.findViewById(R.id.production);

        movie_specs = (LinearLayout)findViewById(R.id.movie_specs);
        genre =(TextView)movie_specs.findViewById(R.id.genre);
        releaseDate =(TextView)movie_specs.findViewById(R.id.release_date);
        runtime =(TextView)movie_specs.findViewById(R.id.runtime);

        records = (LinearLayout)findViewById(R.id.records);
        awards =(TextView)records.findViewById(R.id.awards);
        boxOffice =(TextView)records.findViewById(R.id.boxoffice);
        website =(TextView)records.findViewById(R.id.website);

        MovieResponse response = (MovieResponse)getIntent().getSerializableExtra(ResposeObserver.RESPONSE);

        Picasso.with(MovieActivity.this)
                .load(response.getPoster())
                .into(moviePoster);

        String movieNDate = response.getTitle()+ (response.getYear()!=null ? "("+response.getYear()+")" : "");
        title.setTitle(movieNDate);
        moviePlot.setText(response.getFullPlot());
        director.setText(response.getDirector());
        writer.setText(response.getWriter());
        production.setText(response.getProduction());

        genre.setText(response.getGenre());
        runtime.setText(response.getRuntime());
        releaseDate.setText(response.getReleasedDate());

        awards.setText(response.getAwards());
        boxOffice.setText(response.getBoxoffice());
        website.setText(response.getWebsite());

    }
}
