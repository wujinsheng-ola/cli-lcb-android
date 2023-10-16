package sg.partying.lcb.android.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import pb.FeedRecommendRoomData
import sg.partying.lcb.android.R
import sg.partying.lcb.android.util.ImageLoader

/**
 * Time:2022/3/25 8:26 下午
 * Author:
 * Description:
 */
class VideoSectionAdapter : BaseQuickAdapter<FeedRecommendRoomData, BaseViewHolder>(R.layout.adapter_item_live_recomemd) {
    override fun convert(holder: BaseViewHolder, item: FeedRecommendRoomData) {
        ImageLoader.loadFitCenter(holder.getView(R.id.ivThumbnail), item.icon)
    }

}