package com.wiwj.maigcer.collectlog.capture;

import android.content.Context;
import android.util.Log;
import com.wiwj.maigcer.collectlog.utils.LogCollectorUtility;
import com.wiwj.maigcer.collectlog.utils.LogHelper;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 类描述：
 *
 * @author Ruifeng_Pan
 * @date 2016-12-5
 */
public class LogFileStorage {

    private static final String TAG = LogFileStorage.class.getName();

    public static final String LOG_SUFFIX = ".txt";

    private static final String CHARSET = "UTF-8";

    private static LogFileStorage sInstance;

    private Context mContext;

    private LogFileStorage(Context ctx) {
        mContext = ctx.getApplicationContext();
    }

    public static synchronized LogFileStorage getInstance(Context ctx) {
        if (ctx == null) {
            LogHelper.e(TAG, "Context is null");
            return null;
        }
        if (sInstance == null) {
            sInstance = new LogFileStorage(ctx);
        }
        return sInstance;
    }

    public File getUploadLogFile() {
        File dir = mContext.getFilesDir();
        File logFile = new File(dir, LogCollectorUtility.getMid(mContext)
                + LOG_SUFFIX);
        if (logFile.exists()) {
            return logFile;
        } else {
            return null;
        }
    }

    public File getUploadLogFileSD() {
        File logDir = getExternalLogDir();
        File logFile = new File(logDir, LogCollectorUtility.getMid(mContext)
                + LOG_SUFFIX);
        if (logFile.exists()) {
            return logFile;
        } else {
            return null;
        }
    }

    public boolean deleteUploadLogFile() {
        File dir = mContext.getFilesDir();
        File logFile = new File(dir, LogCollectorUtility.getMid(mContext)
                + LOG_SUFFIX);
        return logFile.delete();
    }

    public boolean deleteUploadLogFileSD() {
        File logDir = getExternalLogDir();
        File logFile = new File(logDir, LogCollectorUtility.getMid(mContext)
                + LOG_SUFFIX);
        return logFile.delete();
    }

    public boolean saveLogFile2Internal(String logString) {
        try {
            File dir = mContext.getFilesDir();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File logFile = new File(dir, LogCollectorUtility.getMid(mContext)
                    + LOG_SUFFIX);
            FileOutputStream fos = new FileOutputStream(logFile, true);
            fos.write(logString.getBytes(CHARSET));
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            LogHelper.e(TAG, "saveLogFile2Internal failed!");
            return false;
        }
        return true;
    }

    public boolean saveLogFile2SDcard(String logString, boolean isAppend) {
        if (!LogCollectorUtility.isSDcardExsit()) {
            LogHelper.e(TAG, "sdcard not exist");
            return false;
        }
        File logFile = null;
        try {
            File logDir = getExternalLogDir();
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            logFile = new File(logDir, LogCollectorUtility.getMid(mContext)
                    + LOG_SUFFIX);
            /*if (!isAppend) {
				if (logFile.exists() && !logFile.isFile())
					logFile.delete();
			}*/
            LogHelper.d(TAG, logFile.getPath());

            FileOutputStream fos = new FileOutputStream(logFile, isAppend);
            fos.write(logString.getBytes(CHARSET));
            fos.close();
        } catch (Exception e) {
            if (logFile != null && logFile.exists() && logFile.isFile())
                logFile.delete();
            e.printStackTrace();
            Log.e(TAG, "saveLogFile2SDcard failed!");
            return false;
        }
        return true;
    }

    private File getExternalLogDir() {
        File logDir = LogCollectorUtility.getExternalDir(mContext, "Log");
        LogHelper.d(TAG, logDir.getPath());
        return logDir;
    }
}
