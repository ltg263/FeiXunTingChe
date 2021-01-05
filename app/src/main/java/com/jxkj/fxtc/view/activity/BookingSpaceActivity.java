package com.jxkj.fxtc.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.entity.LotListBean;
import com.jxkj.fxtc.view.adapter.BookingSpaceAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookingSpaceActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_not)
    LinearLayout mLvNot;
    private BookingSpaceAdapter mBookingSpaceAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_booking_space;
    }

    @Override
    protected void initViews() {
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadMore(false);
        mLlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mLvNot.setVisibility(View.GONE);
        mRvList.setVisibility(View.VISIBLE);
        mBookingSpaceAdapter = new BookingSpaceAdapter(null);
        mRvList.setAdapter(mBookingSpaceAdapter);
        mLvNot.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);

        mBookingSpaceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtils.getInstence().intent(BookingSpaceActivity.this,BookingSpacePayActivity.class);
            }
        });
        String lng = SharedUtils.singleton().get("Longitude","");
        String lat = SharedUtils.singleton().get("Latitude","");
        getLotList(lng,lat);
    }
    private void getLotList(String lng,String lat) {
        RetrofitUtil.getInstance().apiService()
                .getLotList(null, null, lng, lat)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LotListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LotListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            mBookingSpaceAdapter.setNewData(result.getData().getList());
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
