package com.jxkj.fxtc.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.entity.PostCarData;
import com.jxkj.fxtc.view.deme.KeyboardUtil;
import com.jxkj.fxtc.view.deme.NumberEditText;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddCarActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.btn_1)
    TextView mBtn1;
    @BindView(R.id.btn_2)
    TextView mBtn2;
    @BindView(R.id.et_license)
    NumberEditText mNumberEditText;
    private EditText mEditText;
    private KeyboardUtil keyboardUtil;
    int type = 0;//汽油车
    private String carId;
    private String carName;

    @Override
    protected int getContentView() {
        return R.layout.activity_add_car;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initViews() {

        mEditText = mNumberEditText.getInvisibleEt();
        mNumberEditText.isXny(false);
        mTvTitle.setText("添加车辆");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        carId = getIntent().getStringExtra("carId");
        carName = getIntent().getStringExtra("carName");
        if(StringUtil.isNotBlank(carId)){
            mEditText.setText(carName);
            mTvTitle.setText("修改车辆");
        }

        mEditText.setOnTouchListener((view, event) -> {
            if (keyboardUtil == null) {
                keyboardUtil = new KeyboardUtil(AddCarActivity.this, mEditText);
                keyboardUtil.hideSoftInputMethod();
                keyboardUtil.showKeyboard();
            } else {
                keyboardUtil.showKeyboard();
            }
            return false;
        });
    }

    @OnClick({R.id.ll_back, R.id.btn_1, R.id.btn_2, R.id.bnt_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_1:
                type = 0;
                mBtn1.setBackground(getResources().getDrawable(R.drawable.bnt_car_x));
                mBtn2.setBackground(null);
                mBtn1.setTextColor(getResources().getColor(R.color.color_ffffff));
                mBtn2.setTextColor(getResources().getColor(R.color.color_text_theme));
                mNumberEditText.isXny(false);
                if(keyboardUtil!=null){
                    keyboardUtil.changeKeyboard(false);
                }
                break;
            case R.id.btn_2:
                type = 1;
                mBtn1.setBackground(null);
                mBtn2.setBackground(getResources().getDrawable(R.drawable.bnt_car_x));
                mBtn2.setTextColor(getResources().getColor(R.color.color_ffffff));
                mBtn1.setTextColor(getResources().getColor(R.color.color_text_theme));
                mNumberEditText.isXny(true);
                if(keyboardUtil!=null){
                    keyboardUtil.changeKeyboard(false);
                }
                break;
            case R.id.bnt_go:
                addCar();
                break;
        }
    }


    private void addCar() {
        String lincense = mEditText.getText().toString();
        if(true){
            ToastUtils.showShort("车牌号不能为空"+lincense);
            return;
        }
        PostCarData.PostAddCarInfo addCarInfo = new PostCarData.PostAddCarInfo();
        addCarInfo.setLicense(lincense);
        addCarInfo.setType(type);
        show();
        RetrofitUtil.getInstance().apiService()
                .addCar(addCarInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShort("添加成功");
                            AddCarActivity.this.finish();
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


    public static void startActivityIntent(Context mContext, String carId,String carName) {
        Intent mIntent = new Intent(mContext,AddCarActivity.class);
        mIntent.putExtra("carId",carId);
        mIntent.putExtra("carName",carName);
        mContext.startActivity(mIntent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (keyboardUtil.isShow()) {
                keyboardUtil.hideKeyboard();
            }
            finish();
        }
        return false;
    }
}
