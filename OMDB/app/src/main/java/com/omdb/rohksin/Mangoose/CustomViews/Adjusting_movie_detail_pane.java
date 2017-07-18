package com.omdb.rohksin.Mangoose.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Illuminati on 5/11/2017.
 */
public class Adjusting_movie_detail_pane extends RelativeLayout {
    public Adjusting_movie_detail_pane(Context context) {
        super(context);
    }

    public Adjusting_movie_detail_pane(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Adjusting_movie_detail_pane(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int a,int b)
    {
        super.onMeasure(a,b);
        setMeasuredDimension(getMeasuredWidth(), (int)((getMeasuredWidth())*(0.3)));
    }
}
