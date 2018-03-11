package com.wiwj.maigcer.glide;

import android.content.Context;
import android.widget.ImageView;

import com.wiwj.maigcer.R;

import java.io.File;

/**
 * @Author: Joe liuzhaojava@foxmail.com
 * @Date: 2018/3/10 0:39
 * @Description: 图片加载接口, 如有需要可拓展接口
 */
public interface ILoader {
    //加载网络图片
    void loadNet(ImageView target, String url, Options options);

    //加载gif图
    void loadGifImage(ImageView target, String url, Options options);

    //加载图片
    void loadRoundCornersImage(ImageView target, String url, Options options);

    //加载图片为圆角，比如头像
    void loadCircleImage(ImageView target, String url, Options options,int cornerSize);

    //加载本地资源
    void loadResource(ImageView target, int resId, Options options);

    //加载Assets资源
    void loadAssets(ImageView target, String assetName, Options options);

    //下载文件
    void loadFile(ImageView target, File file, Options options);

    //获取图片缓存大小
    String getCacheSize(Context context);

    //清除内存缓存
    void clearMemoryCache(Context context);

    //清除磁盘缓存
    void clearDiskCache(Context context);


    class Options {

        public static final int RES_NONE = -1;
        public static int loadingResId = R.drawable.ic_launcher;//加载中的资源id
        public static int loadErrorResId = R.drawable.ic_launcher;//加载失败的资源id

        public static Options defaultOptions() {
            return new Options(loadingResId, loadErrorResId);
        }

        public Options(int loadingResId, int loadErrorResId) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }
    }
}
