package com.jxkj.fxtc.conpoment.widget;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by orgwcl on 2016/9/2.
 */
public class ViewPagerFix extends ViewPager {

    private boolean isScrollable = true;

    public ViewPagerFix(Context context) {
        super(context);
    }

    public ViewPagerFix(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean isScrollable) {
        this.isScrollable = isScrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isScrollable) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScrollable) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }
}
