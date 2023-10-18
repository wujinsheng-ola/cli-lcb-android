package com.salton123.rtc.agora

import android.content.Context
import android.util.Log
import io.agora.rtc2.ChannelMediaOptions
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine
import io.agora.rtc2.UserInfo
import io.agora.rtc2.video.VideoEncoderConfiguration

/**
 * Time:2021/11/8 3:27 下午
 * Author:
 * Description:
 */
object AgoraFacade {
    private const val tag = "AgoraFacade"
    private val agoraEventHandler: AgoraEventHandler = AgoraEventHandler()
    lateinit var rtcEngine: RtcEngine
    fun init(context: Context) {
        rtcEngine = RtcEngine.create(context, AgoraConfigProvider.AGORA_APP_ID, agoraEventHandler)
        rtcEngine.apply {
            setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
            setClientRole(Constants.CLIENT_ROLE_BROADCASTER)
//            setExternalVideoSource(true, true, true)
            setVideoEncoderConfiguration(
                VideoEncoderConfiguration(
                    VideoEncoderConfiguration.VD_640x360,
                    VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15,
                    VideoEncoderConfiguration.STANDARD_BITRATE,
                    VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT
                )
            )
            enableVideo()
        }
    }

    fun addListener(listener: IRtcEngineEventHandler) {
        agoraEventHandler.addListener(listener)
    }

    fun removeListener(listener: IRtcEngineEventHandler) {
        agoraEventHandler.removeListener(listener)
    }

    fun joinChannel(token: String, channelName: String, uid: Int) {
        Log.i(tag, "joinChannel:$token,$channelName,$uid")
        val options = ChannelMediaOptions()
        options.publishCameraTrack = true
        options.publishMicrophoneTrack = true
        options.autoSubscribeAudio = true
        options.autoSubscribeVideo = true
        rtcEngine.joinChannel(token, channelName, uid, options)
    }


    /**
     * 可以获取直播间内用户的UserInfo
     */
    fun getUserInfoByUserAccount(uid: String): UserInfo {
        var info = UserInfo()
        rtcEngine.getUserInfoByUserAccount(uid, info)
        return info
    }

    /**
     * 可以获取直播间内用户的UserInfo
     */
    fun getUserInfoByUid(uid: Int): UserInfo {
        var info = UserInfo()
        rtcEngine.getUserInfoByUid(uid, info)
        return info
    }


    fun leaveChannel() {
        rtcEngine.leaveChannel()
    }
}