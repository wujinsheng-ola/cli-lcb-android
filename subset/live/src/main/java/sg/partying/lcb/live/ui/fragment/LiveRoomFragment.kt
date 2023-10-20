package sg.partying.lcb.live.ui.fragment

import android.os.Bundle
import android.view.SurfaceView
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import com.salton123.base.BaseFragment
import com.salton123.live.R
import com.salton123.rtc.agora.AgoraFacade
import com.salton123.utils.ScreenUtils
import io.agora.rtc2.video.VideoCanvas
import sg.partying.lcb.android.Prop
import sg.partying.lcb.android.Session
import sg.partying.lcb.model.RoomInfo
import sg.partying.lcb.live.viewmodel.LiveRoomViewModel

/**
 * Time:2023/10/18 11:35
 * Author:
 * Description:
 */
class LiveRoomFragment : BaseFragment() {
    private val viewModel by viewModels<LiveRoomViewModel>()
    private lateinit var liveViewHolder: FrameLayout
    override fun getLayout(): Int = R.layout.fragment_live_room

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    private var roomInfo: RoomInfo? = null
    override fun initViewAndData() {
        liveViewHolder = f(R.id.liveViewHolder)
        roomInfo = Prop.currentRoomInfo
        roomInfo?.apply {
            viewModel.joinChannel(rtcToken, rid, Session.uid)
        }
        viewModel.seatInfoRet.observe(this) { dataSet ->
            setupLiveView(dataSet)
        }
    }

    private fun setupLiveView(dataSet: HashSet<Int>) {
        liveViewHolder.removeAllViews()
        when (dataSet.size) {
            1 -> {
                val surfaceView = SurfaceView(activity())
                AgoraFacade.rtcEngine.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, dataSet.first()))
                liveViewHolder.removeAllViews()
                liveViewHolder.addView(surfaceView)
            }

            2 -> {
                val linearLayout = LinearLayout(activity())
                linearLayout.orientation = LinearLayout.HORIZONTAL
                val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(350f))
                layoutParams.topMargin = ScreenUtils.dp2px(150f)
                linearLayout.layoutParams = layoutParams

                val surfaceView = SurfaceView(activity())
                val surfaceViewParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)
                surfaceViewParams.weight = 1f
                surfaceView.layoutParams = surfaceViewParams
                AgoraFacade.rtcEngine.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, dataSet.first()))
                linearLayout.addView(surfaceView)

                val surfaceView2 = SurfaceView(activity())
                val surfaceView2Params = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)
                surfaceView2Params.weight = 1f
                surfaceView2.layoutParams = surfaceView2Params
                AgoraFacade.rtcEngine.setupRemoteVideo(VideoCanvas(surfaceView2, VideoCanvas.RENDER_MODE_HIDDEN, dataSet.last()))
                linearLayout.addView(surfaceView2)
                linearLayout.id = R.id.salton_id_title_layout
                linearLayout.tag = "hello"
                liveViewHolder.removeAllViews()
                liveViewHolder.addView(linearLayout)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}