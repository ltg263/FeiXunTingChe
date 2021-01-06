package com.jxkj.fxtc.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.api.RetrofitUtil;
import com.jxkj.fxtc.app.ConstValues;
import com.jxkj.fxtc.base.BaseActivity;
import com.jxkj.fxtc.base.Result;
import com.jxkj.fxtc.conpoment.utils.DialogUtils;
import com.jxkj.fxtc.conpoment.utils.GlideCircleTransform;
import com.jxkj.fxtc.conpoment.utils.GlideImgLoader;
import com.jxkj.fxtc.conpoment.utils.HttpRequestUtils;
import com.jxkj.fxtc.conpoment.utils.PickerViewUtils;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;
import com.jxkj.fxtc.conpoment.utils.StringUtil;
import com.jxkj.fxtc.conpoment.utils.TextUtil;
import com.jxkj.fxtc.conpoment.widget.MatisseUtils;
import com.zhihu.matisse.Matisse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MineGrzxRecordActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.riv_icon_pic)
    ImageView mRivIconPic;
    @BindView(R.id.tv_mine_nc)
    TextView mTvMineNc;
    @BindView(R.id.tv_mine_zh)
    TextView mTvMineZh;
    @BindView(R.id.tv_mine_sr)
    TextView mTvMineSr;
    @BindView(R.id.tv_mine_xb)
    TextView mTvMineXb;
    @BindView(R.id.tv_mine_nl)
    TextView mTvMineNl;
    @BindView(R.id.tv_mine_dq)
    TextView mTvMineDq;
    @BindView(R.id.et_mine_jj)
    EditText mEtMineJj;
    private List<String> mFeedTypeList = new ArrayList<>();

    String avatar = "";
    @Override
    protected int getContentView() {
        return R.layout.activity_mine_record;
    }

    @Override
    protected void initViews() {
        initTitle();
        initDataUi();
    }

    private void initDataUi() {
        avatar = SharedUtils.singleton().get(ConstValues.AVATAR,"");
        GlideImgLoader.loadImageAndHeadDefault(this, avatar, mRivIconPic);
        mTvMineNc.setText(SharedUtils.singleton().get(ConstValues.USER_NAME,""));
        if(SharedUtils.singleton().get(ConstValues.GENDER,"").equals("1")){
            mTvMineXb.setText("男");
        }else{
            mTvMineXb.setText("女");
        }
        mTvMineSr.setText(SharedUtils.singleton().get(ConstValues.BIRTHDAY,""));
    }

    private void initTitle() {
        mIvBack.setImageDrawable(getResources().getDrawable(R.mipmap.back_h));
        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
        mTvTitle.setText("个人资料");
        mTvRighttext.setText("提交");
    }

    @OnClick({R.id.ll_back, R.id.tv_righttext, R.id.riv_icon_pic, R.id.tv_mine_nc, R.id.tv_mine_zh, R.id.tv_mine_sr, R.id.tv_mine_xb, R.id.tv_mine_nl, R.id.tv_mine_dq, R.id.et_mine_jj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_righttext:
                submitData();
                break;
            case R.id.riv_icon_pic:
                MatisseUtils.gotoSelectPhoto(this, 1);
                break;
            case R.id.tv_mine_nc:
                DialogUtils.showEditTextDialog(this, -1, "修改昵称", "输入昵称", season -> {
                    mTvMineNc.setText(season);
                });
                break;
            case R.id.tv_mine_zh:
                break;
            case R.id.tv_mine_sr:
                PickerViewUtils.selectorDate(1900,2020,this, new boolean[]{true, true, true, false, false, false},
                        time -> mTvMineSr.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(time)));
                break;
            case R.id.tv_mine_xb:
                mFeedTypeList.clear();
                mFeedTypeList.add("男");
                mFeedTypeList.add("女");
                PickerViewUtils.selectorCustom(this, mFeedTypeList, "选择性别", mTvMineXb);
                break;
            case R.id.tv_mine_nl:
                break;
            case R.id.tv_mine_dq:
                break;
            case R.id.et_mine_jj:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextUtil.logOut(requestCode + "");
        if (resultCode == RESULT_OK) {
            if (requestCode == MatisseUtils.REQUEST_CODE_XC) {
                //图片路径 同样视频地址也是这个 根据requestCode
                List<String> pathList = Matisse.obtainPathResult(data);
//                MatisseUtils.cropRawPhoto(this,pathList.get(0));
                if (pathList != null && pathList.size() > 0) {
                    GlideCircleTransform.glideCircleImg(pathList.get(0), mRivIconPic);
                    postImg(pathList.get(0));
                }

            }
        }//1009.37-962=77.37  915.37+
    }

    private void postImg(final String listPath) {
        show();
        HttpRequestUtils.uploadFiles(listPath, new HttpRequestUtils.UploadFileInterface() {
            @Override
            public void succeed(String path) {
                dismiss();
                avatar = path;
                GlideImgLoader.loadImageAndHeadDefault(MineGrzxRecordActivity.this,avatar, mRivIconPic);
            }

            @Override
            public void failure() {
                dismiss();
            }
        });
    }
    private void submitData() {
        String gender = "1";
        if(mTvMineXb.getText().toString().equals("女")){
            gender ="2";
        }
        String birthday = mTvMineSr.getText().toString();
        String nineNage = mTvMineNc.getText().toString();
        if(StringUtil.isBlank(birthday) || StringUtil.isBlank(nineNage)){
            ToastUtils.showShort("信息填写不完整");
            return;
        }
        show();
        RetrofitUtil.getInstance().apiService()
                .getUserUpdate(avatar,birthday,gender,nineNage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        if(isDataInfoSucceed(result)){
                            ToastUtils.showShort("修改成功");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
    }
}
