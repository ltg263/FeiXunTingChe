package com.jxkj.fxtc.view.search;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.entity.LotListBean;
import com.jxkj.fxtc.view.adapter.BookingSpaceAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchResultTopicActivity extends BaseActivity {


    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.rv_shopping_cart)
    RecyclerView mRvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String search;
    private BookingSpaceAdapter mBookingSpaceAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_search_result_topic;
    }

    @Override
    protected void initViews() {
        search = getIntent().getStringExtra("search");
        tvTopTitle.setText(search);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);

        mBookingSpaceAdapter = new BookingSpaceAdapter(null);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mBookingSpaceAdapter);

        mBookingSpaceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        getAllTopic();
    }

    @OnClick({R.id.img_top_back, R.id.tv_top_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_top_back:
            case R.id.tv_top_title:
                finish();
                break;
        }
    }

    private void getAllTopic(){
        RetrofitUtil.getInstance().apiService()
                .getLotList("",search,"","")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<LotListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<LotListBean> result) {
                        if (isDataInfoSucceed(result)) {
                            if(result.getData().getList()!=null && result.getData().getList().size()>0){
                                llNoData.setVisibility(View.GONE);
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


}
