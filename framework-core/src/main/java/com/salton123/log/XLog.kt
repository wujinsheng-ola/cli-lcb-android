package com.salton123.log

import android.util.Log

/**
 * Time:2022/1/11 15:54
 * Author:
 * Description:
 */
object XLog {
    private var sConfig = LogConfig()


    @JvmStatic
    fun config(config: LogConfig) {
        sConfig = config

        Log.i("salton", "init xlog:$sConfig")
    }

    fun maxFileSize(): Long {
        return (sConfig.logDefaultSplitSize * 1024 * 1024).toLong()
    }

    fun maxAliveTime(): Long {
        return (sConfig.logDeleteDelayDay * 24 * 60 * 60).toLong()
    }

    /**
     * print log
     */
    @JvmStatic
    fun v(tag: Any, msg: String) {
        val line = Utils.callerLineNumber
        val fileName = Utils.callerFilename
        val methodName = Utils.callerMethodName
        val logText = Utils.msgForTextLog(tag, fileName, line, msg, methodName)
        if (sConfig.isDebugable) {
            Log.v(objClassName(tag), logText)
        }
    }

    @JvmStatic
    fun d(tag: Any, msg: String) {
        val line = Utils.callerLineNumber
        val fileName = Utils.callerFilename
        val methodName = Utils.callerMethodName
        val logText = Utils.msgForTextLog(tag, fileName, line, msg, methodName)
        if (sConfig.isDebugable) {
            Log.d(objClassName(tag), logText)
        }
    }

    @JvmStatic
    fun i(tag: Any, msg: String) {
        val line = Utils.callerLineNumber
        val fileName = Utils.callerFilename
        val methodName = Utils.callerMethodName
        val logText = Utils.msgForTextLog(tag, fileName, line, msg, methodName)
        if (sConfig.isDebugable) {
            Log.i(objClassName(tag), logText)
        }
    }

    @JvmStatic
    fun w(tag: Any, msg: String) {
        val line = Utils.callerLineNumber
        val fileName = Utils.callerFilename
        val methodName = Utils.callerMethodName
        val logText = Utils.msgForTextLog(tag, fileName, line, msg, methodName)
        if (sConfig.isDebugable) {
            Log.w(objClassName(tag), logText)
        }
    }

    @JvmStatic
    fun e(tag: Any, msg: String) {
        val line = Utils.callerLineNumber
        val fileName = Utils.callerFilename
        val methodName = Utils.callerMethodName
        val logText = Utils.msgForTextLog(tag, fileName, line, msg, methodName)
        if (sConfig.isDebugable) {
            Log.e(objClassName(tag), logText)
        }
    }

    /**
     * Returns the obj`s classname
     */
    private fun objClassName(obj: Any): String? {
        return if (obj is String) {
            sConfig.prefix + "-" + obj
        } else {
            sConfig.prefix + "-" + obj.javaClass.toString()
        }
    }
}