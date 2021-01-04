package com.jxkj.fxtc.view.fragment;

import android.view.View;
import android.widget.TextView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.base.BaseFragment;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.conpoment.view.RoundImageView;
import com.jxkj.fxtc.view.activity.MineBillActivity;
import com.jxkj.fxtc.view.activity.MineClglActivity;
import com.jxkj.fxtc.view.activity.MineFqzsActivity;
import com.jxkj.fxtc.view.activity.MineMessageActivity;
import com.jxkj.fxtc.view.activity.MineRegardsActivity;
import com.jxkj.fxtc.view.activity.MineWdqbActivity;
import com.jxkj.fxtc.view.activity.MineYhqActivity;
import com.jxkj.fxtc.view.deme.ZsnaviDemoActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 1000D 订单管理
 */
public class HomeFragment_3 extends BaseFragment {


    @BindView(R.id.iv_mine_tx)
    RoundImageView mIvMineTx;
    @BindView(R.id.tv_mine_name)
    TextView mTvMineName;
    @BindView(R.id.tv_mine_sjh)
    TextView mTvMineSjh;
    @BindView(R.id.tv_qb)
    TextView mTvQb;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.tv22)
    TextView mTv22;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_3;
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void initImmersionBar() {

    }

    public static HomeFragment_3 newInstance() {
        HomeFragment_3 homeFragment = new HomeFragment_3();
        return homeFragment;
    }


    @OnClick({R.id.iv_set, R.id.iv_msg, R.id.rv_mine_grzl, R.id.ll_qb, R.id.rl_wdzd, R.id.rl_clgl, R.id.tv_mine_dcq, R.id.tv_mine_fpzs, R.id.tv_mine_yjfk, R.id.tv_mine_sybz, R.id.tv_mine_gywd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_set:
                IntentUtils.getInstence().intent(getActivity(), ZsnaviDemoActivity.class);
                break;
            case R.id.iv_msg:
                IntentUtils.getInstence().intent(getActivity(), MineMessageActivity.class);
                break;
            case R.id.rv_mine_grzl:
                break;
            case R.id.ll_qb:
                IntentUtils.getInstence().intent(getActivity(), MineWdqbActivity.class);
                break;
            case R.id.rl_wdzd:
                IntentUtils.getInstence().intent(getActivity(), MineBillActivity.class);
                break;
            case R.id.rl_clgl:
                IntentUtils.getInstence().intent(getActivity(), MineClglActivity.class);
                break;
            case R.id.tv_mine_dcq:
                IntentUtils.getInstence().intent(getActivity(), MineYhqActivity.class);
                break;
            case R.id.tv_mine_fpzs:
                IntentUtils.getInstence().intent(getActivity(), MineFqzsActivity.class);
                break;
            case R.id.tv_mine_yjfk:
                break;
            case R.id.tv_mine_sybz:
                break;
            case R.id.tv_mine_gywd:
                IntentUtils.getInstence().intent(getActivity(), MineRegardsActivity.class);
                break;
        }
    }
}