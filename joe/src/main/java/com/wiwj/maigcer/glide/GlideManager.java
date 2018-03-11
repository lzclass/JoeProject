package com.wiwj.maigcer.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.GifTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.wiwj.maigcer.common.BaseConfig;
import com.wiwj.maigcer.utils.FileUtil;

import java.io.File;

/**
 * @Author: Joe liuzhaojava@foxmail.com
 * @Date: 2018/3/10 0:46
 * @Description: 使用Glide框架加载图片
 */
public class GlideManager implements ILoader {

    private static class GlideLoaderHolder {
        public static GlideManager instance = new GlideManager();
    }

    public static GlideManager getInstance() {
        return GlideManager.GlideLoaderHolder.instance;
    }

    @Override
    public void loadNet(ImageView target, String url, Options options) {
        load(getRequestManager(target.getContext()).load(url), target, options);
    }

    @Override
    public void loadResource(ImageView target, int resId, Options options) {
        load(getRequestManager(target.getContext()).load(resId), target, options);
    }

    @Override
    public void loadAssets(ImageView target, String assetName, Options options) {
        load(getRequestManager(target.getContext()).load("file:///android_asset/" + assetName), target, options);
    }

    @Override
    public void loadFile(ImageView target, File file, Options options) {
        load(getRequestManager(target.getContext()).load(file), target, options);
    }

    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
            }
        }).start();
    }

    @Override
    public void loadGifImage(ImageView target, String url, Options options) {
        load(getRequestManager(target.getContext()).load(url).asGif(), target, options);
    }

    @Override
    public void loadRoundCornersImage(final ImageView target, String url, Options options) {
        getRequestManager(target.getContext())
                .load(url)
                .asBitmap()
                .into(new BitmapImageViewTarget(target) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(target.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        target.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    @Override
    public void loadCircleImage(final ImageView target, String url, Options options, int cornerSize) {
        getRequestManager(target.getContext()).load(url)
                .transform(new GlideRoundTransform(target.getContext(), cornerSize))
                .into(target);
    }

    @Override
    public String getCacheSize(Context context) {
        try {
            return FileUtil.getFormatSize(FileUtil.getFolderSize(new File(FileUtil.getCacheDir(context) + "/" + BaseConfig.CACHE_DISK_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
            return "获取失败";
        }
    }

    private RequestManager getRequestManager(Context context) {
        return Glide.with(context);
    }

    private void load(GifTypeRequest request, ImageView target, Options options) {
        if (options == null) options = Options.defaultOptions();

        if (options.loadingResId != Options.RES_NONE) {
            request.placeholder(options.loadingResId);
        }
        if (options.loadErrorResId != Options.RES_NONE) {
            request.error(options.loadErrorResId);
        }
        request.crossFade().into(target);
    }

    private void load(DrawableTypeRequest request, ImageView target, Options options) {
        if (options == null) options = Options.defaultOptions();

        if (options.loadingResId != Options.RES_NONE) {
            request.placeholder(options.loadingResId);
        }
        if (options.loadErrorResId != Options.RES_NONE) {
            request.error(options.loadErrorResId);
        }
        request.crossFade().into(target);
    }
}
