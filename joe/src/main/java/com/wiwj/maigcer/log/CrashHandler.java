package com.wiwj.maigcer.log;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;

import com.orhanobut.logger.Logger;
import com.wiwj.maigcer.common.BaseConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Joe liuzhaojava@foxmail.com 2018/3/11 15:26
 * @Description: 异常捕捉类
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + File.separator;
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;
    private Context mContext;
    private CrashListener mCrashListener;
    private File mCrashLogFile;

    private static class crashHandlerHolder {
        public static CrashHandler instance = new CrashHandler();
    }

    public static CrashHandler getInstance() {
        return CrashHandler.crashHandlerHolder.instance;
    }
    public void init(Context mContext,CrashListener mCrashListener) {
        //获取系统默认的异常处理器
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        //获取Context，方便内部使用
        this.mContext = mContext.getApplicationContext();
        this.mCrashListener = mCrashListener;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //导出异常信息到SD卡中
        dumpExceptionToSDCard(e);
        //这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
        uploadExceptionToServer();
        //打印出当前调用栈信息
        e.printStackTrace();

        //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
        if (!handleException(e) && mUncaughtExceptionHandler != null) {
            mUncaughtExceptionHandler.uncaughtException(t, e);
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }
    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param e
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable e) {
        if (e == null) {
            return false;
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                if (mCrashListener != null) {
                    mCrashListener.crashAction();
                }
                Looper.loop();
            }
        }.start();
        return true;
    }
    private void dumpExceptionToSDCard(Throwable ex) {
        //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Logger.w("sdcard unmounted,skip dump exception");
            return;
        }

        File dir = new File(PATH + BaseConfig.CACHE_LOG_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        mCrashLogFile = new File(PATH + BaseConfig.CACHE_LOG_DIR + FILE_NAME + time + FILE_NAME_SUFFIX);
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(mCrashLogFile)));
            //导出发生异常的时间
            printWriter.println(time);

            //导出手机信息
            dumpPhoneInfo(printWriter);

            printWriter.println();

            //导出异常的调用栈信息
            ex.printStackTrace(printWriter);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 打印手机信息
     *
     * @param printWriter
     */
    private void dumpPhoneInfo(PrintWriter printWriter) {
        try {
            //应用的版本名称和版本号
            PackageManager packageManager = mContext.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), PackageManager
                    .GET_ACTIVITIES);
            printWriter.print("App Version:");
            printWriter.print(packageInfo.versionName);
            printWriter.print('_');
            printWriter.println(packageInfo.versionCode);

            //android版本号
            printWriter.print("OS Version:");
            printWriter.print(Build.VERSION.RELEASE);
            printWriter.print('_');
            printWriter.println(Build.VERSION.SDK_INT);

            //手机制造商
            printWriter.print("Vendor:");
            printWriter.println(Build.MANUFACTURER);

            //手机型号
            printWriter.print("Model:");
            printWriter.println(Build.MODEL);

            //cpu架构
            printWriter.print("CPU ABI:");
            printWriter.println(Build.CPU_ABI);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 上传日志到服务器
     */
    private void uploadExceptionToServer() {
        if (mCrashListener != null && mCrashLogFile != null) {
            mCrashListener.uploadExceptionToServer(mCrashLogFile);
        }
    }
    public interface CrashListener {
        /*上传文件到服务器*/
        void uploadExceptionToServer(File file);

        /*出现异常下的处理*/
        void crashAction();
    }
}
