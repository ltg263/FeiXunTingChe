package com.jxkj.fxtc.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.deepexp.zsnavi.bean.CoordinateBean;
import com.deepexp.zsnavi.core.ZsnaviManager;
import com.deepexp.zsnavi.enums.NaviWay;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.conpoment.utils.ZsnaviMapUtils;
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
    private SeatParkbudBean.ParkingLotBean parkingData;

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
                .getParkbud(getIntent().getStringExtra("carName"))
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
                            parkingData = data.getParkingLot();
                            mTvCqh.setText(data.getLicense());
                            mTvSzlc.setText(data.getFloor());
                            mTvSzcw.setText(data.getSeatName());
                            mTvTrsj.setText(data.getStartTime());
                            long start = StringUtil.getMsToTime(data.getStartTime(), "yyyy-MM-dd HH:mm:ss");
                            long end = System.currentTimeMillis();
                            String time = StringUtil.formatDuring(end - start);
                            mTvTrsc.setText(time);
                        }else{
                            SeekCarActivity.this.finish();
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
                finish();
                break;
            case R.id.bnt:
                if(parkingData!=null){
                    ZsnaviMapUtils.openNavi(this,
                            NaviWay.Walk,Double.valueOf(parkingData.getLat()), Double.valueOf(parkingData.getLng()),
                            parkingData.getMapCode(),parkingData.getPoiName());
                }
                break;
        }
    }
}
