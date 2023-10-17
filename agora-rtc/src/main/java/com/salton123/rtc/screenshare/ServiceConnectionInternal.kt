package com.salton123.rtc.screenshare

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.salton123.rtc.tag

/**
 * User: wujinsheng1@yy.com
 * Date: 2020/5/29 11:48
 * ModifyTime: 11:48
 * Description:
 */
class ServiceConnectionInternal(
    var callback: (
        isConnected: Boolean,
        service: IBinder?,
        connection: ServiceConnection?
    ) -> Unit
) : ServiceConnection {

    override fun onServiceDisconnected(name: ComponentName?) {
        Log.i(tag, "[onServiceDisconnected]")
        callback.invoke(true, null, self())
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.i(tag, "[onServiceConnected]")
        callback.invoke(true, service, self())
    }

    private fun self(): ServiceConnection {
        return this@ServiceConnectionInternal
    }
}