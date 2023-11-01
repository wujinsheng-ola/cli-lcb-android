package sg.partying.lcb.live

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.salton123.rtc.agora.AgoraFacade
import com.salton123.soulove.Constants
import com.salton123.soulove.api.ILiveRoomProvider
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.video.VideoCanvas
import sg.partying.lcb.model.RoomInfo

/**
 * Time:2023/11/1 10:15
 * Author:
 * Description:
 */
@Route(path = Constants.Provider.LIVE_ROOM, name = "live-room")
class LiveRoomProvider : ILiveRoomProvider {
    private var roomInfo: RoomInfo = RoomInfo()
    var isJoinedRoomLiveData: LiveData<Boolean>? = null
    private val rtcEngineEventHandler = object : IRtcEngineEventHandler() {
        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            super.onJoinChannelSuccess(channel, uid, elapsed)
            if (channel == roomInfo.rid && uid == roomInfo.uid) {
                isJoinedRoomLiveData = liveData(timeoutInMs = 5000) {
                    this.emit(true)
                }
            }

        }

        override fun onError(err: Int) {
            super.onError(err)
            if (err == (ErrorCode.ERR_TOKEN_EXPIRED or ErrorCode.ERR_INVALID_TOKEN)) {
                isJoinedRoomLiveData = liveData {
                    this.emit(false)
                }
            }
        }
    }

    override fun addListener(listener: IRtcEngineEventHandler) {
        AgoraFacade.addListener(listener)
    }

    override fun removeListener(listener: IRtcEngineEventHandler) {
        AgoraFacade.removeListener(listener)
    }

    override fun joinRoom(roomInfo: RoomInfo) {
        this.roomInfo = roomInfo
        AgoraFacade.joinChannel(roomInfo.rtcToken, roomInfo.rid, roomInfo.uid)
    }

    override fun leaveRoom() {
        /// 清空roomInfo
        this.roomInfo = RoomInfo()
        isJoinedRoomLiveData = liveData { emit(false) }
        AgoraFacade.leaveChannel()
    }

    override fun isJoinedRoom(): LiveData<Boolean>? {
        return isJoinedRoomLiveData
    }

    override fun setupRemoteVideo(canvas: VideoCanvas): Int {
        return AgoraFacade.setupRemoteVideo(canvas)
    }

    override fun init(context: Context?) {
        context?.let { AgoraFacade.init(it) }
        addListener(rtcEngineEventHandler)
    }
}