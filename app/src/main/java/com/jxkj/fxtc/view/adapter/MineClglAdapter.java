package com.jxkj.fxtc.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.entity.UserCarListBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineClglAdapter extends BaseQuickAdapter<UserCarListBean.ListBean, BaseViewHolder> {
    public MineClglAdapter(@Nullable List<UserCarListBean.ListBean> data) {
        super(R.layout.item_mine_clgl, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserCarListBean.ListBean item) {
        helper.setText(R.id.tv_name,item.getLicense()).setText(R.id.tv,"新能源");
        helper.setImageDrawable(R.id.iv,mContext.getResources().getDrawable(R.drawable.ic_clgl_bj2));
        if(item.getType().equals("1")){
            helper.setText(R.id.tv,"汽油车");
            helper.setImageDrawable(R.id.iv,mContext.getResources().getDrawable(R.drawable.ic_clgl_bj1));
        }
    }
}
