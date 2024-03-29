/*
 * Copyright (C) 2016 venshine.cn@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except ic_bnt_no compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to ic_bnt_no writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wx.wheelview.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.wx.wheelview.common.WheelConstants;
import com.wx.wheelview.widget.WheelView;

/**
 * holo滚轮样式
 *
 * @author venshine
 */
public class HoloDrawable extends WheelDrawable {

    private Paint mHoloBgPaint, mHoloPaint;

    private int mWheelSize, mItemH;

    HoloDrawable(int width, int height, WheelView.WheelViewStyle style, int wheelSize, int itemH) {
        super(width, height, style);
        mWheelSize = wheelSize;
        mItemH = itemH;
        init();
    }

    private void init() {
        mHoloBgPaint = new Paint();
        mHoloBgPaint.setColor(mStyle.backgroundColor != -1 ? mStyle.backgroundColor : WheelConstants
                .WHEEL_SKIN_HOLO_BG);

        mHoloPaint = new Paint();
        mHoloPaint.setStrokeWidth(mStyle.holoBorderWidth != -1 ? mStyle.holoBorderWidth : 3);
        mHoloPaint.setColor(mStyle.holoBorderColor != -1 ? mStyle.holoBorderColor : WheelConstants
                .WHEEL_SKIN_HOLO_BORDER_COLOR);
    }


    @Override
    public void draw(Canvas canvas) {
        // draw background
        canvas.drawRect(0, 0, mWidth, mHeight, mHoloBgPaint);

        // draw select border
        if (mItemH != 0) {
            int size = mWheelSize >> 1;
            canvas.drawLine(0, mItemH * size, mWidth, mItemH
                    * size, mHoloPaint);
            canvas.drawLine(0, mItemH * (size + 1), mWidth, mItemH
                    * (size + 1), mHoloPaint);
        }
    }
}
