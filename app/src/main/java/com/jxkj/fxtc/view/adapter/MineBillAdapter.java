package com.jxkj.fxtc.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.entity.UserBillListBean;
import com.jxkj.fxtc.entity.UserCarListBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineBillAdapter extends BaseQuickAdapter<UserBillListBean.ListBeanX, BaseViewHolder> {
    public MineBillAdapter(@Nullable List<UserBillListBean.ListBeanX> data) {
        super(R.layout.item_mine_bill, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserBillListBean.ListBeanX item) {
//        billType 	 停车1充值2,预约3
        if(item.getBillType().equals("1")){
            helper.setText(R.id.tv_name,"停车费-"+item.getLicense())
                    .setText(R.id.tv_time,item.getCreateTime())
                    .setText(R.id.tv_jg,item.getAmount());
        }else if(item.getBillType().equals("2")){

        }
    }
}
