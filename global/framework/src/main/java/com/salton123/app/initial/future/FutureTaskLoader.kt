package com.salton123.app.initial.future

import java.util.concurrent.CountDownLatch

/**
 * Time:2021/6/18 18:20
 * Author:wujinsheng1
 * Description:
 */
class FutureTaskLoader {
    companion object {
        private const val FUTURE_PRIORITY_HIGH = 10
        private const val FUTURE_PRIORITY_MEDIUM = 8
        private const val FUTURE_PRIORITY_LOW = 5
    }

    /**
     * 异步初始化任务
     */
    fun init(priority: IFutureTaskPriority, latch: CountDownLatch) {
        Thread(
            Runnable {
                priority.highPriority()
                latch.countDown()
            }, "future-hight"
        ).apply {
            isDaemon = false
            setPriority(FUTURE_PRIORITY_HIGH)
        }.start()

        Thread(
            Runnable {
                priority.mediumPriority()
                latch.countDown()
            }, "future-medium"
        ).apply {
            isDaemon = true
            setPriority(FUTURE_PRIORITY_MEDIUM)
        }.start()

        Thread(
            Runnable {
                priority.lowPriority()
                latch.countDown()
            }, "future-low"
        ).apply {
            isDaemon = true
            setPriority(FUTURE_PRIORITY_LOW)
        }.start()
    }
}