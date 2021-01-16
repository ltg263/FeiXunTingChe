package com.jxkj.fxtc.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.HttpRequestUtils;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.conpoment.widget.MatisseUtils;
import com.jxkj.fxtc.entity.PostCarData;
import com.jxkj.fxtc.view.adapter.SpPhotoAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MineYjfkActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.et_sjh)
    EditText et_sjh;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    private SpPhotoAdapter mSpPhotoAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_yjfk;
    }

    @Override
    protected void initViews() {
        mTvTitle.setText("意见反馈");
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_h));
        initRvXq();
    }

    @OnClick({R.id.ll_back,R.id.bnt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.bnt:
                feedback();
                break;
        }
    }

    private void initRvXq() {
        mRvList.setLayoutManager(new GridLayoutManager(this, 4));
        mRvList.setHasFixedSize(true);
        mSpPhotoAdapter = new SpPhotoAdapter(null);
        mRvList.setAdapter(mSpPhotoAdapter);
        List<LocalMedia> list = new ArrayList<>();
        LocalMedia mLocalMedia = new LocalMedia();
        mLocalMedia.setPath("-1");
        list.add(mLocalMedia);
        mSpPhotoAdapter.setNewData(list);
        mSpPhotoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mSpPhotoAdapter.getData().get(position).getPath().equals("-1")) {
                    MatisseUtils.selectImg(MineYjfkActivity.this, 6 - mSpPhotoAdapter.getData().size(),false);
                }
            }
        });

        mSpPhotoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mSpPhotoAdapter.remove(position);
                listUrls.remove(position);
                if (!mSpPhotoAdapter.getData().contains(mLocalMedia)) {
                    mSpPhotoAdapter.addData(mLocalMedia);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    List<String> pathList = Matisse.obtainPathResult(data);
                    setIMaaa(selectList,0);
                    break;
            }
        }
    }

    List <String> listUrls= new ArrayList<>();

    private void setIMaaa(List<LocalMedia> selectList,int pos) {
        pos++;
        int finalI = pos;
        HttpRequestUtils.uploadFiles(selectList.get(pos-1).getPath(), new HttpRequestUtils.UploadFileInterface() {
            @Override
            public void succeed(String path) {
                listUrls.add(path);
                mSpPhotoAdapter.addData(mSpPhotoAdapter.getData().size() - 1, selectList.get(finalI-1));
                mSpPhotoAdapter.notifyDataSetChanged();
                if(finalI<selectList.size()){
                    setIMaaa(selectList,finalI);
                }
            }

            @Override
            public void failure() {

            }
        });
    }


    private void feedback() {
        String str = et.getText().toString();
        String sjh = et_sjh.getText().toString();
        if (StringUtil.isBlank(str) || StringUtil.isBlank(sjh)) {
            ToastUtils.showShort("内容或手机号不能为空");
            return;
        }
        PostCarData.Feedback feedback = new PostCarData.Feedback();
        feedback.setContent(str);
        if(listUrls.size()>0){
            feedback.setImgUrl(Arrays.asList(listUrls).toString());
        }
        feedback.setContact(sjh);
        show();
        RetrofitUtil.getInstance().apiService()
                .postFeedback(feedback)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if (isDataInfoSucceed(result)) {
                            ToastUtils.showShort("反馈成功");
                            MineYjfkActivity.this.finish();
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
