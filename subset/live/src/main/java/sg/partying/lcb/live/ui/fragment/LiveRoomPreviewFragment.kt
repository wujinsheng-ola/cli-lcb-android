package sg.partying.lcb.live.ui.fragment

import android.os.Bundle
import android.view.SurfaceView
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.qmuiteam.qmui.kotlin.onClick
import com.salton123.base.BaseFragment
import com.salton123.live.R
import com.salton123.rtc.agora.AgoraFacade
import com.salton123.soulove.api.ProviderManager
import com.salton123.utils.ScreenUtils
import io.agora.rtc2.video.VideoCanvas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kt.singleClick
import kt.toast
import pb.ReqGiftConfig
import sg.olaparty.network.RequestCenter
import sg.olaparty.network.base.NetworkConfigProvider
import sg.partying.lcb.AppProperty
import sg.partying.lcb.android.Prop
import sg.partying.lcb.android.Session
import sg.partying.lcb.live.viewmodel.LiveRoomPreviewViewModel
import sg.partying.lcb.model.RoomInfo
import sg.partying.lcb.live.viewmodel.LiveRoomViewModel

/**
 * Author:
 * Description: 预览页
 */
class LiveRoomPreviewFragment : BaseFragment() {
    private val viewModel by viewModels<LiveRoomPreviewViewModel>()
    private lateinit var startLive: TextView
    override fun getLayout(): Int = R.layout.fragment_live_room_preview

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    private var roomInfo: RoomInfo? = null
    override fun initViewAndData() {
        startLive = f(R.id.start_live)
        startLive.singleClick {
            ToastUtils.showShort("start live")
        }

    }
}