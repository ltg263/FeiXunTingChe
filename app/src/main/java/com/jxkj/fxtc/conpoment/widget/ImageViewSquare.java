package com.jxkj.fxtc.conpoment.widget;

import android.content.Context;
import android.util.AttributeSet;

public class ImageViewSquare extends androidx.appcompat.widget.AppCompatImageView {
    public ImageViewSquare(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public ImageViewSquare(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub

        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

    }

}
