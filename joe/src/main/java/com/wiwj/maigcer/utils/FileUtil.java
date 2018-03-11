package com.wiwj.maigcer.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.Logger;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * author：   ljy
 * date：     2017/10/1
 * description 文件/文件夹工具类
 */
public class FileUtil {

    private static final String TAG = FileUtil.class.getSimpleName();

    /**
     * SD卡是否能用
     *
     * @return true 可用,false不可用
     */
    public static boolean isSDCardAvailable() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            Logger.e(TAG, "isSDCardAvailable : SD卡不可用!", e);
            return false;
        }
    }

    /**
     * 创建一个文件夹, 存在则返回, 不存在则新建
     *
     * @param parentDirectory 父目录路径
     * @param directory       目录名
     * @return 文件，null代表失败
     */
    public static File generateDirectory(String parentDirectory, String directory) {
        if (TextUtils.isEmpty(parentDirectory) || TextUtils.isEmpty(directory)) {
            return null;
        }
        File file = new File(parentDirectory, directory);
        boolean flag;
        if (!file.exists()) {
            flag = file.mkdir();
        } else {
            flag = true;
        }
        return flag ? file : null;
    }

    /**
     * 创建一个文件夹, 存在则返回, 不存在则新建
     *
     * @param parentDirectory 父目录
     * @param directory       目录名
     * @return 文件，null代表失败
     */
    public static File generateDirectory(File parentDirectory, String directory) {
        if (parentDirectory == null || TextUtils.isEmpty(directory)) {
            return null;
        }
        File file = new File(parentDirectory, directory);
        boolean flag;
        if (!file.exists()) {
            flag = file.mkdir();
        } else {
            flag = true;
        }
        return flag ? file : null;
    }


    /**
     * 创建一个文件, 存在则返回, 不存在则新建
     *
     * @param catalogPath 父目录路径
     * @param name        文件名
     * @return 文件，null代表失败
     */
    public static File generateFile(String catalogPath, String name) {
        if (TextUtils.isEmpty(catalogPath) || TextUtils.isEmpty(name)) {
            Log.e(TAG, "generateFile : 创建失败, 文件目录或文件名为空, 请检查!");
            return null;
        }
        boolean flag;
        File file = new File(catalogPath, name);
        if (!file.exists()) {
            try {
                flag = file.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "generateFile : 创建" + catalogPath + "目录下的文件" + name + "文件失败!", e);
                flag = false;
            }
        } else {
            flag = true;
        }
        return flag ? file : null;
    }

    /**
     * 创建一个文件, 存在则返回, 不存在则新建
     *
     * @param catalog 父目录
     * @param name    文件名
     * @return 文件，null代表失败
     */
    public static File generateFile(File catalog, String name) {
        if (catalog == null || TextUtils.isEmpty(name)) {
            Log.e(TAG, "generateFile : 创建失败, 文件目录或文件名为空, 请检查!");
            return null;
        }
        boolean flag;
        File file = new File(catalog, name);
        if (!file.exists()) {
            try {
                flag = file.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "generateFile : 创建" + catalog + "目录下的文件" + name + "文件失败!", e);
                flag = false;
            }
        } else {
            flag = true;
        }
        return flag ? file : null;
    }

    /**
     * 根据全路径创建一个文件
     *
     * @param filePath 文件全路径
     * @return 文件，null代表失败
     */
    public static File generateFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            Log.e(TAG, "generateFile : 创建失败, 文件目录或文件名为空, 请检查!");
            return null;
        }
        boolean flag;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                flag = file.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "generateFile : 创建" + file.getName() + "文件失败!", e);
                flag = false;
            }
        } else {
            flag = true;
        }
        return flag ? file : null;
    }

    /***
     * 获取指定文件夹内所有文件大小的和
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除文件/文件夹
     * 如果是文件夹，则会删除其下的文件以及它本身
     *
     * @param file file
     * @return true代表成功删除
     */
    public static boolean deleteFile(File file) {
        if (file == null) {
            return true;
        }
        if (!file.exists()) {
            return true;
        }
        boolean result = true;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File subFile : files) {
                    if (subFile.isDirectory()) {
                        if (!deleteFile(subFile)) {
                            result = false;
                        }
                    } else {
                        if (!subFile.delete()) {
                            result = false;
                        }
                    }
                }
            }
        }
        if (!file.delete()) {
            result = false;
        }

        return result;
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //返回"/data"目录
    public static String getDataDirectory() {
        return Environment.getDataDirectory().getAbsolutePath();
    }

    //返回"/storage/emulated/0"目录
    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    //返回"/system"目录
    public static String getRootDirectory() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    //返回"/cache"目录
    public static String getDownloadCacheDirectory() {
        return Environment.getDownloadCacheDirectory().getAbsolutePath();
    }

    /**
     * @param type 所放的文件的类型，传入的参数是Environment类中的DIRECTORY_XXX静态变量
     * @return 返回"/storage/emulated/0/xxx"目录
     * 例如传入Environment.DIRECTORY_ALARMS则返回"/storage/emulated/0/Alarms"
     */
    public static String getExternalStoragePublicDirectory(String type) {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS);
        //返回的目录有可能不存在
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    //返回"/data/user/0/com.xxx.xxx/cache"目录
    public static String getCacheDir(Context mContext) {
        return mContext.getCacheDir().getAbsolutePath();
    }

    //返回"/data/user/0/com.xxx.xxx/files"目录
    public static String getFilesDir(Context mContext) {
        return mContext.getFilesDir().getAbsolutePath();
    }

    //返回"/storage/emulated/0/Android/data/com.xxx.xxx/cache"目录
    public static String getExternalCacheDir(Context mContext) {
        return mContext.getExternalCacheDir().getAbsolutePath();
    }

    /**
     * @param type 所放的文件的类型，传入的参数是Environment类中的DIRECTORY_XXX静态变量
     * @return 返回"/storage/emulated/0/Android/data/com.xxx.xxx/files/Alarms"目录
     * 例如传入Environment.DIRECTORY_ALARMS则返回"/storage/emulated/0/Android/data/com.xxx.xxx/files/Alarms"
     */
    public static String getExternalFilesDir(Context mContext,String type) {
        File file = mContext.getExternalFilesDir(Environment.DIRECTORY_ALARMS);
        //返回的目录有可能不存在
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }
    /***
     *  格式化文件大小的单位
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

}
