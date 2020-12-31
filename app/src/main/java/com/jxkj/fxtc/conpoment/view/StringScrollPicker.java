package com.jxkj.fxtc.conpoment.view;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.Layout.Alignment;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import cn.forward.androids.R.styleable;
import cn.forward.androids.utils.ColorUtil;
import cn.forward.androids.views.ScrollPickerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringScrollPicker extends ScrollPickerView<CharSequence> {
    private int mMeasureWidth;
    private int mMeasureHeight;
    private TextPaint mPaint;
    private int mMinTextSize;
    private int mMaxTextSize;
    private int mStartColor;
    private int mEndColor;
    private int mMaxLineWidth;
    private Alignment mAlignment;

    public StringScrollPicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StringScrollPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mMinTextSize = 24;
        this.mMaxTextSize = 32;
        this.mStartColor = -16777216;
        this.mEndColor = -7829368;
        this.mMaxLineWidth = -1;
        this.mAlignment = Alignment.ALIGN_CENTER;
        this.mPaint = new TextPaint(1);
        this.mPaint.setStyle(Style.FILL);
        this.mPaint.setColor(-16777216);
        this.init(attrs);
        this.setData(new ArrayList(Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve")));
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = this.getContext().obtainStyledAttributes(attrs, styleable.StringScrollPicker);
            this.mMinTextSize = typedArray.getDimensionPixelSize(styleable.StringScrollPicker_spv_min_text_size, this.mMinTextSize);
            this.mMaxTextSize = typedArray.getDimensionPixelSize(styleable.StringScrollPicker_spv_max_text_size, this.mMaxTextSize);
            this.mStartColor = typedArray.getColor(styleable.StringScrollPicker_spv_start_color, this.mStartColor);
            this.mEndColor = typedArray.getColor(styleable.StringScrollPicker_spv_end_color, this.mEndColor);
            this.mMaxLineWidth = typedArray.getDimensionPixelSize(styleable.StringScrollPicker_spv_max_line_width, this.mMaxLineWidth);
            int align = typedArray.getInt(styleable.StringScrollPicker_spv_alignment, 1);
            if (align == 2) {
                this.mAlignment = Alignment.ALIGN_NORMAL;
            } else if (align == 3) {
                this.mAlignment = Alignment.ALIGN_OPPOSITE;
            } else {
                this.mAlignment = Alignment.ALIGN_CENTER;
            }

            typedArray.recycle();
        }

    }

    public void setColor(int startColor, int endColor) {
        this.mStartColor = startColor;
        this.mEndColor = endColor;
        this.invalidate();
    }

    public void setTextSize(int minText, int maxText) {
        this.mMinTextSize = minText;
        this.mMaxTextSize = maxText;
        this.invalidate();
    }

    public int getStartColor() {
        return this.mStartColor;
    }

    public int getEndColor() {
        return this.mEndColor;
    }

    public int getMinTextSize() {
        return this.mMinTextSize;
    }

    public int getMaxTextSize() {
        return this.mMaxTextSize;
    }

    public int getMaxLineWidth() {
        return this.mMaxLineWidth;
    }

    public void setMaxLineWidth(int maxLineWidth) {
        this.mMaxLineWidth = maxLineWidth;
    }

    public Alignment getAlignment() {
        return this.mAlignment;
    }

    public void setAlignment(Alignment alignment) {
        this.mAlignment = alignment;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mMeasureWidth = this.getMeasuredWidth();
        this.mMeasureHeight = this.getMeasuredHeight();
        if (this.mMaxLineWidth < 0) {
            this.mMaxLineWidth = this.getItemWidth();
        }

    }

    public void drawItem(Canvas canvas, List<CharSequence> data, int position, int relative, float moveLength, float top) {
        CharSequence text = (CharSequence)data.get(position);
        int itemSize = this.getItemSize();
        if (relative == -1) {
            if (moveLength < 0.0F) {
                this.mPaint.setTextSize((float)this.mMinTextSize);
            } else {
                this.mPaint.setTextSize((float)this.mMinTextSize + (float)(this.mMaxTextSize - this.mMinTextSize) * moveLength / (float)itemSize);
            }
        } else if (relative == 0) {
            this.mPaint.setTextSize((float)this.mMinTextSize + (float)(this.mMaxTextSize - this.mMinTextSize) * ((float)itemSize - Math.abs(moveLength)) / (float)itemSize);
        } else if (relative == 1) {
            if (moveLength > 0.0F) {
                this.mPaint.setTextSize((float)this.mMinTextSize);
            } else {
                this.mPaint.setTextSize((float)this.mMinTextSize + (float)(this.mMaxTextSize - this.mMinTextSize) * -moveLength / (float)itemSize);
            }
        } else {
            this.mPaint.setTextSize((float)this.mMinTextSize);
        }

        StaticLayout layout = new StaticLayout(text, 0, text.length(), this.mPaint, this.mMaxLineWidth, this.mAlignment, 1.0F, 0.0F, true, (TruncateAt)null, 0);
        float x = 0.0F;
        float y = 0.0F;
        float lineWidth = (float)layout.getWidth();
        if (this.isHorizontal()) {
            x = top + ((float)this.getItemWidth() - lineWidth) / 2.0F;
            y = (float)((this.getItemHeight() - layout.getHeight()) / 2);
        } else {
            x = ((float)this.getItemWidth() - lineWidth) / 2.0F;
            y = top + (float)((this.getItemHeight() - layout.getHeight()) / 2);
        }
        if(relative == 0){
            x = top + ((float)this.getItemWidth() - lineWidth) / 2.0F;
            y = (float)((this.getItemHeight() - layout.getHeight()) / 2)-30;
        }
        this.computeColor(relative, itemSize, moveLength);
        canvas.save();
        canvas.translate(x, y);
        layout.draw(canvas);
        canvas.restore();
    }

    private void computeColor(int relative, int itemSize, float moveLength) {
        int color = this.mEndColor;
        float rate;
        if (relative != -1 && relative != 1) {
            if (relative == 0) {
                rate = Math.abs(moveLength) / (float)itemSize;
                color = ColorUtil.computeGradientColor(this.mStartColor, this.mEndColor, rate);
            }
        } else if ((relative != -1 || moveLength >= 0.0F) && (relative != 1 || moveLength <= 0.0F)) {
            rate = ((float)itemSize - Math.abs(moveLength)) / (float)itemSize;
            color = ColorUtil.computeGradientColor(this.mStartColor, this.mEndColor, rate);
        } else {
            color = this.mEndColor;
        }

        this.mPaint.setColor(color);
    }
}

