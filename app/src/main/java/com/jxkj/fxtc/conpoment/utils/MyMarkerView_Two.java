
package com.jxkj.fxtc.conpoment.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.jxkj.fxtc.R;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
@SuppressLint("ViewConstructor")
public class MyMarkerView_Two extends MarkerView {

    private final TextView tvContent;

    public MyMarkerView_Two(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = findViewById(R.id.tvContent);
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
            double v= Double.valueOf(Utils.formatNumber(ce.getHigh(), 0, true))/100;
            tvContent.setText(String.valueOf(v));
        } else {
            double v= Double.valueOf(Utils.formatNumber(e.getY(), 0, true))/100;
            tvContent.setText(String.valueOf(v));
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
