package sg.partying.lcb.live.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.salton123.rtc.agora.AgoraFacade
import com.salton123.soulove.api.ProviderManager
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import sg.partying.lcb.model.RoomInfo

/**
 * Time:2023/10/18 11:58
 * Author:
 * Description:
 */
class LiveRoomViewModel : ViewModel() {
    val seatInfoRet by lazy { MutableLiveData<HashSet<Int>>() }
    val onlineLiveSet = hashSetOf<Int>()

    private val rtcEngineEventHandler = object : IRtcEngineEventHandler() {
        override fun onRemoteVideoStateChanged(uid: Int, state: Int, reason: Int, elapsed: Int) {
            super.onRemoteVideoStateChanged(uid, state, reason, elapsed)
            if (state == Constants.REMOTE_VIDEO_STATE_STARTING) {
                onlineLiveSet.add(uid)
                seatInfoRet.postValue(onlineLiveSet)
            } else if (state == Constants.REMOTE_VIDEO_STATE_STOPPED) {
                onlineLiveSet.remove(uid)
                seatInfoRet.postValue(onlineLiveSet)
            }
        }
    }

    init {
        ProviderManager.liveRoom()?.addListener(rtcEngineEventHandler)
    }

    override fun onCleared() {
        super.onCleared()
        ProviderManager.liveRoom()?.apply {
            removeListener(rtcEngineEventHandler)
            leaveRoom()
        }
    }

    fun isJoinedRoom(): LiveData<Boolean>? {
        return ProviderManager.liveRoom()?.isJoinedRoom()
    }

    fun joinRoom(roomInfo: RoomInfo) {
        ProviderManager.liveRoom()?.joinRoom(roomInfo)
    }

}