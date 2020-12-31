package com.jxkj.fxtc.conpoment.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by 99210 on 2017/8/30.
 */

public class ScrollListenableView extends ScrollView {

    private ScrollListener mScrollListener;

    public ScrollListenableView(Context context) {
        super(context);
    }

    public ScrollListenableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollListenableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollListener(ScrollListener listener){
        this.mScrollListener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollListener!=null){
            mScrollListener.onScrollviewScroll(ScrollListenableView.this,l,t,oldl,oldt);
        }
    }

    public interface ScrollListener{
        void onScrollviewScroll(ScrollListenableView view, int x, int y, int oldX, int oldY);
    }
}
