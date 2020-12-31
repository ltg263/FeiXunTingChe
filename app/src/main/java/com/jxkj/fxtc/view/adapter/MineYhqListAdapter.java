package com.jxkj.fxtc.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.entity.CouponCanListUser;

import java.util.List;

/**
 * author : LiuJie
 * date   : 2020/5/2712:25
 */
public class MineYhqListAdapter extends BaseQuickAdapter<CouponCanListUser.ListBean, BaseViewHolder> {
    int type =0;
    public MineYhqListAdapter(@Nullable List<CouponCanListUser.ListBean> data, int type) {
        super(R.layout.item_mine_yhq_list, data);
        this.type=type;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CouponCanListUser.ListBean item) {
        if(type==2){
            helper.setGone(R.id.ll_yhq_kybj,false);
            helper.setGone(R.id.ll_yhq_kybj_1,true);
            helper.addOnClickListener(R.id.tv_yhq_type_1);
            helper.setText(R.id.tv_yhq_qian_1,item.getReliefAmount())
                    .setText(R.id.tv_yhq_syfs_1,"满"+item.getLimitAmount()+"元"+item.getReliefAmount())
                    .setText(R.id.tv_yhq_bt_1,item.getCouponName())
                    .setText(R.id.tv_yhq_sypt_1,"品类限制："+item.getRemark())
                    .setText(R.id.tv_yhq_sj_1,"有效期至："+item.getExpireDate())
                    .setText(R.id.tv_yhq_type_1,"立即\n使用");
        }else{
            helper.setGone(R.id.ll_yhq_kybj,true);
            helper.setGone(R.id.ll_yhq_kybj_1,false);
            helper.setText(R.id.tv_yhq_qian,item.getReliefAmount())
                    .setText(R.id.tv_yhq_syfs,"满"+item.getLimitAmount()+"元"+item.getReliefAmount())
                    .setText(R.id.tv_yhq_bt,item.getCouponName())
                    .setText(R.id.tv_yhq_sypt,"品类限制："+item.getRemark())
                    .setText(R.id.tv_yhq_sj,"有效期至："+item.getExpireDate());

            if(type==3){
                helper.setText(R.id.tv_yhq_type,"已使用");
            }else{
                helper.setText(R.id.tv_yhq_type,"已过期");
            }
        }
    }
}
