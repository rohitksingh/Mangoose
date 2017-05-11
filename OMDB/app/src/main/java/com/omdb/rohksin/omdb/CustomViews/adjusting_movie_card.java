package com.omdb.rohksin.omdb.CustomViews;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by Illuminati on 5/11/2017.
 */
public class adjusting_movie_card extends CardView {

    public adjusting_movie_card(Context context) {
        super(context);
    }

    public adjusting_movie_card(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public adjusting_movie_card(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int a,int b)
    {
        super.onMeasure(a,b);
        setMeasuredDimension(getMeasuredWidth(),getMeasuredWidth());
    }
}
