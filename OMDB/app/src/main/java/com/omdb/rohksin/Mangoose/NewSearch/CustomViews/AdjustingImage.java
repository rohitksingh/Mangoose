package com.omdb.rohksin.Mangoose.NewSearch.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Illuminati on 6/21/2017.
 */
public class AdjustingImage extends ImageView {
    public AdjustingImage(Context context) {
        super(context);
    }

    public AdjustingImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdjustingImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onMeasure(int a,int b)
    {
        super.onMeasure(a, b);
        setMeasuredDimension(getMeasuredWidth(),(int)(getMeasuredWidth()*(0.7)));
    }
}
