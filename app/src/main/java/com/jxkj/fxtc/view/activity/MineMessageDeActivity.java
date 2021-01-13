package com.jxkj.fxtc.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.entity.MessageListBeanDe;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineMessageDeActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.tv3)
    TextView mTv3;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_message_de;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("我的消息");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        mLlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getUserDetail(getIntent().getStringExtra("id"));
    }

    private void getUserDetail(String id) {
        RetrofitUtil.getInstance().apiService()
                .getMessageDeList(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<MessageListBeanDe>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MessageListBeanDe> result) {
                        if (isDataInfoSucceed(result)) {
                            if (result.getData().getMessageType().equals("1")) {
                                mTvTitle.setText("系统消息");
                            } else if (result.getData().getMessageType().equals("2")) {
                                mTvTitle.setText("交易消息");
                            } else if (result.getData().getMessageType().equals("3")) {
                                mTvTitle.setText("活动消息");
                            }
                            mTv1.setText(result.getData().getTitle());
                            mTv2.setText(result.getData().getCreateTime());
                            mTv3.setText(result.getData().getDetail());

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
