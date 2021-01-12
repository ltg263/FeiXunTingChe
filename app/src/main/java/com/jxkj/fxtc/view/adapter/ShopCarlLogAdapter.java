package com.jxkj.fxtc.view.adapter;

import android.text.Html;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.entity.OrdersListBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class ShopCarlLogAdapter extends BaseQuickAdapter<OrdersListBean.ListBean, BaseViewHolder> {
    public ShopCarlLogAdapter(@Nullable List<OrdersListBean.ListBean> data) {
        super(R.layout.item_shop_car_log, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrdersListBean.ListBean item) {

//        ¥20/小时
        String str = "实付：<font color=\"#0199FC\">¥<big><big>" + item.getOrderPrice() + "</big></big></font>";

        helper.setText(R.id.tv_time, item.getCreatTime())
                .setGone(R.id.ll_1,false)
                .setGone(R.id.ll_2,true)
                .setText(R.id.tv_fy, Html.fromHtml(str))
                .setText(R.id.tv_fy1, Html.fromHtml(str))
                .setText(R.id.tv2, item.getAddress())
                .setText(R.id.tv_orderType,"停车费")
                .setText(R.id.tv, item.getLicense()).setText(R.id.tv1, "车位：" + item.getSeatName() + "         时间：" + StringUtil.getUseTime(item.getUseTime()));
        if(item.getStatus().equals("1")){//订单状态0未支付1已支付
            helper.setGone(R.id.ll_1,true)
                    .setGone(R.id.ll_2,false);
        }
        //订单类型0停车订单1预约订单
        if(item.getOrderType().equals("1")){
            helper.setText(R.id.tv_orderType,"预约费");
        }
    }
}
