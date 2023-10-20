package sg.partying.lcb.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.salton123.log.XLog;

import sg.partying.lcb.android.BuildConfig;
import sg.partying.lcb.android.R;

/**
 * User: newSalton@outlook.com
 * Date: 2019-08-04 12:46
 * ModifyTime: 12:46
 * Description:
 */
public class ImageLoader {

    public static void loadBitmap(Context context, String url, final OnLoadBitmapCallback onLoadBitmapCallback) {
        Glide.with(context)
                .asBitmap().load(url)
                .centerInside()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if (onLoadBitmapCallback != null) {
                            onLoadBitmapCallback.onBitmapCallBack(resource);
                        }
                    }
                });
    }

    public interface OnLoadBitmapCallback {
        void onBitmapCallBack(Bitmap bitmap);
    }

    public static void loadCenterInsideRoundedCorners(ImageView imageView, String url, float sizeMultiplier, int roundingRadius) {
        if (BuildConfig.DEBUG) {
            XLog.d(ImageLoader.class, "[loadCenterInside] sizeMultiplier:" + sizeMultiplier
                    + ",roundingRadius:" + roundingRadius + ",url:" + url);
        }
        Glide.with(imageView)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_placeholder)
                .fitCenter()
                .thumbnail(0.5f)
                .into(imageView);
    }

    public static void loadCenterCrop(ImageView imageView, String url) {
        if (BuildConfig.DEBUG) {
            XLog.d(ImageLoader.class, "[loadCenterCrop]" + ",url:" + url);
        }
        Glide.with(imageView)
                .load(url)
                .centerCrop()
                .thumbnail(0.5f)
                .into(imageView);
    }

    public static void loadFitCenter(ImageView imageView, String url) {
        if (BuildConfig.DEBUG) {
//            XLog.d(ImageLoader.class, "[loadFitCenter]" + ",url:" + url);
        }
        Glide.with(imageView)
            .load(url)
            .fitCenter()
            .thumbnail(0.5f)
            .into(imageView);
    }
}
