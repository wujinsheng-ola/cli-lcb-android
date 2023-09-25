package com.salton123.app.initial

import com.salton123.ClassPath
import com.salton123.log.LogConfig
import com.salton123.log.XLog

/**
 * Time:2021/6/24 17:32
 * Author:wujinsheng1
 * Description:
 */
object ToolKits {

    fun log(): ToolKits {
        XLog.config(LogConfig()
            .setDebugable(ClassPath.IS_DEBUG)
            .setSavePath(ClassPath.BASE_PATH))
        return this
    }

    fun mmkv(): ToolKits {
        return this
    }

    fun utils(): ToolKits {
        return this
    }
}