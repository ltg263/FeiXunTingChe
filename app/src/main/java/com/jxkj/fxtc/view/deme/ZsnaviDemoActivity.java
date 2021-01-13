package com.jxkj.fxtc.view.deme;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.deepexp.zsnavi.base.BaseActivity;
import com.deepexp.zsnavi.bean.ColorPoiBean;
import com.deepexp.zsnavi.bean.CoordinateBean;
import com.deepexp.zsnavi.bean.OptionBean;
import com.deepexp.zsnavi.callback.ILocationCallback;
import com.deepexp.zsnavi.callback.IMapCallback;
import com.deepexp.zsnavi.callback.INavigationCallback;
import com.deepexp.zsnavi.core.ZsnaviManager;
import com.deepexp.zsnavi.enums.NaviWay;
import com.jxkj.fxtc.R;
import com.jxkj.fxtc.conpoment.utils.SharedUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Zsnavi导航Demo
 */
public class ZsnaviDemoActivity extends BaseActivity {
    //region fields

    private Button mBtnMap;
    private Button mBtnDrive;
    private Button mBtnWalk;
    private Button mBtnCal;
    private Button mBtnSave;
    private EditText mEdtCode;
    private EditText mEdtLat;
    private EditText mEdtLon;
    private EditText mEdtPoiMap;
    private EditText mEdtPoiName;
    private RadioButton mRbProd;
    private RadioButton mRbTest;
    private RadioButton mRbCast;
    private RadioButton mRbNoCast;
    private SharedPreferences appConfig;
    Context myContext;
    //endregion

    //region methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zsnavi_demo_layout);
        this.showActionBar();
        myContext = this;
        mBtnMap = (Button) findViewById(R.id.zsnavi_demo_btn_map);
        mBtnDrive = (Button) findViewById(R.id.zsnavi_demo_btn_drive);
        mBtnWalk = (Button) findViewById(R.id.zsnavi_demo_btn_walk);
        mBtnCal = (Button) findViewById(R.id.zsnavi_demo_btn_calculate);
        mBtnSave = (Button) findViewById(R.id.zsnavi_demo_btn_save);
        mEdtCode = (EditText) findViewById(R.id.zsnavi_demo_edt_code);
        mEdtLat = (EditText) findViewById(R.id.zsnavi_demo_edt_latitude);
        mEdtLon = (EditText) findViewById(R.id.zsnavi_demo_edt_longitude);
        mEdtPoiMap = (EditText) findViewById(R.id.zsnavi_demo_edt_poi_map);
        mEdtPoiName = (EditText) findViewById(R.id.zsnavi_demo_edt_poi_name);
        mRbProd = (RadioButton) findViewById(R.id.zsnavi_demo_rb_prod);
        mRbTest = (RadioButton) findViewById(R.id.zsnavi_demo_rb_test);
        mRbCast = (RadioButton) findViewById(R.id.zsnavi_demo_rb_cast);
        mRbNoCast = (RadioButton) findViewById(R.id.zsnavi_demo_rb_nocast);

        mBtnMap.setOnClickListener(onClickListener);
        mBtnDrive.setOnClickListener(onClickListener);
        mBtnWalk.setOnClickListener(onClickListener);
        mBtnCal.setOnClickListener(onClickListener);
        mBtnSave.setOnClickListener(onClickListener);

        appConfig = this.getApplication().getSharedPreferences("APPConfig",
                Context.MODE_PRIVATE);

        this.loadSetting();
        String lng = SharedUtils.singleton().get("Longitude","");
        String lat = SharedUtils.singleton().get("Latitude","");
        mEdtLon.setText(lng);
        mEdtLat.setText(lat);
    }

    /**
     * 加载配置
     */
    private void loadSetting() {
        mEdtCode.setText(this.getData("code"));
        mEdtLat.setText(this.getData("lat"));
        mEdtLon.setText(this.getData("lon"));
        mEdtPoiMap.setText(this.getData("map"));
        mEdtPoiName.setText(this.getData("name"));

        String mode = this.getData("mode");
        if (mode.equals("")) {
            mRbTest.setChecked(true);
        } else {
            if (mode.equals("prod")) {
                mRbProd.setChecked(true);
            } else {
                mRbTest.setChecked(true);
            }
        }

        String cast = this.getData("cast");
        if (cast.equals("")) {
            mRbCast.setChecked(true);
        } else {
            if (mode.equals("enable")) {
                mRbCast.setChecked(true);
            } else {
                mRbNoCast.setChecked(true);
            }
        }
    }

    /**
     * 检查设置
     *
     * @return
     */
    private boolean checkSetting() {
        if (mEdtCode.getText().toString().equals("")) {
            return false;
        }

        if (mEdtPoiMap.getText().toString().equals("")) {
            return false;
        }

        if (mEdtPoiName.getText().toString().equals("")) {
            return false;
        }

        String latStr = mEdtLat.getText().toString();

        if (latStr.equals("") || !this.isDoubleOrFloat(latStr)) {
            return false;
        }

        String lonStr = mEdtLon.getText().toString();

        if (lonStr.equals("") || !this.isDoubleOrFloat(lonStr)) {
            return false;
        }

        return true;
    }

    /**
     * 保存配置
     */
    private void saveSetting() {
        if (this.checkSetting()) {
            this.saveData("code", mEdtCode.getText().toString());
            this.saveData("lat", mEdtLat.getText().toString());
            this.saveData("lon", mEdtLon.getText().toString());
            this.saveData("mode", mRbTest.isChecked() ? "test" : "prod");
            this.saveData("map", mEdtPoiMap.getText().toString());
            this.saveData("name", mEdtPoiName.getText().toString());
            this.saveData("cast", mRbCast.isChecked() ? "enable" : "disable");
            Toast.makeText(ZsnaviDemoActivity.this, "参数保存成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ZsnaviDemoActivity.this, "参数设置有误", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 校验数据
     *
     * @param str
     * @return
     */
    private boolean isDoubleOrFloat(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 保存数据
     *
     * @param key
     * @param value
     */
    public void saveData(String key, String value) {
        SharedPreferences.Editor appEditor = appConfig.edit();
        appEditor.putString(key, value);
        appEditor.commit();
    }

    /**
     * 保存数据
     *
     * @param key
     */
    public String getData(String key) {
        return appConfig.getString(key, "");
    }

    //region 核心代码

    /**
     * 打开地图
     */
    private void openMap() {
        ZsnaviManager.getInstance(this).init(new OptionBean(mEdtCode.getText().toString(), mRbTest.isChecked()));//初始化地图
//        ZsnaviManager.getInstance(this).setOnMapCallback(mapCallback);//注册地图回调
//
//        ZsnaviManager.getInstance(this).showMap();//显示地图
    }

    /**
     * 打开导航
     */
    private void openNavi(NaviWay way) {
        ZsnaviManager.getInstance(this).init(new OptionBean(mEdtCode.getText().toString(), mRbTest.isChecked()));//初始化地图
        ZsnaviManager.getInstance(this).setOnMapCallback(mapCallback);//注册地图回调
        ZsnaviManager.getInstance(this).setOnNavigationCallback(navigationCallback);//注册导航回调

        ZsnaviManager.getInstance(this).startNavi(way, new CoordinateBean(Double.valueOf(mEdtLat.getText().toString()), Double.valueOf(mEdtLon.getText().toString())), mRbCast.isChecked());//开启导航
    }

    /**
     * 开始定位（使用定位前必须请求定位权限，否则定位失败）
     */
    private void startLocation() {
        ZsnaviManager.getInstance(this).setOnLocationCallback(locationCallback);//设置定位回调
        ZsnaviManager.getInstance(this).startLocation();//开启定位，该定位只会回调一次定位信息，建议使用完后调用停止定位接口
    }

    /**
     * 计算距离
     *
     * @param position
     */
    private void calDistance(CoordinateBean position) {
        List<CoordinateBean> ends = new ArrayList<>();

        ends.add(new CoordinateBean(Double.valueOf(mEdtLat.getText().toString()), Double.valueOf(mEdtLon.getText().toString())));

        List<Float> distances = ZsnaviManager.getInstance(this).calculateDistance(position, ends);//计算距离

        Float distance = distances != null && distances.size() > 0 ? distances.get(0) : 0f;

        Toast.makeText(ZsnaviDemoActivity.this, "定位距离" + distance, Toast.LENGTH_SHORT).show();
    }

    //endregion

    //endregion

    //region events

    /**
     * 事件
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (!checkSetting()) {
                Toast.makeText(ZsnaviDemoActivity.this, "参数设置有误", Toast.LENGTH_SHORT).show();
                return;
            }

            switch (view.getId()) {
                case R.id.zsnavi_demo_btn_map:
                    openMap();
                    break;
                case R.id.zsnavi_demo_btn_drive:
                    openNavi(NaviWay.Drive);
                    break;
                case R.id.zsnavi_demo_btn_walk:
                    openNavi(NaviWay.Walk);
                    break;
                case R.id.zsnavi_demo_btn_calculate:
                    startLocation();
                    break;
                case R.id.zsnavi_demo_btn_save:
                    saveSetting();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 地图回调
     */
    IMapCallback mapCallback = new IMapCallback() {
        @Override
        public void onMapReady() {
            Toast.makeText(ZsnaviDemoActivity.this, "地图加载完成", Toast.LENGTH_SHORT).show();

            List<ColorPoiBean> colorPois = new ArrayList<>();

            colorPois.add(new ColorPoiBean(mEdtPoiMap.getText().toString(), mEdtPoiName.getText().toString(), Color.RED));

            ZsnaviManager.getInstance(ZsnaviDemoActivity.this).setPoisColor(colorPois);//修改车位颜色
        }

        @Override
        public void onConfigFailure(int code, String message) {
            ZsnaviManager.getInstance(ZsnaviDemoActivity.this).destory();//关闭地图或者导航页面
            Toast.makeText(ZsnaviDemoActivity.this, "地图配置失败" + message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLoadFailure(int code, String message) {
            ZsnaviManager.getInstance(ZsnaviDemoActivity.this).destory();//关闭地图或者导航页面
            Toast.makeText(ZsnaviDemoActivity.this, "地图加载失败" + message, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 定位回调，定位成功后才能计算距离
     */
    ILocationCallback locationCallback = new ILocationCallback() {
        @Override
        public void onLocationSuccess(CoordinateBean position) {
            Toast.makeText(ZsnaviDemoActivity.this, "定位坐标" + position.getLatitude() + "----" + position.getLongitude(), Toast.LENGTH_SHORT).show();
            ZsnaviManager.getInstance(ZsnaviDemoActivity.this).stopLocation();//因为是一次定位，建议每次用完后关闭
            calDistance(position);
        }

        @Override
        public void onLocationFailure() {
            Toast.makeText(ZsnaviDemoActivity.this, "定位坐标失败", Toast.LENGTH_SHORT).show();
            ZsnaviManager.getInstance(ZsnaviDemoActivity.this).stopLocation();//因为是一次定位，建议每次用完后关闭
        }
    };

    /**
     * 导航回调
     */
    INavigationCallback navigationCallback = new INavigationCallback() {
        @Override
        public void onNaviStart() {
            Toast.makeText(ZsnaviDemoActivity.this, "导航开始", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNaviEnd() {
            Toast.makeText(ZsnaviDemoActivity.this, "到达目的地", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNaviExit() {
            Toast.makeText(ZsnaviDemoActivity.this, "退出导航", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNaviInfoUpdate() {
            Toast.makeText(ZsnaviDemoActivity.this, "导航更新", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNaviText(String s) {
            Toast.makeText(ZsnaviDemoActivity.this, "语音播报信息：" + s, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onInitNaviFailure() {
            Toast.makeText(ZsnaviDemoActivity.this, "导航初始化失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNaviRouteFailure() {
            Toast.makeText(ZsnaviDemoActivity.this, "导航规划失败", Toast.LENGTH_SHORT).show();
        }
    };

    //endregion
}