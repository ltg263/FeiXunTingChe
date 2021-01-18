package com.jxkj.fxtc.view.adapter;

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
public class SearchListAdapter extends BaseQuickAdapter<LotListBean.ListBean, BaseViewHolder> {
    String type;
    public SearchListAdapter(@Nullable List<LotListBean.ListBean> data, String type) {
        super(R.layout.item_search_list, data);
        this.type = type;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LotListBean.ListBean item) {
//        ¥20/小时
        helper.setText(R.id.tv1, item.getParkingName() + "-停车场").setText(R.id.tv_dw, item.getAddress());
    }
}
