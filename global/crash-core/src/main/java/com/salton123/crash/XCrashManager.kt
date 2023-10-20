package com.salton123.crash

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.salton123.crash.TextViewerActivity.Companion.FILE_KEY

import xcrash.XCrash
import java.io.File

/**
 * User: wujinsheng1@yy.com
 * Date: 2020/4/28 14:40
 * ModifyTime: 14:40
 * Description:
 */
object XCrashManager {
    val TAG = "XCrashManager"

    fun init(app: Application) {
        val params = XCrash.InitParameters()
        params.enableAnrCrashHandler()
        params.enableJavaCrashHandler()
        params.enableNativeCrashHandler()
        params.setAppVersion("1.0.0")
//        params.setLogDir(app.getExternalFilesDir("crash")?.absolutePath ?: "")
        params.setLogDir(app.cacheDir?.absolutePath ?: "")
        params.setAnrCallback { logPath, emergency ->
//            Toast.makeText(app, "检测到Anr:$logPath", Toast.LENGTH_LONG).show()
            Log.e(TAG, "检测到Anr:$logPath\n $emergency")
            openActivity(app, logPath)
        }
        params.setJavaCallback { logPath, emergency ->
//            Toast.makeText(app, "检测到Crash:$logPath", Toast.LENGTH_LONG).show()
            Log.e(TAG, "检测到Crash:$logPath\n $emergency")
            openActivity(app, logPath)
        }
        params.setNativeCallback { logPath, emergency ->
//            Toast.makeText(app, "检测到Native Crash:$logPath", Toast.LENGTH_LONG).show()
            Log.e(TAG, "检测到Native Crash:$logPath\n $emergency")
            openActivity(app, logPath)
        }
        XCrash.init(app, params)
    }

    private fun openActivity(context: Context, logPath: String) {
        val bundle = Bundle()
        bundle.putSerializable(FILE_KEY, File(logPath))
        val crashIntent = Intent(context, TextViewerActivity::class.java)
        crashIntent.putExtras(bundle)
        crashIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(crashIntent)
    }
}