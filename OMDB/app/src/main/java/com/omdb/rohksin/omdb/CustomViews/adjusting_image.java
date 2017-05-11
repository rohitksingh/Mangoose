package com.omdb.rohksin.omdb.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Illuminati on 5/11/2017.
 */
public class adjusting_image extends ImageView {

    public adjusting_image(Context context) {
        super(context);
    }

    public adjusting_image(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public adjusting_image(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

   @Override
   public void onMeasure(int a,int b)
   {
       super.onMeasure(a,b);
       setMeasuredDimension(getMeasuredWidth(),(int)((getMeasuredWidth())*(0.7)));
   }
}
