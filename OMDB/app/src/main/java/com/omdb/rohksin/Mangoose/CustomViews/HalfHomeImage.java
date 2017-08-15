package com.omdb.rohksin.Mangoose.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Illuminati on 5/19/2017.
 */
public class HalfHomeImage extends ImageView{
    public HalfHomeImage(Context context) {
        super(context);
    }

    public HalfHomeImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HalfHomeImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int a,int b)
    {
        super.onMeasure(a,b);
        setMeasuredDimension(getMeasuredWidth(), (int)(getMeasuredHeight()*0.7));
    }


}
