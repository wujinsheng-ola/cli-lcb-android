package com.salton123.eleph.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.TimeUtils
import com.salton123.base.DelegateActivity
import com.salton123.base.feature.ImmersionFeature
import com.salton123.base.feature.PermissionFeature
import com.salton123.eleph.R
import com.salton123.log.XLog
import com.salton123.utils.FragmentUtils
import com.salton123.utils.PreferencesUtils
import com.salton123.widget.BottomBarLayout
import kt.toast

/**
 * Time:2022/1/29 11:40 上午
 * Author:
 * Description:
 */
class HomeActivity : DelegateActivity() {

    override fun getLayout(): Int = R.layout.activity_home
    private val mHomeComp by lazy { HelloFragment() }
    private val mGalleryComp by lazy { HelloFragment() }
    private lateinit var bottomBarLayout: BottomBarLayout
    private lateinit var mainContent: FrameLayout
    private val mData = arrayOf<Fragment>(mHomeComp, mGalleryComp)

    override fun initVariable(savedInstanceState: Bundle?) {
        addFeature(ImmersionFeature(this))
        addFeature(PermissionFeature {
            if (it) {

            } else {
                toast("please grant all permissions")
                AppUtils.launchAppDetailsSettings()
            }
        })
    }

    @SuppressLint("CheckResult")
    override fun initViewAndData() {
        bottomBarLayout = f(R.id.bottomBarLayout)
        mainContent = f(R.id.mainContent)
        mData.forEachIndexed { index, fragment ->
            FragmentUtils.addOrReplaceFragment(supportFragmentManager, R.id.mainContent, fragment)
            FragmentUtils.hide(supportFragmentManager, fragment)
        }
        FragmentUtils.show(supportFragmentManager, mData[0])
        bottomBarLayout.setOnItemSelectedListener { _, _, currentPosition ->
            mData.forEachIndexed { index, fragment ->
                FragmentUtils.hide(supportFragmentManager, fragment)
            }
            FragmentUtils.show(supportFragmentManager, mData[currentPosition])
        }

//        if (PreferencesUtils.getBoolean("SHOW_GDPR", true)) {
//            mainContent.postDelayed({
//                FragmentUtils.showDialogFragment(supportFragmentManager, GDPRPopupComp.newInstance(), "GDPRPopupComp")
//            }, 500)
//        }
//        Prop.changeTabLiveData.observe(this) {
//            bottomBarLayout.currentItem = it
//        }
//
//        mainContent.postDelayed({
//            showSurpriseBox()
//        }, 10000)
    }


//    private fun showSurpriseBox() {
//        var lastShowTime = PreferencesUtils.getLong("last_show_surprise_box_time", 0)
//        var showCount = PreferencesUtils.getLong("show_surprise_box_count", 0)
//        if (TimeUtils.isToday(lastShowTime) || lastShowTime == 0L) {
//            XLog.i(this, "[showSurpriseBox] isToday")
//            val currentTime = System.currentTimeMillis()
//            val interval = currentTime - lastShowTime
//            val isInterval = (currentTime - lastShowTime) > 1000 * 60 * 60
//            XLog.i(this, "[showSurpriseBox] showCount interval:$interval,isInterval:$isInterval,showCount:$showCount")
//            if (showCount <= 4 && isInterval) {
//                val comp = SurpriseBoxPopupComp()
//                FragmentUtils.showDialogFragment(supportFragmentManager, comp, "SurpriseBoxPopupComp")
//                PreferencesUtils.putLong("show_surprise_box_count", ++showCount)
//                lastShowTime = currentTime
//                PreferencesUtils.putLong("last_show_surprise_box_time", lastShowTime)
//            }
//        } else {
//            XLog.i(this, "[showSurpriseBox] not Today")
//            showCount = 0
//            lastShowTime = System.currentTimeMillis()
//            PreferencesUtils.putLong("show_surprise_box_count", showCount)
//            PreferencesUtils.putLong("last_show_surprise_box_time", lastShowTime)
//        }
//    }

    override fun onBackPressed() {
        moveTaskToBack(true)
//        val small = Intent(Intent.ACTION_MAIN)
//        small.addCategory(Intent.CATEGORY_HOME)
//        small.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(small)
    }

}