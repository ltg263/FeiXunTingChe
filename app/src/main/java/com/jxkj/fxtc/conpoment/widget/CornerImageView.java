package com.jxkj.fxtc.conpoment.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jxkj.fxtc.R;

public class CornerImageView extends ImageView {
    private Paint mCirclePaint;//角标画笔
    private Paint mTextPaint;//角标数字画笔
    private String cornerText = "";//角标需要显示的内容
    private float textWidth;//字体单位宽度
    private float textSize = 50;//字体大小
    private float cornerTop = 0;//角标位置top距离
    private float cornerRight = 0;//角标位置right距离

    public CornerImageView(Context context) {
        super(context);
        init();
    }

    public CornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @SuppressLint("ResourceType")
    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(1);
        mCirclePaint.setColor(ContextCompat.getColor(getContext(), R.color.red));
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //如果角标无内容则不显示角标
        if (TextUtils.isEmpty(cornerText))
            return;
        //如果只有一位字符，只需要一个圆
        float circleCenterX = getWidth() - textWidth - getPaddingRight() - cornerRight;//圆心X
        float circleCenterY = textWidth + getPaddingTop() + cornerTop;//圆心Y
        if (cornerText.length() == 1) {
            canvas.drawCircle(circleCenterX, circleCenterY, textWidth, mCirclePaint);
            canvas.drawText(cornerText, circleCenterX - textWidth / 2, circleCenterY + textWidth / 2, mTextPaint);

        } else {//如果超过两位字符 绘制两个圆，圆之间用矩形填充
            //文字宽度
            float width = mTextPaint.measureText(cornerText);

            //第一个圆
            canvas.drawCircle(circleCenterX, circleCenterY, textWidth, mCirclePaint);

            //第二个圆
            float circle2CenterX = circleCenterX - width + textWidth;//圆心X
            canvas.drawCircle(circle2CenterX, circleCenterY, textWidth, mCirclePaint);

            //矩形填充
            canvas.drawRect(circle2CenterX, 0, circleCenterX, circleCenterY + textWidth, mCirclePaint);

            canvas.drawText(cornerText, circle2CenterX - textWidth / 2, circleCenterY + textWidth / 2, mTextPaint);
        }
    }

    /** * 设置角标文字 * * @param text 角标内容 * @param size 字体大小（SP） */
    public void setCornerText(String text, float size) {
        //角表内容
        if (!TextUtils.isEmpty(text))
            this.cornerText = text;
        //设置画笔字号
        textSize = sp2px(getContext(), size);
        mTextPaint.setTextSize(textSize);
        //角标高度，取第一个字符的高度
        if (!TextUtils.isEmpty(cornerText))
            textWidth = mTextPaint.measureText(cornerText.substring(0, 1));

    }

    /** * 设置角标文字 * * @param text 角标内容 * @param size 字体大小（SP） * @param bgColor 角标背景颜色id * @param textColor 字体颜色id */
    public void setCornerText(String text, float size, int bgColor, int textColor) {
        //画笔颜色
        mCirclePaint.setColor(ContextCompat.getColor(getContext(), bgColor));
        mTextPaint.setColor(ContextCompat.getColor(getContext(), textColor));
        invalidate();
    }


    public void setCornerLocation(float top, float right) {
        this.cornerTop = top;
        this.cornerRight = right;
    }


    /** * 将sp值转换为px值，保证文字大小不变 * * @param spValue * @return */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
