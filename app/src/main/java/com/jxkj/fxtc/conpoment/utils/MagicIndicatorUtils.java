package com.jxkj.fxtc.conpoment.utils;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.conpoment.ScaleTransitionPagerTitleView;
import com.jxkj.fxtc.conpoment.widget.ColorFlipPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MagicIndicatorUtils {

    /**
     * 基础的下划线
     * @param mContext
     * @param mDataList
     * @param mMagicIndicator
     * @param mViewPager
     */
    public static void initMagicIndicator_1(Context mContext, List<String> mDataList, MagicIndicator mMagicIndicator, ViewPager mViewPager) {
        mMagicIndicator.setBackgroundColor(Color.parseColor("#ffffff"));
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setScrollPivotX(0.25f);
//        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(mContext.getResources().getColor(R.color.color_666666));
                simplePagerTitleView.setSelectedColor(mContext.getResources().getColor(R.color.color_text_theme));
                simplePagerTitleView.setTextSize(15);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 1));
                indicator.setYOffset(20);
                indicator.setColors(mContext.getResources().getColor(R.color.color_text_theme));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    /**
     * 基础的下划线+动作
     * @param mContext
     * @param mDataList
     * @param mMagicIndicator
     * @param mViewPager
     */
    public static void initMagicIndicator_2(Context mContext, List<String> mDataList,MagicIndicator mMagicIndicator, ViewPager mViewPager) {
        mMagicIndicator.setBackgroundColor(mContext.getResources().getColor(R.color.color_ffffff));
        CommonNavigator commonNavigator7 = new CommonNavigator(mContext);
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(mContext.getResources().getColor(R.color.color_666666));
                simplePagerTitleView.setSelectedColor(mContext.getResources().getColor(R.color.color_000000));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 1));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(mContext.getResources().getColor(R.color.color_text_theme));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }
    public static void initMagicIndicator6(Context mContext, List<String> mDataList, MagicIndicator mMagicIndicator, ViewPager mViewPager) {
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(mContext.getResources().getColor(R.color.color_999999));
                simplePagerTitleView.setSelectedColor(mContext.getResources().getColor(R.color.color_text_theme));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 1));
                indicator.setYOffset(20);
                indicator.setColors(mContext.getResources().getColor(R.color.color_text_theme));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }



    public static void initLC(Context mContext, LineChart mLineChart) {


        Description description = new Description();
        // 不需要右下角的描述文字
        description.setEnabled(false);
        mLineChart.setDescription(description);
        // 不需要背景
        mLineChart.setBackground(null);
        // 可以拖动，而不影响缩放比例
        mLineChart.setDragEnabled(false);
        // 设置没有数据时候的字体样式
        mLineChart.setNoDataText("");
        mLineChart.setNoDataTextColor(mContext.getResources().getColor(R.color.gray));
        // 不需要网格背景
        mLineChart.setDrawGridBackground(false);
        // 不需要边界
        mLineChart.setDrawBorders(false);
        // X轴可以缩放，Y轴不能缩放
        mLineChart.setScaleXEnabled(false);
        mLineChart.setScaleYEnabled(false);
        // 左右两边预留点空白部分
        mLineChart.setExtraOffsets(20, 0, 20, 0);
        // 不需要展示图例
        mLineChart.getLegend().setEnabled(false);


//        mLineChart.setViewPortOffsets(0, 0, 0, 0);
//        mLineChart.setBackgroundColor(mContext.getResources().getColor(R.color.color_ffffff));
//
//        // no description text
////        mLineChart.getDescription().setEnabled(false);
//
//        // 不需要展示图例
//        mLineChart.getLegend().setEnabled(false);
//        // enable touch gestures
////        mLineChart.setTouchEnabled(true);
//
//        // enable scaling and dragging
////        mLineChart.setDragEnabled(true);
////        mLineChart.setScaleEnabled(true);
//
//        mLineChart.setScaleXEnabled(true);
//        mLineChart.setScaleYEnabled(false);
//
//        mLineChart.setDragEnabled(true);
//
//        mLineChart.setDrawGridBackground(false);
//        mLineChart.setMaxHighlightDistance(300);

        XAxis x = mLineChart.getXAxis();
        x.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Light.ttf"));
        x.setLabelCount(3, false);
        x.setTextColor(mContext.getResources().getColor(R.color.color_999999));
        x.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        x.setDrawGridLines(false);
        x.setAxisLineColor(mContext.getResources().getColor(R.color.gold));

        YAxis y = mLineChart.getAxisLeft();
        y.setEnabled(true);


        mLineChart.getAxisRight().setEnabled(false);


        mLineChart.getLegend().setEnabled(false);

        mLineChart.animateXY(2000, 2000);

        // don't forget to refresh the drawing
        mLineChart.invalidate();
        setData(mContext,mLineChart);
    }

    private static void setData(Context mContext,LineChart mLineChart) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            float val = 150-i;
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.0f);
            set1.setCircleRadius(2f);
            set1.setCircleColor(mContext.getResources().getColor(R.color.gold));
            set1.setHighLightColor(mContext.getResources().getColor(R.color.transparent));
            set1.setColor(mContext.getResources().getColor(R.color.color_999999));
            set1.setFillColor(mContext.getResources().getColor(R.color.color_eeeeee));
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return mLineChart.getAxisLeft().getAxisMinimum();
                }
            });

            // create a data object with the data sets
            LineData data = new LineData(set1);
            data.setValueTypeface(Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Light.ttf"));
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            mLineChart.setData(data);
        }
    }

    static int i = 1;

    private static int maxCount = 60; //集合最大存储数量
    public static void initChart(List<Entry> mData, LineChart lineChart, long maxYValue) {
        int lineColor = Color.parseColor("#ebebeb");
        int textColor = Color.parseColor("#999999");

        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setEnabled(false);

        YAxis axisLeft = lineChart.getAxisLeft();
        axisLeft.setAxisMinimum(0);
        axisLeft.setLabelCount(10);
        axisLeft.setAxisMaximum(maxYValue);
        axisLeft.setGridColor(lineColor);
        axisLeft.setTextColor(textColor);
        axisLeft.setAxisLineColor(lineColor);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(maxCount);
        xAxis.setLabelCount(maxCount);
        Collections.sort(mData, new EntryXComparator());
        LineData lineData = new LineData(getSet(mData));
        lineData.setDrawValues(false);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    private static LineDataSet getSet(List<Entry> mData) {
        int valueColor = Color.parseColor("#2979FF");
        LineDataSet set = new LineDataSet(mData, "");
        set.setDrawFilled(true);
        set.setFillColor(valueColor);
        set.setColor(valueColor);
        set.setValueTextColor(valueColor);
        set.setDrawCircles(false);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        return set;
    }
    public static void addEntry(List<Entry> mData, LineChart lineChart, float yValues) {
        if (lineChart != null
                && lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            int size = mData.size();
            if (size == 0) {
                Entry entry = new Entry(maxCount, yValues);
                mData.add(entry);
            } else {
                boolean needRemove = false;
                for (Entry e : mData) {
                    float x = e.getX() - 1;
                    if (x < 0) {
                        needRemove = true;
                    }
                    e.setX(x);
                }
                if (needRemove) {
                    mData.remove(0);
                }
                Entry entry = new Entry(maxCount, yValues);
                mData.add(entry);
            }
            LineData lineData = new LineData(getSet(mData));
            lineData.setDrawValues(false);
            lineChart.setData(lineData);
            lineChart.invalidate();
        }
    }
}
