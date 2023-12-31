package sg.partying.lcb.live.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.salton123.base.DelegateActivity
import com.salton123.base.feature.ImmersionFeature
import com.salton123.live.R
import com.salton123.soulove.Constants
import com.salton123.utils.FragmentUtils
import kt.toast
import sg.partying.lcb.android.Prop
import sg.partying.lcb.live.ui.fragment.LiveRoomFragment

@Route(path = Constants.Router.Live.LIVE_ROOM)
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
