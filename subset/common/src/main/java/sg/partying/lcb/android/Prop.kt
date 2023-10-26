package sg.partying.lcb.android

import android.os.Environment
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
    val toParentPath = BaseApplication.sInstance.cacheDir.absolutePath + File.separator
    var qrCodeTempSavePath = toParentPath + "info.txt"
    var changeTabLiveData: MutableLiveData<Int> = MutableLiveData()


}