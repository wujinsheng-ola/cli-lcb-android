package com.salton123

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Process
import androidx.annotation.IntDef
import com.salton123.app.BaseApplication.Companion.getInstance
import java.io.File

/**
 * User: newSalton@outlook.com
 * Date: 2019-05-09 23:42
 * ModifyTime: 23:42
 * Description:
 */
object ClassPath {
    var BASE_PATH = getInstance<Application>().externalCacheDir.toString() + File.separator + "salton"
    var IS_DEBUG = true
    var HAS_PERMISSION =
        getInstance<Application>().checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Process.myPid(),
            Process.myUid()) == PackageManager.PERMISSION_GRANTED
}