package com.jxkj.fxtc.conpoment.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.jxkj.fxtc.R;
import com.jxkj.fxtc.conpoment.widget.CodeUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DialogUtils {

    public static AlertDialog cancelDialog(Context context, String title, String content, DialogInterface.OnClickListener listener) {

        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("确定", listener)
                .setNegativeButton("取消", null)
                .create();

    }

    public static AlertDialog donelDialog(Context context, String title, String content) {

        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("确定", null)
                .create();

    }

    public interface DialogLyInterface {
        /**
         * 确定
         */
        public void btnConfirm();
    }

    /**
     * 链接蓝牙
     *
     * @param context
     * @param title
     * @param state
     * @param dialogLyInterface
     */
    public static void showDialogLyState(Activity context, String title, int state, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.simpleDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_lanyan_state, null);

//        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
//        Bitmap bitmap=screenShotWithoutStatusBar(context);
//        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_state = view.findViewById(R.id.tv_state);
        TextView tv_ok = view.findViewById(R.id.tv_ok);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        ImageView iv_icon = view.findViewById(R.id.iv_icon);
        tv_name.setText(title);
        if (title.equals("智能手套")) {
            iv_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dialog_1));
        } else if (title.equals("呼吸带")) {
            iv_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dialog_2));
        } else if (title.equals("压力传感器")) {
            iv_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dialog_3));
        } else if (title.equals("电刺激")) {
            iv_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dialog_4));
        }
        if (state == 1) {
            tv_state.setText("蓝牙连接传感器成功!");
            tv_ok.setText("确 定");
            iv_close.setVisibility(View.INVISIBLE);
        } else {
            tv_state.setText("蓝牙连接传感器失败");
            tv_ok.setText("重新连接");
        }
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }

    /**
     * Excel导出
     *
     * @param context
     * @param title
     * @param state
     * @param dialogLyInterface
     */
    public static void showDialogExcel(Activity context, String title, int state, final DialogLyInterface dialogLyInterface) {

        final Dialog dialog = new Dialog(context, R.style.simpleDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_excel_state, null);

//        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
//        Bitmap bitmap=screenShotWithoutStatusBar(context);
//        rl_parent.setBackground(new BitmapDrawable(context.getResources(),blurBitmap(context,bitmap,25)));

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_state = view.findViewById(R.id.tv_state);
        TextView tv_ok = view.findViewById(R.id.tv_ok);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        tv_name.setText(title);
//        Glide.with(context).load(imgUrl).into(iv_icon);
        if (state == 0) {
            tv_state.setText("数据生成Excel已导出!");
            tv_ok.setText("确 定");
        } else {
            tv_state.setText("数据生成Excel失败");
            tv_ok.setText("重新连接");
        }
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLyInterface.btnConfirm();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }

    /**
     * 描述: 自定义ShowUnifiedDialog
     * 统一 确认取消的Dialog
     */
    public static void showEditTextDialog(final Context context, int type, String title, String content, final EditTextDialogInterface editTextDialogInterface) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_with_edittext, null);// 获得dialog布局
        final Dialog dialog = new Dialog(context, R.style.simpleDialog);
        dialog.setContentView(view);
        dialog.show();
        TextView tvLogPrompt = view.findViewById(R.id.tv_log_prompt);
        final EditText etLogContent = view.findViewById(R.id.et_log_content);
        etLogContent.setMaxLines(10);
        if (StringUtil.isNotBlank(content)) {
            etLogContent.setHint(content);
        }
        if (type == 1) {
            etLogContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        if (type == 2) {
            etLogContent.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        TextView tvLogConfirm = view.findViewById(R.id.tv_log_confirm);
        TextView tvLogCancel = view.findViewById(R.id.tv_log_cancel);

        tvLogPrompt.setText(title);
        // 确定
        tvLogConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String season = etLogContent.getText().toString().trim();
                editTextDialogInterface.btnConfirm(season);
                dialog.dismiss();
            }

        });
        // 取消
        tvLogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }

    public interface EditTextDialogInterface {
        void btnConfirm(String string);
    }

    public interface DialogEwmInterface {
        void btnConfirm(String string);
    }


    /**
     * 描述: 自定义ShowUnifiedDialog
     * 统一 确认取消的Dialog
     */
    public static void showShopingErm(final Context context, int type, String title, String content, final DialogEwmInterface dialogInterface) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_shopping_erm, null);// 获得dialog布局
        final Dialog dialog = new Dialog(context, R.style.simpleDialog);
        dialog.setContentView(view);
        dialog.show();
        TextView tv_submit = view.findViewById(R.id.tv_submit);
        // 确定
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialogInterface.btnConfirm("");
                getImgPath(context, view);
                dialog.dismiss();
            }

        });
    }

    /**
     * 描述: 自定义ShowUnifiedDialog
     * 统一 确认取消的Dialog
     */
    public static void showShopingErm_tx(final Context context, String content) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_erm_tx, null);// 获得dialog布局
        final Dialog dialog = new Dialog(context, R.style.simpleDialog);
        dialog.setContentView(view);
        dialog.show();
        TextView tv_1 = view.findViewById(R.id.tv_1);
        TextView tv_2 = view.findViewById(R.id.tv_2);
        ImageView iv = view.findViewById(R.id.iv);
        iv.setImageBitmap(CodeUtils.createBarcode(content));
        tv_2.setText(content);
    }

    private static Handler mHandler = new Handler();

    public static String getImgPath(Context context, View view) {
        if (view == null) {
            return "";
        }
        // 获取图片某布局
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 要在运行在子线程中
                final Bitmap bmp = view.getDrawingCache(); // 获取图片
                savePicture(bmp, "test.jpg");// 保存图片
                view.destroyDrawingCache(); // 保存过后释放资源
                Message msg = new Message();
                msg.obj = "-";
//                mHandler.sendMessage(msg);
                ToastUtil.showLongStrToast(context, "保存成功");
            }
        }, 100);

        return "";
    }

    public static void savePicture(Bitmap bm, String fileName) {
        Log.i("xing", "savePicture: ------------------------");
        if (null == bm) {
            Log.i("xing", "savePicture: ------------------图片为空------");
            return;
        }
        File foder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test");
        if (!foder.exists()) {
            foder.mkdirs();
        }
        File myCaptureFile = new File(foder, fileName);
        try {
            if (!myCaptureFile.exists()) {
                myCaptureFile.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            //压缩保存到本地
            bm.compress(Bitmap.CompressFormat.JPEG, 90, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Toast.makeText(this, "保存成功!", Toast.LENGTH_SHORT).show();
    }

    public static void showDialogHint(Context context, String title, boolean isOne, final ErrorDialogInterface dialogConfirm) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_hine, null);
        TextView bt_ok = (TextView) view.findViewById(R.id.bt_confirm);
        TextView suanle = (TextView) view.findViewById(R.id.bt_suanle);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(title);
        if (isOne) {
            suanle.setVisibility(View.GONE);
        }
        suanle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog5.dismiss();
            }
        });
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogConfirm != null) {
                    dialogConfirm.btnConfirm();
                }
                dialog5.dismiss();
            }
        });
        dialog5.setCancelable(false);
        dialog5.setContentView(view);
        dialog5.show();
    }

    public interface ErrorDialogInterface {
        /**
         * 确定
         */
        public void btnConfirm();
    }

    public interface DialogInterface1 {
        /**
         * 确定
         */
        public void btnConfirm(int pos);
    }

    public static void showDialogStartYd(Activity context, final DialogInterface1 dialogInterface) {

        final Dialog dialog = new Dialog(context, R.style.Dialog_Fullscreen);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_start_sop, null);

        LinearLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap = screenShotWithoutStatusBar(context);
        rl_parent.setBackground(new BitmapDrawable(context.getResources(), blurBitmap(context, bitmap, 10)));
        TextView tv_1 = view.findViewById(R.id.tv_1);
        TextView tv_2 = view.findViewById(R.id.tv_2);
        TextView tv_3 = view.findViewById(R.id.tv_3);
        TextView tv_4 = view.findViewById(R.id.tv_4);
        TextView tv_5 = view.findViewById(R.id.tv_5);
        ImageView iv = view.findViewById(R.id.iv);
        iv.setOnClickListener(v -> dialog.dismiss());
        tv_1.setOnClickListener(v -> dialogInterface.btnConfirm(0));
        tv_2.setOnClickListener(v -> dialogInterface.btnConfirm(1));
        tv_3.setOnClickListener(v -> dialogInterface.btnConfirm(2));
        tv_4.setOnClickListener(v -> dialogInterface.btnConfirm(3));
        tv_5.setOnClickListener(v -> dialogInterface.btnConfirm(4));

        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();
    }

    // 图片缩放比例(即模糊度)
    private static final float BITMAP_SCALE = 0.1f;

    public static Bitmap blurBitmap(Activity activity, Bitmap image, float blurRadius) {
        // 计算图片缩小后的长宽
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        // 将缩小后的图片做为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        // 创建一张渲染后的输出图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(activity);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(blurRadius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);
        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }

    public static Bitmap screenShotWithoutStatusBar(Activity activity) {
        //通过window的源码可以看出：检索顶层窗口的装饰视图，可以作为一个窗口添加到窗口管理器
        View view = activity.getWindow().getDecorView();
        //启用或禁用绘图缓存
        view.setDrawingCacheEnabled(true);
        //创建绘图缓存
        view.buildDrawingCache();
        //拿到绘图缓存
        Bitmap bitmap = view.getDrawingCache();

        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

        //状态栏高度
        int statusBarHeight = frame.top;
        int width = getWindowWidth(activity);
        int height = getWindowHeight(activity);

        Bitmap bp = null;
        bp = Bitmap.createBitmap(bitmap, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */

    public static int getWindowWidth(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(
                Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */

    public static int getWindowHeight(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(
                Context.WINDOW_SERVICE);

        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

}
