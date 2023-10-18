package sg.partying.lcb.android.ui.fragment

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.SurfaceView
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import com.salton123.base.BaseFragment
import com.salton123.rtc.agora.AgoraFacade
import io.agora.rtc2.RtcEngine
import io.agora.rtc2.video.VideoCanvas
import sg.partying.lcb.android.Prop
import sg.partying.lcb.android.R
import sg.partying.lcb.android.Session
import sg.partying.lcb.android.api.resp.RoomInfo
import sg.partying.lcb.android.viewmodel.LiveRoomViewModel

/**
 * Time:2023/10/18 11:35
 * Author:
 * Description:
 */
class LiveRoomFragment : BaseFragment() {
    private val viewModel by viewModels<LiveRoomViewModel>()
    private lateinit var liveHoleFirst: FrameLayout
    private lateinit var liveHoleSecond: FrameLayout
    override fun getLayout(): Int = R.layout.fragment_live_room

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    private var roomInfo: RoomInfo? = null
    override fun initViewAndData() {
        liveHoleFirst = f(R.id.liveHoleFirst)
        liveHoleSecond = f(R.id.liveHoleSecond)
        roomInfo = Prop.currentRoomInfo
        roomInfo?.apply {
            viewModel.joinChannel(rtcToken, rid, Session.uid)
        }
        viewModel.firstRemoteVideoDecodedRet.observe(this) { dataSet ->
            dataSet.forEachIndexed { index, uid ->
                if (index == 0) {
                    val surfaceView = SurfaceView(activity())
                    AgoraFacade.rtcEngine.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, uid))
                    liveHoleFirst.removeAllViews()
                    liveHoleFirst.addView(surfaceView)
                } else if (index == 1) {
                    val surfaceView = SurfaceView(activity())
                    AgoraFacade.rtcEngine.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, uid))
                    liveHoleSecond.removeAllViews()
                    liveHoleSecond.addView(surfaceView)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}