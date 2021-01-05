package com.jxkj.fxtc.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.entity.UserEnvelopesBean;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2712:25
 */
public class MineYhqListAdapter extends BaseQuickAdapter<UserEnvelopesBean.ListBean, BaseViewHolder> {
    public MineYhqListAdapter(@Nullable List<UserEnvelopesBean.ListBean> data) {
        super(R.layout.item_mine_yhq_list);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserEnvelopesBean.ListBean item) {
        if(item.getStatus().equals("1")){
            helper.setGone(R.id.ll_yhq_kybj,false);
            helper.setGone(R.id.ll_yhq_kybj_1,true);
            helper.addOnClickListener(R.id.tv_yhq_type_1);
            helper.setText(R.id.tv_yhq_qian_1,"¥ "+item.getAmountMoney())
                    .setText(R.id.tv_yhq_syfs_1,item.getFullReduction())
                    .setText(R.id.tv_yhq_bt_1,item.getEnvelopsName())
//                    .setText(R.id.tv_yhq_sypt_1,"品类限制："+item.getRemark())
                    .setText(R.id.tv_yhq_sj_1,"有效期至："+item.getEffectiveTime())
                    .setText(R.id.tv_yhq_type_1,"立即\n使用");
        }else{
            helper.setGone(R.id.ll_yhq_kybj,true);
            helper.setGone(R.id.ll_yhq_kybj_1,false);
            helper.setText(R.id.tv_yhq_qian,"¥ "+item.getAmountMoney())
                    .setText(R.id.tv_yhq_syfs,item.getFullReduction())
                    .setText(R.id.tv_yhq_bt,item.getEnvelopsName())
//                    .setText(R.id.tv_yhq_sypt,"品类限制："+item.getRemark())
                    .setText(R.id.tv_yhq_sj,"有效期至："+item.getEffectiveTime());
            if(item.getStatus().equals("2")){
                helper.setText(R.id.tv_yhq_type,"已使用");
            }else{
                helper.setText(R.id.tv_yhq_type,"已过期");
            }
        }
    }
}
