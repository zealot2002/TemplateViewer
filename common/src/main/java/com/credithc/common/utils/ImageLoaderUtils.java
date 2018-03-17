package com.credithc.common.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.credithc.common.R;
import com.credithc.common.base.BaseApplication;

/**
 * @author zzy
 * @date 2018/2/26
 */

public class ImageLoaderUtils {
    private ImageLoaderUtils() {}
    public static ImageLoaderUtils getInstance() {
        return ImageLoaderUtils.Holder.instance;
    }
    private static class Holder {
        private static final ImageLoaderUtils instance = new ImageLoaderUtils();
        private Holder() {}
    }

    public void showImg(Context context,final String url,final ImageView imageView){
        Glide.with(BaseApplication.getInstance())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .dontAnimate()
                .priority(Priority.NORMAL)
                .listener(new RequestListener() {
                    @Override
                    public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                        imageView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Object o = imageView.getTag(R.id.imageURI);
                                if(o==null){
                                    imageView.setTag(R.id.imageURI,1);
                                    showImg(BaseApplication.getInstance(),url,imageView);
                                }
                            }
                        },1000);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }
    public void showImg(Context context, final String url, final View view){
        SimpleTarget target = new SimpleTarget<Drawable>() {
            @SuppressLint("NewApi")
            @Override
            public void onResourceReady(Drawable resource, GlideAnimation<? super Drawable> glideAnimation) {
                view.setBackground(resource);
            }
        };
        Glide.with(context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .dontAnimate()
                .priority(Priority.NORMAL)
                .listener(new RequestListener() {
                    @Override
                    public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                        view.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Object o = view.getTag(R.id.imageURI);
                                if(o==null){
                                    view.setTag(R.id.imageURI,1);
                                    showImg(BaseApplication.getInstance(),url,view);
                                }
                            }
                        },1000);
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(target);
    }
}
