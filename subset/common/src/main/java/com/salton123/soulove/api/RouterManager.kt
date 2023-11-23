package com.salton123.soulove.api

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.salton123.soulove.Constants
import com.salton123.soulove.api.RouterManager.goPage

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

    fun goEditProfile(context: Context) {
        Constants.Router.Profile.EDIT.goPage(context)
    }

    fun goContactUs(context: Context) {
        Constants.Router.Profile.CONTACT_US.goPage(context)
    }

    fun goAccountSetting(context: Context) {
        Constants.Router.Profile.ACCOUNT_SETTING.goPage(context)
    }
}