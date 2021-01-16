package com.jxkj.fxtc.view.adapter;


import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.conpoment.view.SquareImageView;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

public class SpPhotoAdapter extends BaseQuickAdapter<LocalMedia, BaseViewHolder> {
    public SpPhotoAdapter(@Nullable List<LocalMedia> data) {
        super(R.layout.item_photo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocalMedia item) {
        if (item.getPath().equals("-1")) {
            helper.setVisible(R.id.iv_close,false).setVisible(R.id.iv_icon_add,true).setVisible(R.id.iv_icon,false);
        } else {
            Glide.with(mContext)
                    .load(item.getCompressPath()).into((SquareImageView) helper.getView(R.id.iv_icon));
            helper.setVisible(R.id.iv_close,true).setVisible(R.id.iv_icon_add,false).setVisible(R.id.iv_icon,true);
            helper.addOnClickListener(R.id.iv_close);
        }
    }
}
