package com.salton123.soulove.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.template.IProvider
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.video.VideoCanvas
import sg.partying.lcb.model.RoomInfo

/**
 * Time:2023/11/1 10:02
 * Author:
 * Description:
 */
interface ILiveRoomProvider : IProvider {
    fun addListener(listener: IRtcEngineEventHandler)

    fun removeListener(listener: IRtcEngineEventHandler)

    fun joinRoom(roomInfo: RoomInfo)

    fun leaveRoom()

    fun isJoinedRoom(): LiveData<Boolean>?

    fun setupRemoteVideo(canvas: VideoCanvas): Int
}