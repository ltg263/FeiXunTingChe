package com.jxkj.fxtc.view.activity;

import android.content.Intent;
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
import com.jxkj.fxtc.view.search.SearchGoodsActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookingSpaceActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
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

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mBookingSpaceAdapter = new BookingSpaceAdapter(null);
        mRvList.setAdapter(mBookingSpaceAdapter);
        mBookingSpaceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LotListBean.ListBean data = mBookingSpaceAdapter.getData().get(position);
                Intent mIntent = new Intent(BookingSpaceActivity.this, BookingSpaceDeActivity.class);
                mIntent.putExtra("data", data);
                startActivity(mIntent);
            }
        });
        String lng = SharedUtils.singleton().get("Longitude", "");
        String lat = SharedUtils.singleton().get("Latitude", "");
        getLotList(lng, lat);
    }

    private void getLotList(String lng, String lat) {
        RetrofitUtil.getInstance().apiService()
                .getLotList(null, null, lng, lat,null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LotListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LotListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData().getList() != null && result.getData().getList().size() > 0) {
                                mLvNot.setVisibility(View.GONE);
                                mRefreshLayout.setVisibility(View.VISIBLE);
                                mBookingSpaceAdapter.setNewData(result.getData().getList());
                            }
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

    @OnClick({R.id.ll_back, R.id.ll_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_search:
                IntentUtils.getInstence().intent(this, SearchGoodsActivity.class,"searchType",2);
                break;
        }
    }
}
