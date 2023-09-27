package sg.partying.lcb.android.util

import android.provider.Settings
import com.salton123.app.BaseApplication

/**
 * Time:2023/9/27 14:42
 * Author:
 * Description:
 */
object Utils {

    fun getMac(): String {
        var mac = Settings.Secure.getString(BaseApplication.sInstance.contentResolver, Settings.Secure.ANDROID_ID)
        if (mac == null) {
            mac = ""
        }
        return mac
    }
}