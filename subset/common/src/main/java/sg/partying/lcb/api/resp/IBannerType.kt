package sg.partying.lcb.api.resp

import sg.partying.lcb.api.resp.RecommendItem

/**
 * Time:2022/1/27 11:23
 * Author:
 * Description:
 */
const val TYPE_CHAT_TAB_BANNER = 0x100
const val TYPE_LIVE_TAB_BANNER = 0x101

interface IBannerType {
    val type: Int
}

data class ChatBannerType(val banner: Banner) : IBannerType {
    override val type: Int get() = TYPE_CHAT_TAB_BANNER
    override fun equals(other: Any?): Boolean {
        if (other is Banner) {
            return other.coverUrl == banner.coverUrl
        }
        return false
    }
}

data class LiveBannerType(val banner: BannerItem) : IBannerType {
    override val type: Int get() = TYPE_LIVE_TAB_BANNER
    override fun equals(other: Any?): Boolean {
        if (other is Banner) {
            return other.coverUrl == banner.coverUrl
        }
        return false
    }
}