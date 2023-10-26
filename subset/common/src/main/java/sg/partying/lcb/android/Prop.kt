package sg.partying.lcb.android

import androidx.lifecycle.MutableLiveData
import com.salton123.app.BaseApplication
import sg.partying.lcb.model.RoomInfo
import java.io.File

/**
 * Time:2022/3/3 3:56 下午
 * Author:
 * Description:
 */
object Prop {
    var currentRoomInfo: RoomInfo? = null
    val parentPath = BaseApplication.sInstance.cacheDir.absolutePath + File.separator + "ola_party" + File.separator
    val netCachePath = parentPath + "net_cache"
    var qrCodeTempSavePath = parentPath + "temp.jpg"
    var changeTabLiveData: MutableLiveData<Int> = MutableLiveData()


}