package sg.partying.lcb.android

import com.salton123.utils.PreferencesUtils

/**
 * Time:2023/9/26 10:37
 * Author:
 * Description:
 */
object Session {
    var uid: Int = 0
        get() {
            return PreferencesUtils.getInt("uid", 0)
        }
        set(value) {
            PreferencesUtils.putInt("uid", value)
            field = value
        }

    var token: String = ""
        get() {
            return PreferencesUtils.getString("token", "")
        }
        set(value) {
            PreferencesUtils.putString("token", value)
            field = value
        }

    var icon: String = ""
        get() {
            return PreferencesUtils.getString("icon", "")
        }
        set(value) {
            PreferencesUtils.putString("icon", value)
            field = value
        }


    var latitude: Int = 0
        get() {
            return PreferencesUtils.getInt("latitude", 0)
        }
        set(value) {
            PreferencesUtils.putInt("latitude", value)
            field = value
        }

    var longitude: Int = 0
        get() {
            return PreferencesUtils.getInt("longitude", 0)
        }
        set(value) {
            PreferencesUtils.putInt("longitude", value)
            field = value
        }
    var name: String = ""
        get() {
            return PreferencesUtils.getString("name", "")
        }
        set(value) {
            PreferencesUtils.putString("name", value)
            field = value
        }

    var isLogined: Boolean = false
        get() {
            return uid > 0 && token.isNotEmpty()
        }

    var area: String = "86"
        get() {
            return PreferencesUtils.getString("area", "86")
        }
        set(value) {
            PreferencesUtils.getString("area", value)
            field = value
        }

}