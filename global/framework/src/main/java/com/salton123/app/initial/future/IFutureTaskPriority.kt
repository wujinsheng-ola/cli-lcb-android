package com.salton123.app.initial.future

/**
 * Time:2021/6/18 18:19
 * Author:wujinsheng1
 * Description:task priority
 */
const val TAG = "IFutureTaskPriority"

interface IFutureTaskPriority {

    fun highPriority()

    fun mediumPriority()

    fun lowPriority()
}