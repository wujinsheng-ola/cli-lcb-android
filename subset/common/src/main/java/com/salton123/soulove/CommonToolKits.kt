package com.salton123.soulove

import com.alibaba.android.arouter.launcher.ARouter
import com.qw.soul.permission.SoulPermission
import com.salton123.app.BaseApplication
import com.salton123.common.BuildConfig
import com.salton123.common.R

import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta

/**
 * Time:2021/6/24 17:32
 * Author:wujinsheng1
 * Description:
 */
object CommonToolKits {
    private var IS_DEBUG = BuildConfig.DEBUG

    fun bugly(): CommonToolKits {
        Beta.largeIconId = R.drawable.ic_launcher
        Beta.smallIconId = R.drawable.ic_launcher
        Beta.canNotifyUserRestart = IS_DEBUG
        Bugly.setIsDevelopmentDevice(BaseApplication.sInstance, IS_DEBUG)
        Bugly.init(BaseApplication.sInstance, CommonClassPath.BUGLY_APP_ID, IS_DEBUG)
        return this
    }

    fun permission(): CommonToolKits {
        SoulPermission.init(BaseApplication.sInstance)
        return this
    }

    /**
     *  这两行必须写在init之前，否则这些配置在init过程中将无效
     *  打印日志
     *  开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
     *  推荐在Application中初始化
     */
    fun arouter(): CommonToolKits {
        if (IS_DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(BaseApplication.sInstance)
        return this
    }
}