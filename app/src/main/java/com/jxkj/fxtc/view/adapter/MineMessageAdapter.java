package com.jxkj.fxtc.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.entity.MessageListBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2914:03
 */
public class MineMessageAdapter extends BaseQuickAdapter<MessageListBean.ListBean, BaseViewHolder> {
    public MineMessageAdapter(@Nullable List<MessageListBean.ListBean> data) {
        super(R.layout.item_mine_message, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MessageListBean.ListBean item) {
        if(item.getMessageType().equals("1")){
            helper.setText(R.id.tv1,"系统消息");
        }else if(item.getMessageType().equals("2")){
            helper.setText(R.id.tv1,"交易消息");
        }else if(item.getMessageType().equals("3")){
            helper.setText(R.id.tv1,"活动消息");
        }
        helper.setText(R.id.tv_content,item.getDetail()).setText(R.id.tv_time,item.getCreateTime());
    }
}
