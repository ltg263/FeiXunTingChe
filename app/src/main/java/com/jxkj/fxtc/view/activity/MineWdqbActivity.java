package com.jxkj.fxtc.view.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.entity.PostCarData;
import com.jxkj.fxtc.entity.UserDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineWdqbActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_qian)
    TextView tv_qian;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.tv_2)
    TextView mTv2;
    @BindView(R.id.tv_3)
    TextView mTv3;
    @BindView(R.id.tv_4)
    TextView mTv4;
    @BindView(R.id.tv_5)
    TextView mTv5;
    @BindView(R.id.tv_6)
    TextView mTv6;
    @BindView(R.id.tv_fy)
    TextView tv_fy;
    @BindView(R.id.et)
    EditText mEt;
    @BindView(R.id.im_ali)
    ImageView mImAli;
    @BindView(R.id.im_chat)
    ImageView mImChat;
    String payType = "2";
    @BindView(R.id.bnt_go)
    Button mBntGo;

    @Override
    protected int getContentView() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("我的钱包");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mImAli.setSelected(true);
        getUserDetail();
        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setSelectTv(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mBntGo.setBackground(getResources().getDrawable(R.drawable.btn_shape_theme));
                    if(s.toString().contains("元")){
                        tv_fy.setText(s);
                    }else{
                        tv_fy.setText(s+"元");
                    }
                }

            }
        });
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
                            tv_qian.setText(result.getData().getIntegral());
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

    private void getUserPayAmount() {
        String str = mEt.getText().toString();
        String amount = str.replace("元", "");
        if (StringUtil.isBlank(amount)) {
            ToastUtils.showShort("请选择或输入金额");
            return;
        }
        PostCarData.PayAmount payAmount = new PostCarData.PayAmount();
        payAmount.setAmount(amount);
        payAmount.setPayType(payType);
        show();
        RetrofitUtil.getInstance().apiService()
                .getUserPayAmount(payAmount)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            getUserDetail();
                            ToastUtils.showShort("充值成功");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });

    }

    @OnClick({R.id.ll_back, R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5, R.id.tv_6, R.id.im_ali, R.id.im_chat, R.id.bnt_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                setSelectTv(mTv1);
                break;
            case R.id.tv_2:
                setSelectTv(mTv2);
                break;
            case R.id.tv_3:
                setSelectTv(mTv3);
                break;
            case R.id.tv_4:
                setSelectTv(mTv4);
                break;
            case R.id.tv_5:
                setSelectTv(mTv5);
                break;
            case R.id.tv_6:
                setSelectTv(mTv6);
                break;
            case R.id.im_ali:
                payType = "2";
                mImAli.setSelected(true);
                mImChat.setSelected(false);
                break;
            case R.id.im_chat:
                payType = "1";
                mImAli.setSelected(false);
                mImChat.setSelected(true);
                break;
            case R.id.ll_back:
                finish();
                break;
            case R.id.bnt_go:
                getUserPayAmount();
                break;
        }
    }

    private void setSelectTv(TextView tv) {
        mTv1.setSelected(false);
        mTv1.setTextColor(getResources().getColor(R.color.color_333333));
        mTv2.setSelected(false);
        mTv2.setTextColor(getResources().getColor(R.color.color_333333));
        mTv3.setSelected(false);
        mTv3.setTextColor(getResources().getColor(R.color.color_333333));
        mTv4.setSelected(false);
        mTv4.setTextColor(getResources().getColor(R.color.color_333333));
        mTv5.setSelected(false);
        mTv5.setTextColor(getResources().getColor(R.color.color_333333));
        mTv6.setSelected(false);
        mTv6.setTextColor(getResources().getColor(R.color.color_333333));
        if (tv != null) {
            mEt.setText(tv.getText().toString());
            tv.setSelected(true);
            tv.setTextColor(getResources().getColor(R.color.color_ffffff));
        }

    }
}
