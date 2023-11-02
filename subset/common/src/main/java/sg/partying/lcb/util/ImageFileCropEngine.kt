package sg.partying.lcb.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.luck.picture.lib.engine.CropFileEngine
import com.luck.picture.lib.utils.ActivityCompatHelper
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropImageEngine

/**
 * Time:2023/10/31 18:22
 * Author:
 * Description:
 */
class ImageFileCropEngine(private var isAvatarOption: Boolean = false) : CropFileEngine {
    override fun onStartCrop(fragment: Fragment, srcUri: Uri, destinationUri: Uri, dataSource: ArrayList<String>, requestCode: Int) {
        val options = buildOptions(isAvatarOption)
        val uCrop = UCrop.of(srcUri, destinationUri, dataSource)
        uCrop.withOptions(options)
        uCrop.setImageEngine(object : UCropImageEngine {
            override fun loadImage(context: Context, url: String, imageView: ImageView) {
                if (!assertValidRequest(context)) {
                    return
                }
                Glide.with(context).load(url).override(180, 180).into(imageView)
            }

            override fun loadImage(context: Context, url: Uri, maxWidth: Int, maxHeight: Int, call: UCropImageEngine.OnCallbackListener<Bitmap>) {
                Glide.with(context).asBitmap().load(url).override(maxWidth, maxHeight).into(object : CustomTarget<Bitmap?>() {

                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                        call.onCall(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        call.onCall(null)
                    }
                })
            }
        })
        uCrop.start(fragment.requireActivity(), fragment, requestCode)
    }

    private fun buildOptions(isAvatarOption: Boolean = false): UCrop.Options {
        val options = UCrop.Options()
        options.setHideBottomControls(false)
        //        options.setFreeStyleCropEnabled(true);
        options.setShowCropFrame(!isAvatarOption)
        options.setShowCropGrid(!isAvatarOption)
        options.setCircleDimmedLayer(isAvatarOption)
        if (isAvatarOption) {
            options.withAspectRatio(1f, 1f)
        }
        //        options.
////        options.setCropOutputPathDir(getSandboxPath());
//        options.isCropDragSmoothToCenter(false);
////        options.setSkipCropMimeType();
//        options.isForbidCropGifWebp(true);
//        options.isForbidSkipMultipleCrop(true);
//        options.setMaxScaleMultiplier(100);
//        if (selectorStyle != null && selectorStyle.getSelectMainStyle().getStatusBarColor() != 0) {
//            SelectMainStyle mainStyle = selectorStyle.getSelectMainStyle();
//            boolean isDarkStatusBarBlack = mainStyle.isDarkStatusBarBlack();
//            int statusBarColor = mainStyle.getStatusBarColor();
//            options.isDarkStatusBarBlack(isDarkStatusBarBlack);
//            if (StyleUtils.checkStyleValidity(statusBarColor)) {
//                options.setStatusBarColor(statusBarColor);
//                options.setToolbarColor(statusBarColor);
//            } else {
//                options.setStatusBarColor(ContextCompat.getColor(getContext(), R.color.ps_color_grey));
//                options.setToolbarColor(ContextCompat.getColor(getContext(), R.color.ps_color_grey));
//            }
//            TitleBarStyle titleBarStyle = selectorStyle.getTitleBarStyle();
//            if (StyleUtils.checkStyleValidity(titleBarStyle.getTitleTextColor())) {
//                options.setToolbarWidgetColor(titleBarStyle.getTitleTextColor());
//            } else {
//                options.setToolbarWidgetColor(ContextCompat.getColor(getContext(), R.color.ps_color_white));
//            }
//        } else {
//            options.setStatusBarColor(ContextCompat.getColor(getContext(), R.color.ps_color_grey));
//            options.setToolbarColor(ContextCompat.getColor(getContext(), R.color.ps_color_grey));
//            options.setToolbarWidgetColor(ContextCompat.getColor(getContext(), R.color.ps_color_white));
//        }
        return options
    }

    companion object {
        fun assertValidRequest(context: Context?): Boolean {
            if (context is Activity) {
                return !ActivityCompatHelper.isDestroy(context)
            } else if (context is ContextWrapper) {
                if (context.baseContext is Activity) {
                    val activity = context.baseContext as Activity
                    return !ActivityCompatHelper.isDestroy(activity)
                }
            }
            return true
        }
    }
}