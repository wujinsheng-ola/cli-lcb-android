package sg.partying.lcb.android.viewmodel

import android.view.SurfaceView
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ThreadUtils.runOnUiThread
import com.salton123.rtc.agora.AgoraFacade
import io.agora.rtc2.Constants.REMOTE_VIDEO_STATE_STARTING
import io.agora.rtc2.Constants.REMOTE_VIDEO_STATE_STOPPED
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.video.VideoCanvas
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.state.ResultState
import sg.partying.lcb.android.api.resp.LoginOption
import sg.partying.lcb.android.api.resp.Resp

/**
 * Time:2023/10/18 11:58
 * Author:
 * Description:
 */
class LiveRoomViewModel : BaseViewModel() {
    val firstRemoteVideoDecodedRet by lazy { MutableLiveData<HashSet<Int>>() }
    val onlineLiveSet = hashSetOf<Int>()
    private val rtcEngineEventHandler = object : IRtcEngineEventHandler() {
        override fun onFirstRemoteVideoDecoded(uid: Int, width: Int, height: Int, elapsed: Int) {
            super.onFirstRemoteVideoDecoded(uid, width, height, elapsed)
//            firstRemoteVideoDecodedRet.value = uid

        }

        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            super.onJoinChannelSuccess(channel, uid, elapsed)
        }

        override fun onError(err: Int) {
            super.onError(err)
        }

        override fun onRemoteVideoStateChanged(uid: Int, state: Int, reason: Int, elapsed: Int) {
            super.onRemoteVideoStateChanged(uid, state, reason, elapsed)
            if (state == REMOTE_VIDEO_STATE_STARTING) {
                onlineLiveSet.add(uid)
                firstRemoteVideoDecodedRet.postValue(onlineLiveSet)
            } else if (state == REMOTE_VIDEO_STATE_STOPPED) {
                onlineLiveSet.remove(uid)
                firstRemoteVideoDecodedRet.postValue(onlineLiveSet)
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
    }

    fun onDestroy() {
        AgoraFacade.leaveChannel()
    }

}