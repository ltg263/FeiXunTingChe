package com.jxkj.fxtc.conpoment.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.conpoment.utils.DensityUtil;


import java.util.ArrayList;
import java.util.List;

public class WorkerNodeProgressView extends View {
    //node 默认
    private Paint mNodePaint;
    //node 已完成
    private Paint mNodeProgressPaint;

    private Paint mTextPaint;
    private Paint mTextProgressPaint;

    private Paint mLinePaint;
    private Paint mLineProgressPaint;

    private int mNodeColor;
    private int mNodeProgressColor;

    private int mTextColor;
    private int mTextProgressColor;

    private int mLineColor;
    private int mLineProgressColor;


    private Bitmap mNodeBitmap;
    private Bitmap mNodeProgressBitmap;


    //node 半径
    private int mNodeRadio;
    //节点个数
    private int mNumber;
    private List<Node> nodes;

    private int mCurentNode;


    private int mWidth;
    private int mHeight;

    public WorkerNodeProgressView(Context context) {
        this(context, null);
    }

    public WorkerNodeProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WorkerNodeProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(getResources().getColor(R.color.transparent));
        initAttrs(attrs);
        init();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray mTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.nodeProgress);
        mNodeColor = mTypedArray.getColor(R.styleable.nodeProgress_node_color, getResources().getColor(R.color.color_eeeeee));
        mNodeProgressColor = mTypedArray.getColor(R.styleable.nodeProgress_node_progresscolor, getResources().getColor(R.color.color_Ffd946));

        mTextColor = mTypedArray.getColor(R.styleable.nodeProgress_node_tip, getResources().getColor(R.color.color_eeeeee));
        mTextProgressColor = mTypedArray.getColor(R.styleable.nodeProgress_node_progress_tip, getResources().getColor(R.color.color_Ffd946));

        mLineColor = mTypedArray.getColor(R.styleable.nodeProgress_node_bar, getResources().getColor(R.color.color_eeeeee));
        mLineProgressColor = mTypedArray.getColor(R.styleable.nodeProgress_node_progress_bar, getResources().getColor(R.color.color_Ffd946));

        mNumber = mTypedArray.getInt(R.styleable.nodeProgress_node_num, 2);
        mCurentNode = mTypedArray.getInt(R.styleable.nodeProgress_node_current, 0);
        mNodeRadio = mTypedArray.getDimensionPixelSize(R.styleable.nodeProgress_node_radio, 7);
        BitmapDrawable drawable = (BitmapDrawable) mTypedArray.getDrawable(R.styleable.nodeProgress_node_bg);
        mNodeBitmap = drawable.getBitmap();
        BitmapDrawable drawable1 = (BitmapDrawable) mTypedArray.getDrawable(R.styleable.nodeProgress_node_progress_bg);
        mNodeProgressBitmap = drawable1.getBitmap();


    }

    private void init() {
        mNodeRadio = DensityUtil.dip2px(getContext(), 7);

        mNodePaint = new Paint();
        mNodePaint.setAntiAlias(true);
        mNodePaint.setFilterBitmap(true);
        mNodePaint.setStyle(Paint.Style.FILL);
        mNodePaint.setColor(mNodeColor);

        mNodeProgressPaint = new Paint();
        mNodeProgressPaint.setAntiAlias(true);
        mNodeProgressPaint.setFilterBitmap(true);
        mNodeProgressPaint.setStyle(Paint.Style.FILL);
        mNodeProgressPaint.setColor(mNodeProgressColor);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setFilterBitmap(true);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE );
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(DensityUtil.dip2px(getContext(), 10));

        mTextProgressPaint = new Paint();
        mTextProgressPaint.setAntiAlias(true);
        mTextProgressPaint.setFilterBitmap(true);
        mTextProgressPaint.setStyle(Paint.Style.FILL_AND_STROKE );
        mTextProgressPaint.setColor(mTextProgressColor);
        mTextProgressPaint.setTextAlign(Paint.Align.CENTER);
        mTextProgressPaint.setTextSize(DensityUtil.dip2px(getContext(), 10));


        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setFilterBitmap(true);
        mLinePaint.setStrokeWidth(DensityUtil.dip2px(getContext(), 2));
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setColor(mLineColor);

        mLineProgressPaint = new Paint();
        mLineProgressPaint.setAntiAlias(true);
        mLineProgressPaint.setFilterBitmap(true);
        mLineProgressPaint.setStrokeWidth(DensityUtil.dip2px(getContext(), 2));
        mLineProgressPaint.setStyle(Paint.Style.FILL);
        mLineProgressPaint.setColor(mLineProgressColor);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        int d = (mWidth - getPaddingLeft() - getPaddingRight()) / (mNumber - 1);

        nodes = new ArrayList<>();
        for (int i = 0; i < mNumber; i++) {
            if (i == 0) {
                Node node = new Node();
                Point paint = new Point(getPaddingLeft() + mNodeRadio+20, mHeight / 2);
                node.setPoint(paint);
                node.setDes("待联系");
                nodes.add(node);
                continue;
            }

            if (i == 1) {
                Node node = new Node();
                Point paint = new Point(getPaddingLeft() + d * i - mNodeRadio+20, mHeight / 2);
                node.setPoint(paint);
                node.setDes("待上门");
                nodes.add(node);
                continue;
            }

            if (i == 2) {
                Node node = new Node();
                Point paint = new Point(getPaddingLeft() + d * i - mNodeRadio-20, mHeight / 2);
                node.setPoint(paint);
                node.setDes("服务中");
                nodes.add(node);
                continue;
            }

            if (i == 3) {
                Node node = new Node();
                Point paint = new Point(mWidth - getPaddingRight() - mNodeRadio-20, mHeight / 2);
                node.setPoint(paint);
                node.setDes("已完成");
                nodes.add(node);
                continue;
            }

            /*if (i == mNumber - 1) {
                Node node = new Node();
                Point paint = new Point(mWidth - getPaddingRight() - mNodeRadio, mHeight / 2);
                node.setPoint(paint);
                node.setDes("第" + (i + 1) + "个");
                nodes.add(node);
                continue;
            }
            Node node = new Node();
            Point paint = new Point(getPaddingLeft() + d * i - mNodeRadio, mHeight / 2);
            node.setPoint(paint);
            node.setDes("第" + (i + 1) + "个");
            nodes.add(node);*/
        }


    }

    private Canvas mCanvas;

    @Override
    protected void onDraw(Canvas canvas) {

        mCanvas = canvas;
        drawLineProgress(canvas);
        drawNodeProgress(canvas);
        drawTextProgress(canvas);
    }

    public void clear() {
        // mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mCanvas.drawPaint(paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        // setBackgroundColor(Color.WHITE);
        //invalidate();
    }


    private void drawLineProgress(Canvas canvas) {
        int startX = getPaddingLeft() + mNodeRadio * 2;
        for (int i = 1; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            Point point = node.getPoint();
            int x = point.x;
            int y = point.y;
            x = x - mNodeRadio;
            if (mCurentNode >= i) {
                canvas.drawLine(startX, y, x, y, mLineProgressPaint);
            } else {
                canvas.drawLine(startX, y, x, y, mLinePaint);
            }
            startX = x + mNodeRadio * 2;

        }

    }

    private void drawNodeProgress(Canvas canvas) {
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            Point point = node.getPoint();
            if (mCurentNode >= i) {
                if (mNodeProgressBitmap != null) {
                    Rect b1 = new Rect(0, 0, mNodeProgressBitmap.getWidth(), mNodeProgressBitmap.getHeight());
                    Rect b = new Rect(point.x - mNodeRadio, point.y - mNodeRadio,
                            point.x + mNodeRadio, point.y + mNodeRadio);
                    canvas.drawBitmap(mNodeProgressBitmap, b1, b, mNodePaint);
                } else {
                    canvas.drawCircle(point.x, point.y, mNodeRadio, mNodeProgressPaint);
                }
            } else {
                if (mNodeBitmap != null) {
                    Rect b1 = new Rect(0, 0, mNodeBitmap.getWidth(), mNodeBitmap.getHeight());
                    Rect b = new Rect(point.x - mNodeRadio, point.y - mNodeRadio,
                            point.x + mNodeRadio, point.y + mNodeRadio);
                    canvas.drawBitmap(mNodeBitmap, b1, b, mNodePaint);
                } else {
                    canvas.drawCircle(point.x, point.y, mNodeRadio, mNodePaint);
                }

            }

        }
    }


    private void drawTextProgress(Canvas canvas) {
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            Point point = node.getPoint();
            String text = node.getDes();
            Paint.FontMetricsInt fmi = mTextPaint.getFontMetricsInt();
            if (mCurentNode >= i) {
                canvas.drawText(text, point.x, point.y + mNodeRadio + fmi.bottom - fmi.top, mTextProgressPaint);
            } else {
                canvas.drawText(text, point.x, point.y + mNodeRadio + fmi.bottom - fmi.top, mTextPaint);
            }

        }

    }


    public void setmNumber(int mNumber) {
        this.mNumber = mNumber;
    }

    public void setCurentNode(int curentNode) {
        this.mCurentNode = curentNode;
    }

    public void reDraw() {
        clear();
        invalidate();
    }

    class Node {
        private Point point;
        private String des;

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }


    private void testDraw(Canvas canvas) {


        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3);
        paint.setTextSize(40);
        Paint.FontMetricsInt fmi = paint.getFontMetricsInt();
        String testString = "测试：ijkJQKA:1234";
        Rect bounds1 = new Rect();
        paint.getTextBounds("测", 0, 1, bounds1);
        Rect bounds2 = new Rect();
        paint.getTextBounds("测试：ijk", 0, 6, bounds2);
        // 随意设一个位置作为baseline
        int x = 10;
        int y = 400;
        // 把testString画在baseline上
        canvas.drawText(testString, x, y, paint);
        // bounds1
        paint.setStyle(Paint.Style.STROKE);  // 画空心矩形
        canvas.save();
        canvas.translate(x, y);  // 注意这里有translate。getTextBounds得到的矩形也是以baseline为基准的
        paint.setColor(Color.GREEN);
        canvas.drawRect(bounds1, paint);
        canvas.restore();

        // bounds2
        canvas.save();
        paint.setColor(Color.MAGENTA);
        canvas.translate(x, y);
        canvas.drawRect(bounds2, paint);
        canvas.restore();

        // baseline
        paint.setColor(Color.RED);
        canvas.drawLine(x, y, 1024, y, paint);
        // ascent
        paint.setColor(Color.YELLOW);
        canvas.drawLine(x, y + fmi.ascent, 1024, y + fmi.ascent, paint);
        // descent
        paint.setColor(Color.BLUE);
        canvas.drawLine(x, y + fmi.descent, 1024, y + fmi.descent, paint);
        // top
        paint.setColor(Color.DKGRAY);
        canvas.drawLine(x, y + fmi.top, 1024, y + fmi.top, paint);
        // bottom
        paint.setColor(Color.GREEN);
        canvas.drawLine(x, y + fmi.bottom, 1024, y + fmi.bottom, paint);
    }
}
