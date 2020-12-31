package com.jxkj.fxtc.conpoment.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author WangZH
 * Created by WangZH on 2016/12/22.
 *
 * 使用方法：Application级别的类中
 * CrashCat.getInstance(ApplicationContext, PathDirectory,PathName).start();
 * e.g.
 * CrashCat.getInstance(getApplicationContext(), Environment.getExternalStorageDirectory(),"/Log.txt").start();
 *
 */

public class CrashCat implements Thread.UncaughtExceptionHandler {

    private static CrashCat crashCat;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private static String DEVICE_INFO="";
    private File path;
    private File fileName;
    private FileOutputStream fileOutputStream;
    private BufferedOutputStream bufferedOutputStream;
    private static String FILE_NAME = "";
    private Intent intent;

    private CrashCat(Context context, String filePath, String fileName){
        init(context,filePath,fileName);
    }

    public static CrashCat getInstance(Context context, String filePath, String fileName){
        crashCat = new CrashCat(context,filePath,fileName);
        return  crashCat;
    }

    private void init(Context context, String filePath, String fileName){
        this.mContext = context;
       // this.FILE_NAME = fileName;
        try {
            intent = new Intent(mContext
                    , Class.forName(mContext.getPackageManager()
                    .getLaunchIntentForPackage(mContext.getPackageName())
                    .getComponent().getClassName()));
        } catch (ClassNotFoundException e) {
            //writeLog(e.toString());
        }
        /*path = new File(filePath);
        if (!path.exists()){
            path.mkdirs();
        }
        StringBuffer sb = new StringBuffer();
        sb.append("DeviceID="+ Build.ID+"\n");
        sb.append("AndroidApi="+Build.VERSION.SDK_INT+"\n");
        sb.append("AndroidVersion="+Build.VERSION.RELEASE+"\n");
        sb.append("Brand="+Build.BRAND+"\n");
        sb.append("ManuFacture="+Build.MANUFACTURER+"\n");
        sb.append("Model="+Build.MODEL+"\n");
        sb.append("PackageName="+mContext.getPackageName()+"\n");
        DEVICE_INFO = sb.toString();
        writeLog("Application Start");*/
    }

    public void start(){
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private void writeLog(String log){
       /* SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log = "----------"+simpleDateFormat.format(new Date(System.currentTimeMillis())).toString()+"----------"+"\n"+log+"\n";
        try {
            fileName = new File(path+FILE_NAME);
            if (fileName.exists() && fileName.length() > 10485760){
                fileName.delete();
            }
            fileOutputStream = new FileOutputStream(fileName,true);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(log.getBytes());
            bufferedOutputStream.flush();
            fileOutputStream.close();
            bufferedOutputStream.close();
        } catch (Exception e) {
            Log.e("IO Exception",e.toString());
        }*/
    }

    private void handlerException(String exception) {
        if (exception !=null){
            try{
                //writeLog(DEVICE_INFO+exception.toString());
            }finally {
                mContext.startActivity(intent);
                Process.killProcess(Process.myPid());
                System.exit(1);
            }

        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        StringBuffer sb = new StringBuffer(e.toString()+"\n");
        for (int i=0,size = stackTraceElements.length;i<size;i++){
            sb.append(stackTraceElements[i].toString()+"\n");
        }
        Log.e("error",sb.toString());
        handlerException(sb.toString());
    }
}
