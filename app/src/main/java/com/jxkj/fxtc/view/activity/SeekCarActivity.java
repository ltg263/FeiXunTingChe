package com.jxkj.fxtc.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.entity.SeatParkbudBean;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SeekCarActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_cqh)
    TextView mTvCqh;
    @BindView(R.id.tv_szlc)
    TextView mTvSzlc;
    @BindView(R.id.tv_szcw)
    TextView mTvSzcw;
    @BindView(R.id.tv_trsj)
    TextView mTvTrsj;
    @BindView(R.id.tv_trsc)
    TextView mTvTrsc;

    @Override
    protected int getContentView() {
        return R.layout.activity_seek_car;
    }

    @Override
    protected void initViews() {
        mIvBack.setImageDrawable(getResources().getDrawable(R.mipmap.back_h));
        mTvTitle.setText("停车导航");
        getParkbud();
    }

    private void getParkbud() {
        RetrofitUtil.getInstance().apiService()
                .getParkbud("")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<SeatParkbudBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SeatParkbudBean> result) {
                        if (isDataInfoSucceed(result)) {
                            SeatParkbudBean data = result.getData();
                            mTvCqh.setText(data.getLicense());
                            mTvSzlc.setText(data.getFloor());
                            mTvSzcw.setText(data.getSeatName());
                            mTvTrsc.setText("");
                            mTvTrsj.setText("");
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

    @OnClick({R.id.ll_back, R.id.bnt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                break;
            case R.id.bnt:
                break;
        }
    }
}
