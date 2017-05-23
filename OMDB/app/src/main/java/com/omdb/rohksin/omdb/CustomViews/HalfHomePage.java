package com.omdb.rohksin.omdb.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Illuminati on 5/19/2017.
 */
public class HalfHomePage extends RelativeLayout{

    public HalfHomePage(Context context) {
        super(context);
    }

    public HalfHomePage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HalfHomePage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int a,int b)
    {
        super.onMeasure(a, b);

        setMeasuredDimension(getMeasuredWidth(), getHeight() / 2);
    }
}
