package sg.partying.lcb.android.ui

import android.os.Bundle
import com.salton123.base.DelegateActivity
import com.salton123.base.feature.ImmersionFeature
import com.salton123.utils.FragmentUtils
import kt.toast
import sg.partying.lcb.android.Prop
import sg.partying.lcb.android.R
import sg.partying.lcb.android.ui.fragment.LiveRoomFragment

class LiveRoomActivity : DelegateActivity() {

    override fun getLayout(): Int = R.layout.activity_video_live

    override fun initVariable(savedInstanceState: Bundle?) {
        addFeature(ImmersionFeature(this))
        if (Prop.currentRoomInfo == null) {
            toast("room info is invalid, please try again!")
            finish()
            return
        }
    }

    override fun initViewAndData() {
        FragmentUtils.add(supportFragmentManager, LiveRoomFragment(), R.id.liveLayer)
    }

}
