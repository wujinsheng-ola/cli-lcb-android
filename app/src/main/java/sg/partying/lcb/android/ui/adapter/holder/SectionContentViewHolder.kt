package sg.partying.lcb.android.ui.adapter.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sg.olaparty.network.base.NetworkConfigProvider
import sg.partying.lcb.android.R
import sg.partying.lcb.android.model.LiveRecommendContent
import sg.partying.lcb.android.util.ImageLoader

/**
 * Time:2022/1/30 11:43 下午
 * Author:
 * Description:
 */
class SectionContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var ivThumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail)
    var ivBadgePk: ImageView = itemView.findViewById(R.id.ivBadgePk)
    var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    var tvOnlineNum: TextView = itemView.findViewById(R.id.tvOnlineNum)
    fun updateUi(sectionItem: LiveRecommendContent) {
        ImageLoader.loadFitCenter(
            ivThumbnail,
            NetworkConfigProvider.RESOURCE_PREFIX_URL + sectionItem.liveRecommendRoomInfo.icon + "!cover375"
        )
        val info = sectionItem.liveRecommendRoomInfo
        if (info.pkState == 1) {
            ivBadgePk.visibility = View.VISIBLE
        } else {
            ivBadgePk.visibility = View.GONE
        }
        tvTitle.text = info.name + ""
        tvOnlineNum.text = "${info.hotNum}"
    }
}
