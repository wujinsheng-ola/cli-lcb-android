package com.salton123.soulove

import com.salton123.config.AppModeEnum
import com.salton123.utils.PreferencesUtils
import com.squareup.wire.get


/**
 * Time:2021/6/24 18:03
 * Author:wujinsheng1
 * Description:
 */
object CommonClassPath {
    const val BUGLY_APP_ID: String = ""

    @AppModeEnum.AppMode
    var appMode: Int = AppModeEnum.Product
        get() {
            return PreferencesUtils.getInt("appMode", AppModeEnum.Product)
        }
        set(value) {
            PreferencesUtils.putInt("appMode", value)
            field = value
        }

//    val isDebugAppMode: Boolean get() = appMode == AppModeEnum.Debug
    val isDebugAppMode: Boolean get() = true
}