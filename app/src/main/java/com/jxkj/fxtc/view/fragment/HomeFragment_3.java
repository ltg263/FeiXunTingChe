package com.jxkj.fxtc.view.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.app.ConstValues;
import com.jxkj.fxtc.base.BaseFragment;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.GlideImageLoader;
import com.jxkj.fxtc.conpoment.utils.GlideImgLoader;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.conpoment.view.RoundImageView;
import com.jxkj.fxtc.conpoment.widget.CodeUtils;
import com.jxkj.fxtc.entity.HomeBean;
import com.jxkj.fxtc.entity.UserDetailBean;
import com.jxkj.fxtc.view.activity.LoginActivity;
import com.jxkj.fxtc.view.activity.MineBillActivity;
import com.jxkj.fxtc.view.activity.MineClglActivity;
import com.jxkj.fxtc.view.activity.MineFqzsActivity;
import com.jxkj.fxtc.view.activity.MineGrzxRecordActivity;
import com.jxkj.fxtc.view.activity.MineMessageActivity;
import com.jxkj.fxtc.view.activity.MineRegardsActivity;
import com.jxkj.fxtc.view.activity.MineSetActivity;
import com.jxkj.fxtc.view.activity.MineSybzActivity;
import com.jxkj.fxtc.view.activity.MineWdqbActivity;
import com.jxkj.fxtc.view.activity.MineYhqActivity;
import com.jxkj.fxtc.view.activity.MineYjfkActivity;
import com.jxkj.fxtc.view.deme.ZsnaviDemoActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        Log.w("sHA1","sHA1:"+ CodeUtils.sHA1(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserDetail();
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
//                IntentUtils.getInstence().intent(getActivity(), MineSetActivity.class);
                break;
            case R.id.iv_msg:
//                IntentUtils.getInstence().intent(getActivity(), MineMessageActivity.class);
                IntentUtils.getInstence().intent(getActivity(), LoginActivity.class);
                break;
            case R.id.rv_mine_grzl:
                IntentUtils.getInstence().intent(getActivity(), MineGrzxRecordActivity.class);
                break;
            case R.id.ll_qb:
                IntentUtils.getInstence().intent(getActivity(), MineWdqbActivity.class);
                break;
            case R.id.rl_wdzd:
                IntentUtils.getInstence().intent(getActivity(), MineBillActivity.class);
                break;
            case R.id.rl_clgl:
                IntentUtils.getInstence().intent(getActivity(), MineClglActivity.class,"type","1");
                break;
            case R.id.tv_mine_dcq:
                IntentUtils.getInstence().intent(getActivity(), MineYhqActivity.class);
                break;
            case R.id.tv_mine_fpzs:
                IntentUtils.getInstence().intent(getActivity(), MineFqzsActivity.class);
                break;
            case R.id.tv_mine_yjfk:
                IntentUtils.getInstence().intent(getActivity(), MineYjfkActivity.class);
                break;
            case R.id.tv_mine_sybz:
                IntentUtils.getInstence().intent(getActivity(), MineSybzActivity.class);
                break;
            case R.id.tv_mine_gywd:
                IntentUtils.getInstence().intent(getActivity(), MineRegardsActivity.class);
                break;
        }
    }
    private void getUserDetail() {
        RetrofitUtil.getInstance().apiService()
                .getUserDetail()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<UserDetailBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserDetailBean> result) {
                        if (isDataInfoSucceed(result)) {
                            GlideImgLoader.setGlideImage(getActivity(),result.getData().getAvatar(),mIvMineTx);
                            mTvMineName.setText(result.getData().getNickName());
                            mTvMineSjh.setText(result.getData().getMobile());
                            mTvQb.setText(result.getData().getIntegral());
                            SharedUtils.singleton().put(ConstValues.AVATAR,result.getData().getAvatar());
                            SharedUtils.singleton().put(ConstValues.USER_NAME,result.getData().getNickName());
                            if(StringUtil.isBlank(result.getData().getSex())){
                                SharedUtils.singleton().put(ConstValues.GENDER,result.getData().getSex());
                            }
                            SharedUtils.singleton().put(ConstValues.BIRTHDAY,result.getData().getBalance());
                            SharedUtils.singleton().put(ConstValues.USER_PHONE,result.getData().getMobile());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}