package sg.partying.lcb.live.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.salton123.rtc.agora.AgoraFacade
import com.salton123.soulove.api.ProviderManager
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import sg.partying.lcb.live.model.PreviewData
import sg.partying.lcb.model.RoomInfo

/**
 * Time:2023/10/18 11:58
 * Author:
 * Description:
 */
class LiveRoomPreviewViewModel : ViewModel() {
    val previewViewModel by lazy { MutableLiveData<PreviewData>() }
    var previewData = PreviewData("test", Uri.parse(""), "美颜")


    init {

    }

    fun changeTitle(title: String) {
        previewData.title = title
        previewViewModel.postValue(previewData)
    }

}