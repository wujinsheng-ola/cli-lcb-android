package com.salton123.utils.task

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper

/**
 * Time:2022/3/1 10:22 上午
 * Author:
 * Description:
 */
object ThreadQueue {
    private val handlerThread: HandlerThread = HandlerThread("squeezer-thread-queue")
    private val handler: Handler
    private val mainHanlder: Handler = Handler(Looper.getMainLooper())
    private val sDefaultExecutor = PriorityExecutor(true)

    init {
        handlerThread.apply {
            isDaemon = true
            start()
            handler = Handler(handlerThread.looper)
        }
    }

    fun post(runnable: Runnable) {
        handler.post(runnable)
    }

    fun postDelay(runnable: Runnable, timeMills: Long) {
        handler.postDelayed(runnable, timeMills)
    }

    fun postOnUi(runnable: Runnable) {
        mainHanlder.post(runnable)
    }

    fun postOnUiDelay(runnable: Runnable, timeMills: Long) {
        mainHanlder.postDelayed(runnable, timeMills)
    }

    fun execOnCache(runnable: Runnable) {
        sDefaultExecutor.execute(runnable)
    }
}