package com.omdb.rohksin.omdb.NewSearch.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Illuminati on 7/1/2017.
 */


public class AdjustingTextView extends ImageView {
    public AdjustingTextView(Context context) {
        super(context);
    }

    public AdjustingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdjustingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onMeasure(int a,int b)
    {
        super.onMeasure(a, b);
        setMeasuredDimension(getMeasuredWidth()/2,(getMeasuredWidth()));
    }
}