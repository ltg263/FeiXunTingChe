package com.jxkj.fxtc.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fxtc.MainActivity;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.HttpUtils;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.app.ConstValues;
import com.jxkj.fxtc.app.MainApplication;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.LoginBean;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.conpoment.utils.TimeCounter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.et_input_sjh)
    EditText mEtInputSjh;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.et_input_yzm)
    EditText mEtInputYzm;
    @BindView(R.id.tv_go_yzm)
    TextView mTvGoYzm;
    @BindView(R.id.ll2)
    LinearLayout mLl2;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.bnt_login)
    Button mBntLogin;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    private TimeCounter mTimeCounter;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        MainApplication.addActivity(this);
    }

    public static void startActivityIntent(Context mContext) {
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }

    @OnClick({R.id.tv_go_yzm, R.id.bnt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_yzm:
                String sjh = mEtInputSjh.getText().toString();
                if(!TextUtils.isEmpty(sjh)&&sjh.length()==11){
                    mTimeCounter = new TimeCounter(60 * 1000, 1000, mTvGoYzm);
                    mTimeCounter.start();
                    HttpUtils.goGetYzm(sjh,0);
                }else{
                    ToastUtils.showShort("请输入正确的手机号");
                }
                break;
            case R.id.bnt_login:
                yzmLogin();
                break;
        }
    }

    private void yzmLogin() {
        String sjh = mEtInputSjh.getText().toString();
        String yzm = mEtInputYzm.getText().toString();
        if(StringUtil.isBlank(sjh) || StringUtil.isBlank(yzm)){
            ToastUtils.showShort("手机号或验证码不能为空");
            return;
        }
        show();
        RetrofitUtil.getInstance().apiService()
                .postLogin(sjh,yzm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LoginBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LoginBean> result) {
                        if(isDataInfoSucceed(result)){
                            ToastUtils.showShort("登录成功");
                            Log.w("App-Token:","App-Token:"+ SharedUtils.getToken());
                            SharedUtils.singleton().put(ConstValues.TOKEN,result.getData().getTokenId());
                            SharedUtils.singleton().put(ConstValues.USER_PHONE,result.getData().getMobile());
                            SharedUtils.singleton().put(ConstValues.TOKEN,result.getData().getTokenId());
                            SharedUtils.singleton().put(ConstValues.TOKEN,result.getData().getTokenId());
                            SharedUtils.singleton().put(ConstValues.ISLOGIN,true);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
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

}
