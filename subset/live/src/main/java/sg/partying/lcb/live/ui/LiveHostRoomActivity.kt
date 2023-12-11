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
import sg.partying.lcb.live.ui.fragment.LiveRoomPreviewFragment

@Route(path = Constants.Router.Live.LIVE_HOST_ROOM)
class LiveHostRoomActivity : DelegateActivity() {

    override fun getLayout(): Int = R.layout.activity_video_live_host

    override fun initVariable(savedInstanceState: Bundle?) {
        addFeature(ImmersionFeature(this))
    }
    /// 主播页面逻辑：进房前检查，引擎初始化，是否是恢复直播，走进房逻辑，
    override fun initViewAndData() {
        //FragmentUtils.add(supportFragmentManager, LiveRoomFragment(), R.id.liveLayer)
        FragmentUtils.add(supportFragmentManager, LiveRoomPreviewFragment(), R.id.profileLayer)

    }

}
