package com.jxkj.fxtc.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.HttpUtils;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.app.MainApplication;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.conpoment.utils.TimeCounter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginWjmmActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.et_sjh)
    EditText mEtSjh;
    @BindView(R.id.get_code)
    TextView mGetCode;
    @BindView(R.id.et_yzm)
    EditText mEtYzm;
    @BindView(R.id.et_mm)
    EditText mEtMm;
    @BindView(R.id.tv_ok)
    TextView mTvOk;
    private TimeCounter mTimeCounter;

    @Override
    protected int getContentView() {
        return R.layout.activity_login_wjjmm;
    }

    @Override
    protected void initViews() {
        MainApplication.addActivity(this);
        mIvBack.setImageDrawable(getResources().getDrawable(R.mipmap.back_h));
        mTvTitle.setText("设置新密码");
    }

    @OnClick({R.id.get_code, R.id.tv_ok,R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.get_code:
                getCode();
                break;
            case R.id.tv_ok:
                setPos();
                break;
        }
    }

    private void setPos() {
        show();
        String sjh = mEtSjh.getText().toString();
        String yzm = mEtYzm.getText().toString();
        String mm = mEtMm.getText().toString();
        if(StringUtil.isBlank(sjh) ||StringUtil.isBlank(yzm) ||StringUtil.isBlank(mm)){
            ToastUtils.showShort("填写不完整");
            return;
        }
        RetrofitUtil.getInstance().apiService()
                .verifyForgetPswd(sjh,mm,yzm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(result.getStatus()==0){
                            ToastUtils.showShort("修改成功");
                            finish();
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

    private void getCode() {
        String sjh = mEtSjh.getText().toString();
        if (!TextUtils.isEmpty(sjh) && sjh.length() == 11) {
            mTimeCounter = new TimeCounter(60 * 1000, 1000, mGetCode);
            mTimeCounter.start();
            HttpUtils.goGetYzm(sjh,0);
        } else {
            ToastUtils.showShort("请输入正确的手机号");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimeCounter != null) {
            mTimeCounter.cancel();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mTimeCounter != null) {
            mTimeCounter.cancel();
        }
    }

}
