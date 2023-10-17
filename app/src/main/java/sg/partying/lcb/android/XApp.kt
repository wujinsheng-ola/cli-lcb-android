package sg.partying.lcb.android

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.Utils
import com.bumptech.glide.Glide
import com.salton123.app.BaseApplication
import sg.partying.lcb.android.config.AppConfig
import sg.partying.lcb.android.config.ConfigProvider

/**
 * User: newSalton@outlook.com
 * Date: 2019/4/24 16:11
 * ModifyTime: 16:11
 * Description:
 */
class XApp : BaseApplication() {
    companion object {

        init {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppUtils.registerAppStatusChangedListener(
            object : Utils.OnAppStatusChangedListener {
                override fun onForeground(activity: Activity?) {
                }

                override fun onBackground(activity: Activity?) {
                }
            })
        val configProvider = ConfigProvider()
        AppConfig.init(configProvider)
    }


    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).onTrimMemory(level)
    }
}