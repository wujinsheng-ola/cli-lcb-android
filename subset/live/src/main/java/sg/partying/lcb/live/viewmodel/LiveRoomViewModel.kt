package sg.partying.lcb.live.viewmodel

import androidx.lifecycle.MutableLiveData
import com.salton123.rtc.agora.AgoraFacade
import io.agora.rtc2.Constants.REMOTE_VIDEO_STATE_STARTING
import io.agora.rtc2.Constants.REMOTE_VIDEO_STATE_STOPPED
import io.agora.rtc2.IRtcEngineEventHandler
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 * Time:2023/10/18 11:58
 * Author:
 * Description:
 */
class LiveRoomViewModel : BaseViewModel() {
    val seatInfoRet by lazy { MutableLiveData<HashSet<Int>>() }
    val onlineLiveSet = hashSetOf<Int>()
    private var currentRid: String = ""
    private var currentUid: Int = 0
    var hasJoinChannel = false
    private val rtcEngineEventHandler = object : IRtcEngineEventHandler() {
        override fun onFirstRemoteVideoDecoded(uid: Int, width: Int, height: Int, elapsed: Int) {
            super.onFirstRemoteVideoDecoded(uid, width, height, elapsed)
//            firstRemoteVideoDecodedRet.value = uid

        }

        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            super.onJoinChannelSuccess(channel, uid, elapsed)
            if (channel == currentRid && uid == currentUid) {
                hasJoinChannel = true
            }
        }

        override fun onError(err: Int) {
            super.onError(err)
        }

        override fun onRemoteVideoStateChanged(uid: Int, state: Int, reason: Int, elapsed: Int) {
            super.onRemoteVideoStateChanged(uid, state, reason, elapsed)
            if (state == REMOTE_VIDEO_STATE_STARTING) {
                onlineLiveSet.add(uid)
                seatInfoRet.postValue(onlineLiveSet)
            } else if (state == REMOTE_VIDEO_STATE_STOPPED) {
                onlineLiveSet.remove(uid)
                seatInfoRet.postValue(onlineLiveSet)
            }
        }

        override fun onVideoSubscribeStateChanged(channel: String?, uid: Int, oldState: Int, newState: Int, elapseSinceLastState: Int) {
            super.onVideoSubscribeStateChanged(channel, uid, oldState, newState, elapseSinceLastState)
        }
    }

    init {
        AgoraFacade.addListener(rtcEngineEventHandler)
    }

    override fun onCleared() {
        super.onCleared()
        AgoraFacade.removeListener(rtcEngineEventHandler)
    }

    fun joinChannel(rtcToken: String, rid: String, uid: Int) {
        AgoraFacade.joinChannel(rtcToken, rid, uid)
        currentRid = rid
        currentUid = uid
    }

    fun onDestroy() {
        AgoraFacade.leaveChannel()
    }

}