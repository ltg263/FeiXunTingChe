package com.jxkj.fxtc.conpoment.widget;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jxkj.fxtc.R;

public class PopupAddPharmacyUtils extends PopupWindow {

    Context mcontext;

    @SuppressLint("ClickableViewAccessibility")
    public PopupAddPharmacyUtils(Activity context, GiveDialogInterface dialogInterface) {
        super(context);
        this.mcontext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.view_pharmacy_popu, null);

        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ActionBar.LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点�?
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        setEvent(view, dialogInterface);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener((v, event) -> {

            int height = view.findViewById(R.id.pop_layout).getTop();
            int y = (int) event.getY();
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (y < height) {
                    dismiss();
                }
            }
            return true;
        });
    }
    ImageView iv_icon;
    TextView tv_content;
    EditText et_num;
    private void setEvent(View view, GiveDialogInterface dialogInterface) {
        view.findViewById(R.id.tv_qx).setOnClickListener(v -> dismiss());
        view.findViewById(R.id.tv_qr).setOnClickListener(v -> {
            if(Integer.valueOf(et_num.getText().toString())>0){
                dialogInterface.btnConfirm(0,et_num.getText().toString());
            }
            dismiss();
        });
        iv_icon= view.findViewById(R.id.iv_icon);
        tv_content= view.findViewById(R.id.tv_content);
        et_num= view.findViewById(R.id.et_num);
    }
    public void setData (String content){
//        Glide.with(mcontext).load(imgPath).into(iv_icon);
        tv_content.setText(content);
    }


    public interface GiveDialogInterface {
        /**
         * 确定
         */
        void btnConfirm(int topicId, String str);
    }


}
