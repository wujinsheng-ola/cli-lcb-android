package sg.partying.lcb.android

import android.os.Environment
import androidx.lifecycle.MutableLiveData
import sg.partying.lcb.model.RoomInfo
import java.io.File

/**
 * Time:2022/3/3 3:56 下午
 * Author:
 * Description:
 */
object Prop {
    var currentRoomInfo: RoomInfo? = null
    val toParentPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).absolutePath + File.separator + "eleph" + File.separator
    var qrCodeTempSavePath = toParentPath + "temp.jpg"
    var changeTabLiveData: MutableLiveData<Int> = MutableLiveData()


}