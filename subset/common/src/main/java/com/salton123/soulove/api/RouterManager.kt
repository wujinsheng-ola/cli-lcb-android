package com.salton123.soulove.api

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.salton123.soulove.Constants

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


    //
    fun goLiveRoom(context: Context) {
        Constants.Router.Live.LIVE_ROOM.goPage(context)
    }

    fun goLiveHostRoom(context: Context) {
        Constants.Router.Live.LIVE_HOST_ROOM.goPage(context)
    }

    fun goEditProfile(context: Context) {
        Constants.Router.Profile.EDIT.goPage(context)
    }

    fun goContactUs(context: Context) {
        Constants.Router.Profile.CONTACT_US.goPage(context)
    }

    fun goAccountSetting(context: Context) {
        Constants.Router.Profile.ACCOUNT_SETTING.goPage(context)
    }

    fun goHome(context: Context) {
        Constants.Router.App.HOME.goPage(context)
    }

    fun goDev(context: Context) {
        Constants.Router.App.DEV.goPage(context)
    }

    fun goLogin(context: Context) {
        Constants.Router.Profile.LOGIN.goPage(context)
    }

    fun goWeb(context: Context, bundle: Bundle) {
        ARouter.getInstance().build(Constants.Router.Profile.WEB)
            .with(bundle)
            .navigation(context)

    }
}