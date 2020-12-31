package com.jxkj.fxtc.execldemo;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.widget.Toast;

import com.jxkj.fxtc.app.ConstValues;
import com.jxkj.fxtc.entity.AddChangeList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtil {
    //内存地址
    public static String root = Environment.getExternalStorageDirectory()
            .getPath();

    public static void writeExcel(Context context, AddChangeList dataBean,
                                  String fileName) throws Exception {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && getAvailableStorage() > 1000000) {
            Toast.makeText(context, "SD卡不可用", Toast.LENGTH_LONG).show();
            return;
        }
        String title = "手套";
        String con = "度数";
        if(fileName.equals("1")){
            fileName = "智能手套_";
            title = "手套";
            con = "度数";
        }
        if(fileName.equals("2")){
            fileName = "呼吸袋_";
            title = "呼吸袋";
            con = "周期";
        }
        File file;
        File dir = new File(Environment.getExternalStorageDirectory()+ File.separator+ ConstValues.APPNAME_ENGLISH);
        file = new File(dir, fileName + ".xls");
        Log.w("dir","dir"+dir.getPath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 创建Excel工作表
        WritableWorkbook wwb;
        OutputStream os = new FileOutputStream(file);
        wwb = Workbook.createWorkbook(os);
        // 添加第一个工作表并设置第一个Sheet的名字
        WritableSheet sheet = wwb.createSheet("订单", 0);
        Label label = new Label(0, 0, title, getHeader());
        // 将定义好的单元格添加到工作表中
        sheet.addCell(label);


        for (int i = 0; i < dataBean.getChangeList().size(); i++) {

            if (i == 0) {
                Label time = new Label(0, 1, "时间");
                Label angle = new Label(1, 1, con);
                sheet.addCell(time);
                sheet.addCell(angle);
            }
            Label time = new Label(0, i + 2, dataBean.getChangeList().get(i).getChangeTime());
            Label angle = new Label(1, i + 2, dataBean.getChangeList().get(i).getValue());
            sheet.addCell(time);
            sheet.addCell(angle);
        }
        // 写入数据
        wwb.write();
        // 关闭文件
        wwb.close();
    }

    public static WritableCellFormat getHeader() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 10,
                WritableFont.BOLD);// 定义字体
        try {
            font.setColour(Colour.BLUE);// 蓝色字体
        } catch (WriteException e1) {
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            // format.setBorder(Border.ALL, BorderLineStyle.THIN,
            // Colour.BLACK);// 黑色边框
            // format.setBackground(Colour.YELLOW);// 黄色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    /**
     * 获取SD可用容量
     */
    private static long getAvailableStorage() {

        StatFs statFs = new StatFs(root);
        long blockSize = statFs.getBlockSize();
        long availableBlocks = statFs.getAvailableBlocks();
        long availableSize = blockSize * availableBlocks;
        // Formatter.formatFileSize(context, availableSize);
        return availableSize;
    }
}
