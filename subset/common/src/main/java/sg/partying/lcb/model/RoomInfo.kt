package sg.partying.lcb.model

import com.google.gson.annotations.SerializedName

/**
 * Time:2023/10/19 14:34
 * Author:
 * Description:
 */

/**
 * 标准的房间信息
 */
data class RoomInfo(
    @SerializedName("rtc_type") var rtcType: String,
    @SerializedName("rtc_token") var rtcToken: String,
    @SerializedName("room_id") var rid: String,
    @SerializedName("anchor_id") var anchorId: Int,
)