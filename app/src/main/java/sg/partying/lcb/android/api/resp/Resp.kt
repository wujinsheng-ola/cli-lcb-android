package sg.partying.lcb.android.api.resp

import com.google.gson.annotations.SerializedName

/**
 * Time:2022/12/14 16:47
 * Author:
 * Description:
 */
data class Resp<T>(
    @SerializedName("success") var success: Boolean = false,
    @SerializedName("msg") var msg: String = "",
    @SerializedName("data") var data: T)

data class LoginOption(
    @SerializedName("main") var main: List<String> = mutableListOf(),
    @SerializedName("sub") var sub: List<String> = mutableListOf(),
)

data class LoginRet(
    @SerializedName("age") var age: Int = 0,
    @SerializedName("app_id") var app: Int = 0,
    @SerializedName("birthday") var birthday: String = "",
    @SerializedName("cash_min") var cashMin: Int = 0,
    @SerializedName("cash_rate") var cashRate: Int = 0,
    @SerializedName("city") var city: String = "",
    @SerializedName("city_code") var cityCode: Int = 0,
    @SerializedName("dateline") var dateline: Int = 0,
    @SerializedName("deleted") var deleted: Int = 0,
    @SerializedName("dtoken") var dtoken: String = "",
    @SerializedName("friend") var friend: Int = 0,
    @SerializedName("game_login_token") var gameLoginToken: String = "",
    @SerializedName("god_num") var godNum: Int = 0,
    @SerializedName("icon") var icon: String = "",
    @SerializedName("job") var job: Int = 0,
    @SerializedName("latitude") var latitude: Int = 0,
    @SerializedName("longitude") var longitude: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("online_dateline") var onlineDateline: Int = 0,
    @SerializedName("pay_money") var payMoney: Int = 0,
    @SerializedName("pay_num") var payNum: Int = 0,
    @SerializedName("position") var position: String = "",
    @SerializedName("role") var role: Int = 0,
    @SerializedName("server_time") var serverTime: Int = 0,
    @SerializedName("sex") var sex: Int = 0,
    @SerializedName("sign") var sign: String = "",
    @SerializedName("star") var star: Int = 0,
    @SerializedName("title") var title: Int = 0,
    @SerializedName("title_new") var titleNew: Int = 0,
    @SerializedName("tmp_icon") var tmpIcon: String = "",
    @SerializedName("token") var token: String = "",
    @SerializedName("uid") var uid: Int = 0,
    @SerializedName("version") var version: Int = 0,
    @SerializedName("vip") var vip: Int = 0,
    @SerializedName("vip_new") var vipNew: Int = 0
)