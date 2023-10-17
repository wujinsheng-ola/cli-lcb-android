package com.salton123.soulove.api

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Time:6/26/21 7:02 PM
 * Author:
 * Description:
 */
object RouterManager {
    private fun String.buildPage(context: Context): Fragment? {
        return ARouter.getInstance()
            .build(this)
            .navigation(context) as Fragment?
    }

    private fun String.goPage(context: Context) {
        ARouter.getInstance().build(this).navigation(context)
    }

    @JvmStatic
    fun loginMain(context: Context) {

    }
//
//    fun fundationHomePage(context: Context): Fragment? {
//        return Constants.Router.Fundation.MAIN.buildPage(context)
//    }
}