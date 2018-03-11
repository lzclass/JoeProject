package com.wiwj.maigcer.view.anim;

import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;


/**
 * @author ljy
 * @Description 帧动画控制类
 * @datetime 2017/9/7
 */
public class FrameAnimation {

    private ImageView mIvAnimation;
    private AnimationDrawable mAnimationDrawable;

    public FrameAnimation(ImageView ivLoading) {
        this.mIvAnimation = ivLoading;
    }

    /**
     * 显示动画
     */
    public void showAnim() {
        if (mIvAnimation != null && (mAnimationDrawable = (AnimationDrawable) mIvAnimation.getBackground()) != null) {
            mAnimationDrawable.start();
        }
    }


    /**
     * 关闭动画
     */
    public void closeAnim() {
        if (mIvAnimation != null && mAnimationDrawable != null) mAnimationDrawable.stop();
    }


}