package sg.partying.lcb.android.util

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.salton123.common.BuildConfig
import com.salton123.log.XLog.d
import sg.partying.lcb.android.R

/**
 * User: newSalton@outlook.com
 * Date: 2019-08-04 12:46
 * ModifyTime: 12:46
 * Description:
 */
object ImageLoader {
    fun loadBitmap(context: Context?, url: String?, onLoadBitmapCallback: OnLoadBitmapCallback?) {
        Glide.with(context!!)
            .asBitmap().load(url)
            .centerInside()
            .into(object : SimpleTarget<Bitmap?>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                    onLoadBitmapCallback?.onBitmapCallBack(resource)
                }
            })
    }

    fun loadCenterInsideRoundedCorners(imageView: ImageView?, url: String, sizeMultiplier: Float, roundingRadius: Int) {
        if (BuildConfig.DEBUG) {
            d(ImageLoader::class.java, "[loadCenterInside] sizeMultiplier:" + sizeMultiplier
                + ",roundingRadius:" + roundingRadius + ",url:" + url)
        }
        Glide.with(imageView!!)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_placeholder)
            .fitCenter()
            .thumbnail(0.5f)
            .into(imageView)
    }

    fun loadCenterCrop(imageView: ImageView, url: String) {
        if (BuildConfig.DEBUG) {
            d(ImageLoader::class.java, "[loadCenterCrop],url:$url")
        }
        Glide.with(imageView)
            .load(url)
            .centerCrop()
            .thumbnail(0.3f)
            .into(imageView)
    }

    fun loadFitCenter(imageView: ImageView, url: String) {
        if (BuildConfig.DEBUG) {
//            XLog.d(ImageLoader.class, "[loadFitCenter]" + ",url:" + url);
        }
        Glide.with(imageView)
            .load(url)
            .centerCrop()
            .thumbnail(0.3f)
            .into(imageView)
    }

    interface OnLoadBitmapCallback {
        fun onBitmapCallBack(bitmap: Bitmap?)
    }
}