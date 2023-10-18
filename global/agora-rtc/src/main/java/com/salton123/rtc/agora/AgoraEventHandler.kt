package com.salton123.rtc.agora

import android.graphics.Rect
import android.util.Log
import com.blankj.utilcode.util.GsonUtils
import io.agora.rtc2.ClientRoleOptions
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.UserInfo
import java.util.concurrent.CopyOnWriteArrayList

class AgoraEventHandler : IRtcEngineEventHandler() {
    private val eventHandlers: CopyOnWriteArrayList<IRtcEngineEventHandler> = CopyOnWriteArrayList()
    private val tag = "AgoraEventHandler"
    fun addListener(listener: IRtcEngineEventHandler) {
        if (!eventHandlers.contains(listener)) {
            eventHandlers.add(listener)
        }
    }

    fun removeListener(listener: IRtcEngineEventHandler) {
        eventHandlers.remove(listener)
    }

    override fun onError(err: Int) {
        super.onError(err)
        eventHandlers.forEach {
            it.onError(err)
        }
        Log.i(tag, "onError:$err")
    }

    override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
        super.onJoinChannelSuccess(channel, uid, elapsed)
        eventHandlers.forEach {
            it.onJoinChannelSuccess(channel, uid, elapsed)
        }
        Log.i(tag, "onJoinChannelSuccess:$channel,$uid,$elapsed")
    }

    override fun onRejoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
        super.onRejoinChannelSuccess(channel, uid, elapsed)
        eventHandlers.forEach {
            it.onRejoinChannelSuccess(channel, uid, elapsed)
        }
        Log.i(tag, "onRejoinChannelSuccess:$channel,$uid,$elapsed")
    }

    override fun onLeaveChannel(stats: RtcStats?) {
        super.onLeaveChannel(stats)
        eventHandlers.forEach {
            it.onLeaveChannel(stats)
        }
        Log.i(tag, "onLeaveChannel:$stats")
    }

    override fun onLocalUserRegistered(uid: Int, userAccount: String?) {
        super.onLocalUserRegistered(uid, userAccount)
        eventHandlers.forEach {
            it.onLocalUserRegistered(uid, userAccount)
        }
        Log.i(tag, "onLocalUserRegistered:$uid,$userAccount")
    }

    override fun onUserInfoUpdated(uid: Int, userInfo: UserInfo?) {
        super.onUserInfoUpdated(uid, userInfo)
        eventHandlers.forEach {
            it.onUserInfoUpdated(uid, userInfo)
        }
        Log.i(tag, "onUserInfoUpdated:$uid,$userInfo")
    }

    override fun onUserStateChanged(uid: Int, state: Int) {
        super.onUserStateChanged(uid, state)
        eventHandlers.forEach {
            it.onUserStateChanged(uid, state)
        }
        Log.i(tag, "onUserStateChanged:$uid,$state")
    }

    override fun onFirstLocalVideoFramePublished(source: Constants.VideoSourceType?, elapsed: Int) {
        super.onFirstLocalVideoFramePublished(source, elapsed)
        eventHandlers.forEach {
            it.onFirstLocalVideoFramePublished(source, elapsed)
        }
        Log.i(tag, "onFirstLocalVideoFramePublished:$source,$elapsed")
    }

    override fun onUserJoined(uid: Int, elapsed: Int) {
        super.onUserJoined(uid, elapsed)
        eventHandlers.forEach {
            it.onUserJoined(uid, elapsed)
        }
        Log.i(tag, "onUserJoined:$uid,$elapsed")
    }

    override fun onUserOffline(uid: Int, reason: Int) {
        super.onUserOffline(uid, reason)
        eventHandlers.forEach {
            it.onUserOffline(uid, reason)
        }
        Log.i(tag, "onUserOffline:$uid,$reason")
    }

    override fun onConnectionStateChanged(state: Int, reason: Int) {
        super.onConnectionStateChanged(state, reason)
        eventHandlers.forEach {
            it.onConnectionStateChanged(state, reason)
        }
        Log.i(tag, "onConnectionStateChanged:$state,$reason")
    }

    override fun onConnectionInterrupted() {
        super.onConnectionInterrupted()
        eventHandlers.forEach {
            it.onConnectionInterrupted()
        }
        Log.i(tag, "onConnectionInterrupted")
    }

    override fun onConnectionLost() {
        super.onConnectionLost()
        eventHandlers.forEach {
            it.onConnectionLost()
        }
        Log.i(tag, "onConnectionLost")
    }

    override fun onConnectionBanned() {
        super.onConnectionBanned()
        eventHandlers.forEach {
            it.onConnectionBanned()
        }
        Log.i(tag, "onConnectionBanned")
    }

    override fun onTokenPrivilegeWillExpire(token: String?) {
        super.onTokenPrivilegeWillExpire(token)
        eventHandlers.forEach {
            it.onTokenPrivilegeWillExpire(token)
        }
        Log.i(tag, "onTokenPrivilegeWillExpire:$token")
    }

    override fun onRequestToken() {
        super.onRequestToken()
        eventHandlers.forEach {
            it.onRequestToken()
        }
        Log.i(tag, "onRequestToken")
    }

    override fun onLicenseValidationFailure(error: Int) {
        super.onLicenseValidationFailure(error)
        eventHandlers.forEach {
            it.onLicenseValidationFailure(error)
        }
        Log.i(tag, "onLicenseValidationFailure:$error")
    }


    override fun onAudioVolumeIndication(speakers: Array<out AudioVolumeInfo>?, totalVolume: Int) {
        super.onAudioVolumeIndication(speakers, totalVolume)
        eventHandlers.forEach {
            it.onAudioVolumeIndication(speakers, totalVolume)
        }
        Log.i(tag, "onAudioVolumeIndication:$speakers,$totalVolume")
    }

    override fun onActiveSpeaker(uid: Int) {
        super.onActiveSpeaker(uid)
        eventHandlers.forEach {
            it.onActiveSpeaker(uid)
        }
        Log.i(tag, "onActiveSpeaker:$uid")
    }

    override fun onFirstLocalAudioFramePublished(elapsed: Int) {
        super.onFirstLocalAudioFramePublished(elapsed)
        eventHandlers.forEach {
            it.onFirstLocalAudioFramePublished(elapsed)
        }
        Log.i(tag, "onFirstLocalAudioFramePublished:$elapsed")
    }

    override fun onFirstRemoteAudioFrame(uid: Int, elapsed: Int) {
        super.onFirstRemoteAudioFrame(uid, elapsed)
        eventHandlers.forEach {
            it.onFirstRemoteAudioFrame(uid, elapsed)
        }
        Log.i(tag, "onFirstRemoteAudioFrame:$uid,$elapsed")
    }

    override fun onVideoStopped() {
        super.onVideoStopped()
        eventHandlers.forEach {
            it.onVideoStopped()
        }
        Log.i(tag, "onVideoStopped")
    }


    override fun onFirstRemoteVideoDecoded(uid: Int, width: Int, height: Int, elapsed: Int) {
        super.onFirstRemoteVideoDecoded(uid, width, height, elapsed)
        eventHandlers.forEach {
            it.onFirstRemoteVideoDecoded(uid, width, height, elapsed)
        }
        Log.i(tag, "onFirstRemoteVideoDecoded:$uid,$width,$height,$elapsed")
    }

    override fun onFirstRemoteVideoFrame(uid: Int, width: Int, height: Int, elapsed: Int) {
        super.onFirstRemoteVideoFrame(uid, width, height, elapsed)
        eventHandlers.forEach {
            it.onFirstRemoteVideoFrame(uid, width, height, elapsed)
        }
        Log.i(tag, "onFirstRemoteVideoFrame:$uid,$width,$height,$elapsed")
    }

    override fun onUserMuteAudio(uid: Int, muted: Boolean) {
        super.onUserMuteAudio(uid, muted)
        eventHandlers.forEach {
            it.onUserMuteAudio(uid, muted)
        }
        Log.i(tag, "onUserMuteAudio:$uid,$muted")
    }

    override fun onUserMuteVideo(uid: Int, muted: Boolean) {
        super.onUserMuteVideo(uid, muted)
        eventHandlers.forEach {
            it.onUserMuteVideo(uid, muted)
        }
        Log.i(tag, "onUserMuteVideo:$uid,$muted")
    }

    override fun onUserEnableVideo(uid: Int, enabled: Boolean) {
        super.onUserEnableVideo(uid, enabled)
        eventHandlers.forEach {
            it.onUserEnableVideo(uid, enabled)
        }
        Log.i(tag, "onUserEnableVideo:$uid,$enabled")
    }

    override fun onUserEnableLocalVideo(uid: Int, enabled: Boolean) {
        super.onUserEnableLocalVideo(uid, enabled)
        eventHandlers.forEach {
            it.onUserEnableLocalVideo(uid, enabled)
        }
        Log.i(tag, "onUserEnableLocalVideo:$uid,$enabled")
    }

    override fun onRemoteAudioStateChanged(uid: Int, state: Int, reason: Int, elapsed: Int) {
        super.onRemoteAudioStateChanged(uid, state, reason, elapsed)
        eventHandlers.forEach {
            it.onRemoteAudioStateChanged(uid, state, reason, elapsed)
        }
        Log.i(tag, "onRemoteAudioStateChanged:$uid,$state,$reason")
    }

    override fun onAudioPublishStateChanged(channel: String?, oldState: Int, newState: Int, elapseSinceLastState: Int) {
        super.onAudioPublishStateChanged(channel, oldState, newState, elapseSinceLastState)
        eventHandlers.forEach {
            it.onAudioPublishStateChanged(channel, oldState, newState, elapseSinceLastState)
        }
        Log.i(tag, "onAudioPublishStateChanged:$channel,$oldState,$newState,$elapseSinceLastState")
    }

    override fun onVideoPublishStateChanged(source: Constants.VideoSourceType?, channel: String?, oldState: Int, newState: Int, elapseSinceLastState: Int) {
        super.onVideoPublishStateChanged(source, channel, oldState, newState, elapseSinceLastState)
        eventHandlers.forEach {
            it.onVideoPublishStateChanged(source, channel, oldState, newState, elapseSinceLastState)
        }
        Log.i(tag, "onVideoPublishStateChanged:$source,$channel,$oldState,$newState,$elapseSinceLastState")
    }

    override fun onUploadLogResult(requestId: String?, success: Boolean, reason: Int) {
        super.onUploadLogResult(requestId, success, reason)
        eventHandlers.forEach {
            it.onUploadLogResult(requestId, success, reason)
        }
        Log.i(tag, "onUploadLogResult:$requestId,$success,$reason")
    }

    override fun onWlAccMessage(reason: Int, action: Int, wlAccMsg: String?) {
        super.onWlAccMessage(reason, action, wlAccMsg)
        eventHandlers.forEach {
            it.onWlAccMessage(reason, action, wlAccMsg)
        }
        Log.i(tag, "onWlAccMessage:$reason,$action,$wlAccMsg")
    }

    override fun onWlAccStats(currentStats: WlAccStats?, averageStats: WlAccStats?) {
        super.onWlAccStats(currentStats, averageStats)
        eventHandlers.forEach {
            it.onWlAccStats(currentStats, averageStats)
        }
        Log.i(tag, "onWlAccStats:$currentStats,$averageStats")
    }

    override fun onVideoRenderingTracingResult(uid: Int, currentEvent: Constants.MEDIA_TRACE_EVENT?, tracingInfo: VideoRenderingTracingInfo?) {
        super.onVideoRenderingTracingResult(uid, currentEvent, tracingInfo)
        eventHandlers.forEach {
            it.onVideoRenderingTracingResult(uid, currentEvent, tracingInfo)
        }
        Log.i(tag, "onVideoRenderingTracingResult:$uid,$currentEvent,$tracingInfo")
    }

    override fun onAudioSubscribeStateChanged(
        channel: String?,
        uid: Int,
        oldState: Int,
        newState: Int,
        elapseSinceLastState: Int
    ) {
        super.onAudioSubscribeStateChanged(channel, uid, oldState, newState, elapseSinceLastState)
        eventHandlers.forEach {
            it.onAudioSubscribeStateChanged(channel, uid, oldState, newState, elapseSinceLastState)
        }
        Log.i(tag, "onAudioSubscribeStateChanged:$channel,$uid,$oldState,$newState,$elapseSinceLastState")
    }

    override fun onVideoSubscribeStateChanged(
        channel: String?,
        uid: Int,
        oldState: Int,
        newState: Int,
        elapseSinceLastState: Int
    ) {
        super.onVideoSubscribeStateChanged(channel, uid, oldState, newState, elapseSinceLastState)
        eventHandlers.forEach {
            it.onVideoSubscribeStateChanged(channel, uid, oldState, newState, elapseSinceLastState)
        }
        Log.i(tag, "onVideoSubscribeStateChanged:$channel,$uid,$oldState,$newState,$elapseSinceLastState")
    }

    override fun onRemoteVideoStateChanged(uid: Int, state: Int, reason: Int, elapsed: Int) {
        super.onRemoteVideoStateChanged(uid, state, reason, elapsed)
        eventHandlers.forEach {
            it.onRemoteVideoStateChanged(uid, state, reason, elapsed)
        }
        Log.i(tag, "onRemoteVideoStateChanged:$uid,$state,$reason,$elapsed")
    }

    override fun onChannelMediaRelayStateChanged(state: Int, code: Int) {
        super.onChannelMediaRelayStateChanged(state, code)
        eventHandlers.forEach {
            it.onChannelMediaRelayStateChanged(state, code)
        }
        Log.i(tag, "onChannelMediaRelayStateChanged:$state,$code")
    }

    override fun onChannelMediaRelayEvent(code: Int) {
        super.onChannelMediaRelayEvent(code)
        eventHandlers.forEach {
            it.onChannelMediaRelayEvent(code)
        }
        Log.i(tag, "onChannelMediaRelayEvent:$code")
    }

    override fun onIntraRequestReceived() {
        super.onIntraRequestReceived()
        eventHandlers.forEach {
            it.onIntraRequestReceived()
        }
        Log.i(tag, "onIntraRequestReceived")
    }

    override fun onUplinkNetworkInfoUpdated(info: UplinkNetworkInfo?) {
        super.onUplinkNetworkInfoUpdated(info)
        eventHandlers.forEach {
            it.onUplinkNetworkInfoUpdated(info)
        }
        Log.i(tag, "onUplinkNetworkInfoUpdated:${GsonUtils.toJson(info)}")
    }

    override fun onDownlinkNetworkInfoUpdated(info: DownlinkNetworkInfo?) {
        super.onDownlinkNetworkInfoUpdated(info)
        eventHandlers.forEach {
            it.onDownlinkNetworkInfoUpdated(info)
        }
        Log.i(tag, "onDownlinkNetworkInfoUpdated:${GsonUtils.toJson(info)}")
    }

    override fun onEncryptionError(errorType: Int) {
        super.onEncryptionError(errorType)
        eventHandlers.forEach {
            it.onEncryptionError(errorType)
        }
        Log.i(tag, "onEncryptionError:$errorType")
    }

    override fun onPermissionError(permission: Int) {
        super.onPermissionError(permission)
        eventHandlers.forEach {
            it.onPermissionError(permission)
        }
        Log.i(tag, "onPermissionError:$permission")
    }

    override fun onLocalPublishFallbackToAudioOnly(isFallbackOrRecover: Boolean) {
        super.onLocalPublishFallbackToAudioOnly(isFallbackOrRecover)
        eventHandlers.forEach {
            it.onLocalPublishFallbackToAudioOnly(isFallbackOrRecover)
        }
        Log.i(tag, "onLocalPublishFallbackToAudioOnly:$isFallbackOrRecover")
    }

    override fun onRemoteSubscribeFallbackToAudioOnly(uid: Int, isFallbackOrRecover: Boolean) {
        super.onRemoteSubscribeFallbackToAudioOnly(uid, isFallbackOrRecover)
        eventHandlers.forEach {
            it.onRemoteSubscribeFallbackToAudioOnly(uid, isFallbackOrRecover)
        }
        Log.i(tag, "onRemoteSubscribeFallbackToAudioOnly:$uid,$isFallbackOrRecover")
    }

    override fun onAudioRouteChanged(routing: Int) {
        super.onAudioRouteChanged(routing)
        eventHandlers.forEach {
            it.onAudioRouteChanged(routing)
        }
        Log.i(tag, "onAudioRouteChanged:$routing")
    }

    override fun onCameraReady() {
        super.onCameraReady()
        eventHandlers.forEach {
            it.onCameraReady()
        }
        Log.i(tag, "onCameraReady")
    }

    override fun onCameraFocusAreaChanged(rect: Rect?) {
        super.onCameraFocusAreaChanged(rect)
        eventHandlers.forEach {
            it.onCameraFocusAreaChanged(rect)
        }
        Log.i(tag, "onCameraFocusAreaChanged:$rect")
    }

    override fun onCameraExposureAreaChanged(rect: Rect?) {
        super.onCameraExposureAreaChanged(rect)
        eventHandlers.forEach {
            it.onCameraExposureAreaChanged(rect)
        }
        Log.i(tag, "onCameraExposureAreaChanged:$rect")
    }

    override fun onContentInspectResult(result: Int) {
        super.onContentInspectResult(result)
        eventHandlers.forEach {
            it.onContentInspectResult(result)
        }
        Log.i(tag, "onContentInspectResult:$result")
    }

    override fun onSnapshotTaken(uid: Int, filePath: String?, width: Int, height: Int, errCode: Int) {
        super.onSnapshotTaken(uid, filePath, width, height, errCode)
        eventHandlers.forEach {
            it.onSnapshotTaken(uid, filePath, width, height, errCode)
        }
        Log.i(tag, "onSnapshotTaken:$uid,$filePath,$width,$height,$errCode")
    }

    override fun onFacePositionChanged(imageWidth: Int, imageHeight: Int, faces: Array<out AgoraFacePositionInfo>?) {
        super.onFacePositionChanged(imageWidth, imageHeight, faces)
        eventHandlers.forEach {
            it.onFacePositionChanged(imageWidth, imageHeight, faces)
        }
        Log.i(tag, "onFacePositionChanged:$imageWidth,$imageHeight,$faces")
    }

    override fun onAudioQuality(uid: Int, quality: Int, delay: Short, lost: Short) {
        super.onAudioQuality(uid, quality, delay, lost)
        eventHandlers.forEach {
            it.onAudioQuality(uid, quality, delay, lost)
        }
        Log.i(tag, "onAudioQuality:$uid,$quality,$delay,$lost")
    }

    override fun onRtcStats(stats: RtcStats?) {
        super.onRtcStats(stats)
        eventHandlers.forEach {
            it.onRtcStats(stats)
        }
        Log.i(tag, "onRtcStats:${GsonUtils.toJson(stats)}")
    }


    override fun onLastmileQuality(quality: Int) {
        super.onLastmileQuality(quality)
        eventHandlers.forEach {
            it.onLastmileQuality(quality)
        }
        Log.i(tag, "onLastmileQuality:$quality")
    }

    override fun onLastmileProbeResult(result: LastmileProbeResult?) {
        super.onLastmileProbeResult(result)
        eventHandlers.forEach {
            it.onLastmileProbeResult(result)
        }
        Log.i(tag, "onLastmileProbeResult:$result")
    }

    override fun onNetworkQuality(uid: Int, txQuality: Int, rxQuality: Int) {
        super.onNetworkQuality(uid, txQuality, rxQuality)
        eventHandlers.forEach {
            it.onNetworkQuality(uid, txQuality, rxQuality)
        }
        Log.i(tag, "onNetworkQuality:$uid,$txQuality,$rxQuality")
    }


    override fun onRemoteVideoStats(stats: RemoteVideoStats?) {
        super.onRemoteVideoStats(stats)
        eventHandlers.forEach {
            it.onRemoteVideoStats(stats)
        }
        Log.i(tag, "onRemoteVideoStats:${GsonUtils.toJson(stats)}")
    }

    override fun onLocalAudioStats(stats: LocalAudioStats?) {
        super.onLocalAudioStats(stats)
        eventHandlers.forEach {
            it.onLocalAudioStats(stats)
        }
        Log.i(tag, "onLocalAudioStats:${GsonUtils.toJson(stats)}")
    }

    override fun onFirstLocalVideoFrame(source: Constants.VideoSourceType?, width: Int, height: Int, elapsed: Int) {
        super.onFirstLocalVideoFrame(source, width, height, elapsed)
        eventHandlers.forEach {
            it.onFirstLocalVideoFrame(source, width, height, elapsed)
        }
        Log.i(tag, "onFirstLocalVideoFrame:$source,$width,$height,$elapsed")
    }

    override fun onRemoteAudioStats(stats: RemoteAudioStats?) {
        super.onRemoteAudioStats(stats)
        eventHandlers.forEach {
            it.onRemoteAudioStats(stats)
        }
        Log.i(tag, "onRemoteAudioStats:${GsonUtils.toJson(stats)}")
    }

    override fun onLocalVideoStats(source: Constants.VideoSourceType?, stats: LocalVideoStats?) {
        super.onLocalVideoStats(source, stats)
        eventHandlers.forEach {
            it.onLocalVideoStats(source, stats)
        }
        Log.i(tag, "onLocalVideoStats:$source,${GsonUtils.toJson(stats)}")
    }

    override fun onLocalVideoStat(sentBitrate: Int, sentFrameRate: Int) {
        super.onLocalVideoStat(sentBitrate, sentFrameRate)
        eventHandlers.forEach {
            it.onLocalVideoStat(sentBitrate, sentFrameRate)
        }
        Log.i(tag, "onLocalVideoStat:$sentBitrate")
    }

    override fun onRemoteVideoStat(uid: Int, delay: Int, receivedBitrate: Int, receivedFrameRate: Int) {
        super.onRemoteVideoStat(uid, delay, receivedBitrate, receivedFrameRate)
        eventHandlers.forEach {
            it.onRemoteVideoStat(uid, delay, receivedBitrate, receivedFrameRate)
        }
        Log.i(tag, "onRemoteVideoStat:$uid,$delay,$receivedBitrate,$receivedFrameRate")
    }

    override fun onRemoteAudioTransportStats(uid: Int, delay: Int, lost: Int, rxKBitRate: Int) {
        super.onRemoteAudioTransportStats(uid, delay, lost, rxKBitRate)
        eventHandlers.forEach {
            it.onRemoteAudioTransportStats(uid, delay, lost, rxKBitRate)
        }
        Log.i(tag, "onRemoteAudioTransportStats:$uid,$delay,$lost,$rxKBitRate")
    }

    override fun onRemoteVideoTransportStats(uid: Int, delay: Int, lost: Int, rxKBitRate: Int) {
        super.onRemoteVideoTransportStats(uid, delay, lost, rxKBitRate)
        eventHandlers.forEach {
            it.onRemoteVideoTransportStats(uid, delay, lost, rxKBitRate)
        }
        Log.i(tag, "onRemoteVideoTransportStats:$uid,$delay,$lost,$rxKBitRate")
    }

    override fun onAudioMixingStateChanged(state: Int, reason: Int) {
        super.onAudioMixingStateChanged(state, reason)
        eventHandlers.forEach {
            it.onAudioMixingStateChanged(state, reason)
        }
        Log.i(tag, "onAudioMixingStateChanged:$state,$reason")
    }

    override fun onAudioMixingPositionChanged(position: Long) {
        super.onAudioMixingPositionChanged(position)
        eventHandlers.forEach {
            it.onAudioMixingPositionChanged(position)
        }
        Log.i(tag, "onAudioMixingPositionChanged:$position")
    }

    override fun onAudioMixingFinished() {
        super.onAudioMixingFinished()
        eventHandlers.forEach {
            it.onAudioMixingFinished()
        }
        Log.i(tag, "onAudioMixingFinished")
    }

    override fun onAudioEffectFinished(soundId: Int) {
        super.onAudioEffectFinished(soundId)
        eventHandlers.forEach {
            it.onAudioEffectFinished(soundId)
        }
        Log.i(tag, "onAudioEffectFinished:$soundId")
    }

    override fun onClientRoleChanged(oldRole: Int, newRole: Int, newRoleOptions: ClientRoleOptions?) {
        super.onClientRoleChanged(oldRole, newRole, newRoleOptions)
        eventHandlers.forEach {
            it.onClientRoleChanged(oldRole, newRole, newRoleOptions)
        }
        Log.i(tag, "onClientRoleChanged:$oldRole,$newRole,$newRoleOptions")
    }

    override fun onClientRoleChangeFailed(reason: Int, currentRole: Int) {
        super.onClientRoleChangeFailed(reason, currentRole)
        eventHandlers.forEach {
            it.onClientRoleChangeFailed(reason, currentRole)
        }
        Log.i(tag, "onClientRoleChangeFailed:$reason,$currentRole")
    }

    override fun onFirstRemoteAudioDecoded(uid: Int, elapsed: Int) {
        super.onFirstRemoteAudioDecoded(uid, elapsed)
        eventHandlers.forEach {
            it.onFirstRemoteAudioDecoded(uid, elapsed)
        }
        Log.i(tag, "onFirstRemoteAudioDecoded:$uid")
    }

    override fun onLocalAudioStateChanged(state: Int, error: Int) {
        super.onLocalAudioStateChanged(state, error)
        eventHandlers.forEach {
            it.onLocalAudioStateChanged(state, error)
        }
        Log.i(tag, "onLocalAudioStateChanged:$state")
    }

    override fun onRtmpStreamingStateChanged(url: String?, state: Int, errCode: Int) {
        super.onRtmpStreamingStateChanged(url, state, errCode)
        eventHandlers.forEach {
            it.onRtmpStreamingStateChanged(url, state, errCode)
        }
        Log.i(tag, "onRtmpStreamingStateChanged:$url,$state,$errCode")
    }


    override fun onTranscodingUpdated() {
        super.onTranscodingUpdated()
        eventHandlers.forEach {
            it.onTranscodingUpdated()
        }
        Log.i(tag, "onTranscodingUpdated")
    }

    override fun onRtmpStreamingEvent(url: String?, error: Int) {
        super.onRtmpStreamingEvent(url, error)
        eventHandlers.forEach {
            it.onRtmpStreamingEvent(url, error)
        }
        Log.i(tag, "onRtmpStreamingEvent:$url,$error")
    }

    override fun onStreamInjectedStatus(url: String?, uid: Int, status: Int) {
        super.onStreamInjectedStatus(url, uid, status)
        eventHandlers.forEach {
            it.onStreamInjectedStatus(url, uid, status)
        }
        Log.i(tag, "onStreamInjectedStatus:$url,$uid,$status")
    }

    override fun onStreamMessage(uid: Int, streamId: Int, data: ByteArray?) {
        super.onStreamMessage(uid, streamId, data)
        eventHandlers.forEach {
            it.onStreamMessage(uid, streamId, data)
        }
        Log.i(tag, "onStreamMessage:$uid,$streamId,$data")
    }

    override fun onStreamMessageError(uid: Int, streamId: Int, error: Int, missed: Int, cached: Int) {
        super.onStreamMessageError(uid, streamId, error, missed, cached)
        eventHandlers.forEach {
            it.onStreamMessageError(uid, streamId, error, missed, cached)
        }
        Log.i(tag, "onStreamMessageError:$uid,$streamId,$error,$missed,$cached")
    }

    override fun onMediaEngineLoadSuccess() {
        super.onMediaEngineLoadSuccess()
        eventHandlers.forEach {
            it.onMediaEngineLoadSuccess()
        }
        Log.i(tag, "onMediaEngineLoadSuccess")
    }

    override fun onMediaEngineStartCallSuccess() {
        super.onMediaEngineStartCallSuccess()
        eventHandlers.forEach {
            it.onMediaEngineStartCallSuccess()
        }
        Log.i(tag, "onMediaEngineStartCallSuccess")
    }

    override fun onNetworkTypeChanged(type: Int) {
        super.onNetworkTypeChanged(type)
        eventHandlers.forEach {
            it.onNetworkTypeChanged(type)
        }
        Log.i(tag, "onNetworkTypeChanged:$type")
    }

}