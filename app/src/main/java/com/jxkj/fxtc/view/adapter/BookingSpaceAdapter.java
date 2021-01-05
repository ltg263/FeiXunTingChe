package com.jxkj.fxtc.view.adapter;

import android.text.Html;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.entity.LotListBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class BookingSpaceAdapter extends BaseQuickAdapter<LotListBean.ListBean, BaseViewHolder> {
    public BookingSpaceAdapter(@Nullable List<LotListBean.ListBean> data) {
        super(R.layout.item_booking_space, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LotListBean.ListBean item) {
//        ¥20/小时
        String str = "<font color=\"#0199FC\">¥<big><big>"+item.getParkingPrice()+"</big></big></font>/小时";
        helper.setText(R.id.tv_time, Html.fromHtml(str));
        helper.setText(R.id.tv1,item.getParkingName()+"-停车场").setText(R.id.tv_dw,item.getAddress())
        .setText(R.id.tv_content,"营业时间："+item.getStartTime()+"-"+item.getEndTime())
        .setText(R.id.tv_jl,"距你"+item.getDistance()+"km")
        .setText(R.id.tv_cw,"剩余车位"+item.getSeatCount()+"个");



    }
}
