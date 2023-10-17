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

data class PbResp<T>(
    @SerializedName("common") var common: PbCommon? = null,
    @SerializedName("data") var data: T?)

data class PbCommon(
    @SerializedName("err_code") var errorCode: Int = -1,
    @SerializedName("msg") var msg: String = "",
)

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

data class BannerItem(
    @SerializedName("cover_url") var coverUrl: String = "",
    @SerializedName("data") var data: String = "",
    @SerializedName("duration") var duration: Int = 0,
    @SerializedName("id") var id: String = "",
    @SerializedName("image") var image: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("type") var type: String = "",
    @SerializedName("url") var url: String
)

data class LiveRecommendModel(
    @SerializedName("more") var more: Int,
    @SerializedName("ranker") var ranker: List<Ranker>,
    @SerializedName("rooms") var liveRecommendRoomInfos: List<LiveRecommendRoomInfo>
)

data class Ranker(
    @SerializedName("icon") var icon: String,
    @SerializedName("uid") var uid: String
)

data class LiveRecommendRoomInfo(
    @SerializedName("act_hot_num") var actHotNum: Int,
    @SerializedName("agora_token") var agoraToken: String,
    @SerializedName("area") var area: String,
    @SerializedName("areaCode") var areaCode: String,
    @SerializedName("boom_rocket_lv") var boomRocketLv: Int,
    @SerializedName("boss_icon") var bossIcon: String,
    @SerializedName("boss_uid") var bossUid: String,
    @SerializedName("dateline") var dateline: String,
    @SerializedName("deleted") var deleted: Int,
    @SerializedName("effect") var effect: String,
    @SerializedName("fixed_tag_id_new") var fixedTagIdNew: Int,
    @SerializedName("game_money_type") var gameMoneyType: Int,
    @SerializedName("game_status") var gameStatus: Int,
    @SerializedName("guestEnable") var guestEnable: Int,
    @SerializedName("hot") var hot: Int,
    @SerializedName("hot_num") var hotNum: Int,
    @SerializedName("icon") var icon: String,
    @SerializedName("is_biz") var isBiz: Int,
    @SerializedName("language") var language: String,
    @SerializedName("link_mic_status") var linkMicStatus: Int,
    @SerializedName("name") var name: String,
    @SerializedName("num_boy") var numBoy: Int,
    @SerializedName("num_girl") var numGirl: Int,
    @SerializedName("online_num") var onlineNum: Int,
    @SerializedName("password") var password: Int,
    @SerializedName("pattern_style") var patternStyle: Int,
    @SerializedName("pay_room_money") var payRoomMoney: Int,
    @SerializedName("pk_state") var pkState: Int,
    @SerializedName("prefix") var prefix: String,
    @SerializedName("property") var property: String,
    @SerializedName("real") var real: Int,
    @SerializedName("reception_uid") var receptionUid: String,
    @SerializedName("rid") var rid: String,
    @SerializedName("sex") var sex: Int,
    @SerializedName("show_red_packet") var showRedPacket: Boolean,
    @SerializedName("tags") var tags: String,
    @SerializedName("team_pk_state") var teamPkState: Int,
    @SerializedName("type") var type: String,
    @SerializedName("typeName") var typeName: TypeName,
    @SerializedName("types") var types: String,
    @SerializedName("uid") var uid: Int,
    @SerializedName("uname") var uname: String,
    @SerializedName("user_icon") var userIcon: String,
    @SerializedName("users") var users: List<User>,
    @SerializedName("utitle") var uTitle: Int,
    @SerializedName("vip") var vip: Int
)

data class TypeName(
    @SerializedName("abstract_icon") var abstractIcon: String,
    @SerializedName("acute_icon") var acuteIcon: String,
    @SerializedName("bg") var bg: List<String>,
    @SerializedName("big_icon") var bigIcon: String,
    @SerializedName("color") var color: String,
    @SerializedName("icon") var icon: String,
    @SerializedName("label") var label: String,
    @SerializedName("main_bg") var mainBg: List<String>,
    @SerializedName("party_style") var partyStyle: Int,
    @SerializedName("room_type") var roomType: RoomType,
    @SerializedName("show") var show: String,
    @SerializedName("small_icon") var smallIcon: String,
    @SerializedName("tag_color") var tagColor: String,
    @SerializedName("tag_icon") var tagIcon: String,
    @SerializedName("taginfo_type") var taginfoType: String,
    @SerializedName("type_bg") var typeBg: List<String>,
    @SerializedName("type_icon") var typeIcon: String
)

data class User(
    @SerializedName("icon") var icon: String,
    @SerializedName("raw_icon") var rawIcon: String,
    @SerializedName("uid") var uid: Int
)

data class RoomType(
    @SerializedName("acute_icon") var acuteIcon: String,
    @SerializedName("app_id") var appId: List<Int>,
    @SerializedName("bg") var bg: List<String>,
    @SerializedName("label") var label: String,
    @SerializedName("room_type") var roomType: String,
    @SerializedName("show") var show: String,
    @SerializedName("string_key") var stringKey: String
)