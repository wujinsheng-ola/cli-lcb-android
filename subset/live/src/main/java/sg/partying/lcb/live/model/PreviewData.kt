package sg.partying.lcb.live.model

import android.net.Uri


/**
 *  开播信息，标题，封面，标签
 */
data class PreviewData constructor(var title: String, var imgUrl: Uri, var lable: String) {
    init {

    }

}