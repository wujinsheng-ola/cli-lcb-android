package com.salton123.app

import android.app.Application
import android.content.Context
import com.salton123.app.initial.future.BaseAppDelegate

/**
 * User: newSalton@outlook.com
 * Date: 2019/5/9 17:50
 * ModifyTime: 17:50
 * Description:
 */
@Suppress("UNCHECKED_CAST")
open class BaseApplication : Application() {
    companion object {
        lateinit var sInstance: Application

        @JvmStatic
        fun <T : Application?> getInstance(): T {
            return sInstance as T
        }
    }

    lateinit var mBaseAppDelegate: BaseAppDelegate
    override fun attachBaseContext(base: Context?) {
        sInstance = this
        super.attachBaseContext(base)
        mBaseAppDelegate = getAppDelegate()
    }

    open fun getAppDelegate(): BaseAppDelegate {
        return BaseAppDelegate()
    }

    override fun onCreate() {
        super.onCreate()
        mBaseAppDelegate.onCreate()
    }
}