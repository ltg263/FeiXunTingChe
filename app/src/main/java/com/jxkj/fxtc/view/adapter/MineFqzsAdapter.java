package com.jxkj.fxtc.view.adapter;

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
public class MineFqzsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MineFqzsAdapter(@Nullable List<String> data) {
        super(R.layout.item_mine_fqzs, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
    }
}
