package com.jxkj.fxtc.view.adapter;

import android.text.Html;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fxtc.R;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class BookingSpaceAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public BookingSpaceAdapter(@Nullable List<String> data) {
        super(R.layout.item_booking_space, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
//        ¥20/小时
        String str = "<font color=\"#0199FC\">¥<big><big>"+20+"</big></big></font>/小时";
        helper.setText(R.id.tv_time, Html.fromHtml(str));
    }
}
