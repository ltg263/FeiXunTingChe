package com.jxkj.fxtc.conpoment.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

public class MyWebView extends WebView {

    private OnTouchScreenListener onTouchScreenListener;

    public MyWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MyWebView(Context context) {
        super(context);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (onTouchScreenListener != null)
                onTouchScreenListener.onTouchScreen();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (onTouchScreenListener != null)
                onTouchScreenListener.onReleaseScreen();
        }

        return super.onTouchEvent(event);
    }

    public interface OnTouchScreenListener {
        void onTouchScreen();

        void onReleaseScreen();
    }

    public void setOnTouchScreenListener(OnTouchScreenListener onTouchScreenListener) {
        this.onTouchScreenListener = onTouchScreenListener;
    }
}
