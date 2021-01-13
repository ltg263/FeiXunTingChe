package com.jxkj.fxtc.conpoment.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

import com.deepexp.zsnavi.bean.ColorPoiBean;
import com.deepexp.zsnavi.bean.CoordinateBean;
import com.deepexp.zsnavi.bean.OptionBean;
import com.deepexp.zsnavi.callback.IMapCallback;
import com.deepexp.zsnavi.callback.INavigationCallback;
import com.deepexp.zsnavi.core.ZsnaviManager;
import com.deepexp.zsnavi.enums.NaviWay;
import com.jxkj.fxtc.view.activity.ImageAcy;

import java.util.ArrayList;
import java.util.List;

public class ZsnaviMapUtils {
    /**
     * 打开导航
     */
    public static void openNavi(Activity mContext,NaviWay way,Double lat,Double lng,String mapCode,String poiName) {
        ZsnaviManager.getInstance(mContext).init(new OptionBean("zssw",true));//初始化地图
        ZsnaviManager.getInstance(mContext).setOnMapCallback(new IMapCallback() {
            @Override
            public void onMapReady() {
                Toast.makeText(mContext, "地图加载完成", Toast.LENGTH_SHORT).show();

                List<ColorPoiBean> colorPois = new ArrayList<>();

                colorPois.add(new ColorPoiBean(mapCode, poiName, Color.RED));

                ZsnaviManager.getInstance(mContext).setPoisColor(colorPois);//修改车位颜色
            }

            @Override
            public void onConfigFailure(int code, String message) {
                ZsnaviManager.getInstance(mContext).destory();//关闭地图或者导航页面
                Toast.makeText(mContext, "地图配置失败" + message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadFailure(int code, String message) {
                ZsnaviManager.getInstance(mContext).destory();//关闭地图或者导航页面
                Toast.makeText(mContext, "地图加载失败" + message, Toast.LENGTH_SHORT).show();
            }
        });//注册地图回调
        ZsnaviManager.getInstance(mContext).setOnNavigationCallback(new INavigationCallback() {
            @Override
            public void onNaviStart() {
                Toast.makeText(mContext, "导航开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNaviEnd() {
                mContext.startActivity(new Intent(mContext,ImageAcy.class));
                Toast.makeText(mContext, "到达目的地", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNaviExit() {
                Toast.makeText(mContext, "退出导航", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNaviInfoUpdate() {
                Toast.makeText(mContext, "导航更新", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNaviText(String s) {
                Toast.makeText(mContext, "语音播报信息：" + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInitNaviFailure() {
                Toast.makeText(mContext, "导航初始化失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNaviRouteFailure() {
                Toast.makeText(mContext, "导航规划失败", Toast.LENGTH_SHORT).show();
            }
        });//注册导航回调

        ZsnaviManager.getInstance(mContext).startNavi(way, new CoordinateBean(lat, lng), true);//开启导航
    }

}
