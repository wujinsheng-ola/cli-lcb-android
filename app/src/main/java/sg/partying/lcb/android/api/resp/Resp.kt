package sg.partying.lcb.android.api.resp

import com.google.gson.annotations.SerializedName

/**
 * Time:2022/12/14 16:47
 * Author:
 * Description:
 */
data class Resp<T>(@SerializedName("success") var success: Boolean = false, @SerializedName("msg") var msg: String = "", @SerializedName("data") var data: T)

data class LoginOption(
    @SerializedName("main") var main: List<String> = mutableListOf(),
    @SerializedName("sub") var sub: List<String> = mutableListOf(),
)