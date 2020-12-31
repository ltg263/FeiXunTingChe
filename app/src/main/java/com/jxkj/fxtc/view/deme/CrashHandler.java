package com.jxkj.fxtc.view.deme;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Created by LC125
 * @Description:
 * @Time:Created on 2020/11/11 16:51
 */
public class CrashHandler implements UncaughtExceptionHandler
{
    // region field

    // 系统默认的UncaughtExceptionHandler处理类
    private UncaughtExceptionHandler mDefaultHandler;
    // CrashProvider实例
    private static CrashHandler sInstance;
    // 程序的Context对象
    private Context mContext;
    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();
    // 用于格式化日期，作为日志文件名的一部分
    private DateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private String crashFolder=Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/ZsnaviDemo/";

    // endregion

    // region methods

    /**
     * @构造函数
     */
    private CrashHandler()
    {
    }

    /**
     * @单件模型方法
     * @return
     */
    public static CrashHandler getInstance()
    {
        if (sInstance == null)
        {
            sInstance = new CrashHandler();
        }
        return sInstance;
    }

    /**
     * @初始化方法
     * @param context
     * @应用程序的上下文
     */
    public void init(Context context)
    {
        mContext = context;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler位程序默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);

        File file = new File(crashFolder);

        if (!file.exists())
        {
            file.mkdirs();
        }
    }

    /**
     * @用户未处理异常处理函数
     */
    public void uncaughtException(Thread thread, Throwable ex)
    {
        if (!handleException(ex) && mDefaultHandler != null)
        {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else
        {
            try
            {
                Thread.sleep(3000);
            } catch (InterruptedException e)
            {
                Log.e("CrashHandler", "error : ", e);
            }
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * @自定义错误处理函数
     * @收集错误信息、发送错误报告等操作均在此完成
     * @param ex
     * @return true:如果处理了该异常信息；否则返回false
     */
    private boolean handleException(Throwable ex)
    {
        if (ex == null)
        {
            return false;
        }
        // 使用Toast来显示异常信息
        new Thread()
        {
            @Override
            public void run()
            {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉，程序出现异常，即将退出.", Toast.LENGTH_LONG)
                        .show();
                Looper.loop();
            }
        }.start();

        saveCrashInfoToFile(ex);


        return true;
    }

    /**
     * @保存错误信息到文件中
     * @param ex
     * @return 返回文件名称，便于将文件传送到服务器
     */
    private String saveCrashInfoToFile(Throwable ex)
    {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null)
        {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try
        {
            long timestamp = System.currentTimeMillis();
            String time = mFormatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED))
            {
                File dir = new File(crashFolder);
                if (!dir.exists())
                {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(crashFolder + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e)
        {
            Log.e("CrashHandler",
                    "an error occured while writing file...", e);
        }
        return null;
    }


    // endregion
}
