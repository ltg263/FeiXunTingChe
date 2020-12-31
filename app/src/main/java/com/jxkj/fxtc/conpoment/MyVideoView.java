package com.jxkj.fxtc.conpoment;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;

public class MyVideoView extends VideoView {
    private PlayListener playListener;

    public interface PlayListener {
        public void onPause();

        public void onStart();

    }

    public MyVideoView(Context context) {
        super(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPlayListener(PlayListener playListener) {
        this.playListener = playListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 其实就是在这里做了一些处理。
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    public void pause() {
        super.pause();
        if (playListener == null) {

        } else {
            playListener.onPause();
        }
    }

    @Override
    public void start() {
        super.start();
        if (playListener == null) {

        } else {
            playListener.onStart();
        }
    }
}
