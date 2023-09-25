package com.salton123.app.initial.future

import android.util.Log
import com.salton123.app.initial.ToolKits
import com.salton123.utils.CommonUtils
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Time:2021/6/18 18:36
 * Author:wujinsheng1
 * Description:
 */
open class FutureTaskAppDelegate : IFutureTaskPriority {
    //只有高优先级的任务需要和主线程保持同步，其他线程的任务异步处理，加快初始化过程。
    private val mCountDownLatch = CountDownLatch(3)

    fun onCreate() {
        try {
            if (CommonUtils.isMainProcess()) {
                //主进程
                runOnMainProcessMainThread()
                FutureTaskLoader().init(this, mCountDownLatch)
                mCountDownLatch.await(1500, TimeUnit.MILLISECONDS)
            } else {
                runOnAllProcessMainThread()
            }
        } catch (ignore: Exception) {
            ignore.fillInStackTrace()
            Log.e("FutureTaskApplication", ignore.toString())
        }
    }

    open fun runOnAllProcessMainThread() {
    }

    open fun runOnMainProcessMainThread() {
        initSdk()
    }

    open fun initSdk() {
        ToolKits
            .log()
            .mmkv()
            .utils()
    }

    override fun highPriority() {
    }

    override fun mediumPriority() {
    }

    override fun lowPriority() {
    }
}