package com.salton123.eleph

import android.os.Environment
import androidx.lifecycle.MutableLiveData
import java.io.File

/**
 * Time:2022/3/3 3:56 下午
 * Author:
 * Description:
 */
object Prop {
    val toParentPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).absolutePath + File.separator + "eleph" + File.separator
    var qrCodeTempSavePath = toParentPath + "temp.jpg"
    var changeTabLiveData: MutableLiveData<Int> = MutableLiveData()
}