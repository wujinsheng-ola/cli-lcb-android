package sg.partying.lcb.android.model

import sg.partying.lcb.api.resp.RecommendItem

/**
 * Time:2022/1/27 11:23
 * Author:
 * Description:
 */
const val TYPE_LIVE_RECOMMEND_CONTENT = 0x100

interface IMultiType {
    val type: Int
}

data class LiveRecommendContent(val recommendItem: RecommendItem) : IMultiType {
    override val type: Int get() = TYPE_LIVE_RECOMMEND_CONTENT
    override fun equals(other: Any?): Boolean {
        if (other is LiveRecommendContent) {
            return other.recommendItem.uid == recommendItem.uid
        }
        return false
    }
}
