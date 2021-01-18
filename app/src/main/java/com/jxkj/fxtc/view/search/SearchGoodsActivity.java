package com.jxkj.fxtc.view.search;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.IntentUtils;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.entity.LotListBean;
import com.jxkj.fxtc.view.adapter.BookingSpaceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 搜索界面
 * 编号：1200
 */
public class SearchGoodsActivity extends BaseActivity {


    @BindView(R.id.img_top_back)
    ImageView imgTopBack;
    @BindView(R.id.tv_top_title)
    EditText searchEt;
    @BindView(R.id.activity_search_goods_search_tv)
    TextView mSearchTv;
    @BindView(R.id.rl_actionbar)
    RelativeLayout llActionbar;
    @BindView(R.id.flowlayout)
    ShoppingFlowLayout flowLayout;
    @BindView(R.id.flowlayout_rm)
    ShoppingFlowLayout flowLayoutRm;
    @BindView(R.id.activity_search_goods_history_ll)
    LinearLayout mHistoryLl;
    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    private String searchStr = null;
    private int searchType = 0;
    public static SearchGoodsActivity activity;
    private BookingSpaceAdapter mBookingSpaceAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_search_goods;
    }
    @Override
    protected void onResume() {
        super.onResume();
        setHistorySearchData();
        if(searchStr!=null){
            searchEt.setSelection(searchStr.length());//将光标移至文字末尾
            getWindow().setSoftInputMode(WindowManager.LayoutParams
                    .SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    protected void initViews() {
        searchType = getIntent().getIntExtra("searchType",0);
        if(searchType==1){
            searchEt.setHint("搜索内容或圈子");
        }else if(searchType == 2){
            searchEt.setHint("搜索要去的目的地");
        }
        activity = this;
        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
        setHistorySearchData();
        setHistorySearchDataRm();
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(searchEt.getText().toString().trim().length()>0){
                        searchStr = searchEt.getText().toString().trim();
                        startSearchResultActivity(searchEt.getText().toString().trim());
                    }else {
                        Toast.makeText(SearchGoodsActivity.this,"请输入搜索内容",Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
                return false;
            }
        });
        initRv();
    }

    private void initRv() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBookingSpaceAdapter = new BookingSpaceAdapter(null,"0");
        mRecyclerView.setAdapter(mBookingSpaceAdapter);
        mBookingSpaceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                getActivity().finish();
            }
        });

        searchEt.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("字符变换后", "afterTextChanged");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("字符变换前", s + "-" + start + "-" + count + "-" + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("字符变换中", s + "-" + "-" + start + "-" + before + "-" + count);

                String lng = SharedUtils.singleton().get("Longitude", "");
                String lat = SharedUtils.singleton().get("Latitude", "");
//                getLotList(lng, lat);
            }
        });
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
                            mRecyclerView.setVisibility(View.GONE);
                            if (result.getData().getList() != null && result.getData().getList().size() > 0) {

                                mRecyclerView.setVisibility(View.VISIBLE);
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
    private void setHistorySearchData() {
        List<String> mHistoryList = SearchHistorySpUtil.getSearchHistory(SearchGoodsActivity.this,"goods","goodsName");
        //往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        if (flowLayout != null) {
            flowLayout.removeAllViews();
        }
        for (int i = 0; i < mHistoryList.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(19, 5, 19, 5);
            tv.setText(mHistoryList.get(i));
            tv.setMaxEms(10);
            tv.setSingleLine();
                    tv.setBackgroundResource(R.drawable.shap_f5f5f5_15);
            tv.setLayoutParams(layoutParams);
            final String name = mHistoryList.get(i);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchStr = name;
                    searchEt.setText(name);
                    startSearchResultActivity(searchEt.getText().toString().trim());

                }
            });
            flowLayout.addView(tv, layoutParams);
        }
    }
    private void setHistorySearchDataRm() {
        List<String> list = new ArrayList<>();
        list.add("123456");
        list.add("1234");
        list.add("123455456");
        list.add("12asdfads6");
        //往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        if (flowLayoutRm != null) {
            flowLayoutRm.removeAllViews();
        }
        for (int i = 0; i < list.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(19, 5, 19, 5);
            tv.setText(list.get(i));
            tv.setMaxEms(10);
            tv.setSingleLine();
            tv.setBackgroundResource(R.drawable.shap_f5f5f5_15);
            tv.setLayoutParams(layoutParams);
            final String name = list.get(i);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchStr = name;
                    searchEt.setText(name);
                    startSearchResultActivity(searchEt.getText().toString().trim());
                }
            });
            flowLayoutRm.addView(tv, layoutParams);
        }
    }

    @OnClick({R.id.img_top_back, R.id.activity_search_goods_search_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_top_back:
                finish();
                break;
            case R.id.activity_search_goods_search_tv:
                if(searchEt.getText().toString().trim().length()>0){
                    startSearchResultActivity(searchEt.getText().toString().trim());
                }else {
                    Toast.makeText(SearchGoodsActivity.this,"请输入搜索内容",Toast.LENGTH_LONG).show();

                }
                break;
                default:
        }
    }

    private void startSearchResultActivity(String inputText){
        SearchHistorySpUtil.saveSearchHistory(SearchGoodsActivity.this,"goods","goodsName",inputText);
        if(searchType==1){
//            IntentUtils.getInstence().intent(SearchGoodsActivity.this, SearchResultGoodsActivity.class,"search",inputText);
        }
        if(searchType==2){
            IntentUtils.getInstence().intent(SearchGoodsActivity.this, SearchResultTopicActivity.class,"search",inputText);
        }
    }


}
