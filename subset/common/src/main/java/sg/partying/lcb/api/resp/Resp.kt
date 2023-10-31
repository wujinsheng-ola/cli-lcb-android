package sg.partying.lcb.api.resp

import com.google.gson.annotations.SerializedName

/**
 * Time:2022/12/14 16:47
 * Author:
 * Description:
 */
data class Resp<T>(
    @SerializedName("success") var success: Boolean = false,
    @SerializedName("msg") var msg: String = "",
    @SerializedName("data") var data: T?)

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
    @SerializedName("url") var url: String = ""
)

data class BannerList(
    @SerializedName("banner_list") val bannerList: MutableList<Banner> = mutableListOf(),
)

data class Banner(
    @SerializedName("app_id") val appId: String = "",
    @SerializedName("area") val area: String = "",
    @SerializedName("begin_time") val beginTime: String = "",
    @SerializedName("cid") val cid: String = "",
    @SerializedName("cover_url") val coverUrl: String = "",
    @SerializedName("data") val dataInfo: String = "",
    @SerializedName("dateline") val dateline: String = "",
    @SerializedName("deleted") val deleted: String = "",
    @SerializedName("duration") val duration: String = "",
    @SerializedName("end_time") val endTime: String = "",
    @SerializedName("icon") val icon: String = "",
    @SerializedName("id") val id: String = "",
    @SerializedName("image") val image: String = "",
    @SerializedName("language") val language: String = "",
    @SerializedName("limit_lv") val limitLv: String = "",
    @SerializedName("ordering") val ordering: String = "",
    @SerializedName("position") val position: String = "",
    @SerializedName("role") val role: String = "",
    @SerializedName("room_type") val roomType: String = "",
    @SerializedName("settlement_channel") val settlementChannel: String = "",
    @SerializedName("stype") val sType: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("url") val url: String = ""
)


data class LiveRecommendModel(
    @SerializedName("more") var more: Int = 0,
    @SerializedName("ranker") var ranker: List<Ranker> = mutableListOf(),
    @SerializedName("rooms") var liveRecommendRoomInfos: List<LiveRecommendRoomInfo> = mutableListOf()
)

data class Ranker(
    @SerializedName("icon") var icon: String = "",
    @SerializedName("uid") var uid: String = ""
)


data class RecommendItem(
    @SerializedName("agora_token") var agoraToken: String = "",
    @SerializedName("area_code") var areaCode: String = "",
    @SerializedName("boom_rocket_lv") var boomRocketLv: Int = 0,
    @SerializedName("hot_num") var hotNum: Int = 0,
    @SerializedName("icon") var icon: String = "",
    @SerializedName("link_mic_status") var linkMicStatus: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("online_num") var onlineNum: Int = 0,
    @SerializedName("pk_state") var pkState: Int = 0,
    @SerializedName("property") var property: String = "",
    @SerializedName("rid") var rid: Int = 0,
    @SerializedName("room_sex") var roomSex: Int = 0,
    @SerializedName("show_red_packet") var showRedPacket: Boolean,
    @SerializedName("tags") var tags: Int = 0,
    @SerializedName("team_pk_state") var teamPkState: Int = 0,
    @SerializedName("uid") var uid: Int = 0,
)

data class LiveRecommendRoomInfo(
    @SerializedName("act_hot_num") var actHotNum: Int = 0,
    @SerializedName("agora_token") var agoraToken: String = "",
    @SerializedName("area") var area: String = "",
    @SerializedName("areaCode") var areaCode: String = "",

    @SerializedName("boss_icon") var bossIcon: String = "",
    @SerializedName("boss_uid") var bossUid: String = "",
    @SerializedName("dateline") var dateline: String = "",
    @SerializedName("deleted") var deleted: Int = 0,
    @SerializedName("effect") var effect: String = "",
    @SerializedName("fixed_tag_id_new") var fixedTagIdNew: Int = 0,
    @SerializedName("game_money_type") var gameMoneyType: Int = 0,
    @SerializedName("game_status") var gameStatus: Int = 0,
    @SerializedName("guestEnable") var guestEnable: Int = 0,
    @SerializedName("hot") var hot: Int = 0,
    @SerializedName("hot_num") var hotNum: Int = 0,
    @SerializedName("icon") var icon: String = "",
    @SerializedName("is_biz") var isBiz: Int = 0,
    @SerializedName("language") var language: String = "",


    @SerializedName("num_boy") var numBoy: Int = 0,
    @SerializedName("num_girl") var numGirl: Int = 0,

    @SerializedName("password") var password: Int = 0,
    @SerializedName("pattern_style") var patternStyle: Int = 0,
    @SerializedName("pay_room_money") var payRoomMoney: Int = 0,
    @SerializedName("pk_state") var pkState: Int = 0,
    @SerializedName("prefix") var prefix: String = "",
    @SerializedName("property") var property: String = "",
    @SerializedName("real") var real: Int = 0,
    @SerializedName("reception_uid") var receptionUid: String = "",

    @SerializedName("sex") var sex: Int = 0,


    @SerializedName("type") var type: String = "",
    @SerializedName("typeName") var typeName: TypeName,
    @SerializedName("types") var types: String = "",
    @SerializedName("uid") var uid: Int = 0,
    @SerializedName("uname") var uname: String = "",
    @SerializedName("user_icon") var userIcon: String = "",
    @SerializedName("users") var users: List<User>,
    @SerializedName("utitle") var uTitle: Int = 0,
    @SerializedName("vip") var vip: Int = 0
)

data class TypeName(
    @SerializedName("abstract_icon") var abstractIcon: String = "",
    @SerializedName("acute_icon") var acuteIcon: String = "",
    @SerializedName("bg") var bg: List<String>,
    @SerializedName("big_icon") var bigIcon: String = "",
    @SerializedName("color") var color: String = "",
    @SerializedName("icon") var icon: String = "",
    @SerializedName("label") var label: String = "",
    @SerializedName("main_bg") var mainBg: List<String>,
    @SerializedName("party_style") var partyStyle: Int = 0,
    @SerializedName("room_type") var roomType: RoomType,
    @SerializedName("show") var show: String = "",
    @SerializedName("small_icon") var smallIcon: String = "",
    @SerializedName("tag_color") var tagColor: String = "",
    @SerializedName("tag_icon") var tagIcon: String = "",
    @SerializedName("taginfo_type") var taginfoType: String = "",
    @SerializedName("type_bg") var typeBg: List<String>,
    @SerializedName("type_icon") var typeIcon: String = ""
)

data class User(
    @SerializedName("icon") var icon: String = "",
    @SerializedName("raw_icon") var rawIcon: String = "",
    @SerializedName("uid") var uid: Int = 0
)

data class RoomType(
    @SerializedName("acute_icon") var acuteIcon: String = "",
    @SerializedName("app_id") var appId: List<Int>,
    @SerializedName("bg") var bg: List<String>,
    @SerializedName("label") var label: String = "",
    @SerializedName("room_type") var roomType: String = "",
    @SerializedName("show") var show: String = "",
    @SerializedName("string_key") var stringKey: String = ""
)
