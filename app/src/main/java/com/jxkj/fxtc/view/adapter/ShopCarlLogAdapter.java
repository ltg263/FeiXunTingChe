package com.jxkj.fxtc.view.adapter;

import android.text.Html;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.entity.CarRecordListBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class ShopCarlLogAdapter extends BaseQuickAdapter<CarRecordListBean.ListBean, BaseViewHolder> {
    public ShopCarlLogAdapter(@Nullable List<CarRecordListBean.ListBean> data) {
        super(R.layout.item_shop_car_log, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CarRecordListBean.ListBean item) {

//        ¥20/小时
        String str = "实付：<font color=\"#0199FC\">¥<big><big>" + 20 + "</big></big></font>";
        helper.setText(R.id.tv_time,item.getCreatTime())
                .setText(R.id.tv_fy, Html.fromHtml(str))
        .setText(R.id.tv,item.getLicense());
    }
}
