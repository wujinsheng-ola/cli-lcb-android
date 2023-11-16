package sg.partying.lcb.live.ui.fragment

import android.os.Bundle
import android.view.SurfaceView
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import com.blankj.utilcode.util.GsonUtils
import com.salton123.base.BaseFragment
import com.salton123.live.R
import com.salton123.rtc.agora.AgoraFacade
import com.salton123.soulove.api.ProviderManager
import com.salton123.utils.ScreenUtils
import io.agora.rtc2.video.VideoCanvas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kt.toast
import pb.ReqGiftConfig
import sg.olaparty.network.RequestCenter
import sg.olaparty.network.base.NetworkConfigProvider
import sg.partying.lcb.AppProperty
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
        viewModel.seatInfoRet.observe(this) { dataSet ->
            setupLiveView(dataSet)
        }
        lifecycleScope.launch(Dispatchers.IO) {
//            display->room\chat\video
            val ret = RequestCenter.liveRoomService.giftConfig(ReqGiftConfig("chat", roomInfo?.rid?.toInt()
                ?: 0))
            ret.data_.forEach {
                it.gifts.forEach {
                    val giftUrl = AppProperty.RESOURCE_PREFIX_URL + "https://xs-aws-proxy.starcloud.rocks/static/gift_big/${it.id}.mp4"
                    println("giftUrl:$giftUrl")
                }
            }
//            println(GsonUtils.toJson(ret))

            AppProperty.RESOURCE_PREFIX_URL + "static/gift_big/6216969.mp4"
//            https://xs-aws-proxy.starcloud.rocks/static/gift_big/10000408.mp4
//            "https://xs-aws-proxy.starcloud.rocks/static/commodity/c19103115300444.png"
//            "https://xs-aws-proxy.starcloud.rocks/static/gift_big/6216969.mp4"
//            "https://apc-admin-test.oss-cn-hangzhou.aliyuncs.com/static/gift_big/6216969.mp4"
//            "http://xs-image.oss-cn-hangzhou.aliyuncs.com/static/gift_big/6216969.mp4"
//            "https://xs-image.oss-cn-hangzhou.aliyuncs.com/static/gift_big/6216969.mp4"
//            https://apc-admin-test.oss-cn-hangzhou.aliyuncs.com/static/gift_big/10000680.mp4
//            https://apc-admin-test.oss-cn-hangzhou.aliyuncs.com/static/gift_big/10000408.mp4
//            https://xs-aws-proxy.starcloud.rocks/static/gift_big/10000408.mp4
//            https://apc-admin-test.oss-cn-hangzhou.aliyuncs.com/static/gift_big/6216969.mp4
        }
//        viewModel.isJoinedRoom()?.observe(this) {
//            if (!it) {
//                toast("进rtc房间失败，请重试")
//            }
//        }
    }

    private fun setupLiveView(dataSet: HashSet<Int>) {
        liveViewHolder.removeAllViews()
        when (dataSet.size) {
            1 -> {
                val surfaceView = SurfaceView(activity())
                ProviderManager.liveRoom()?.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, dataSet.first()))
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
                ProviderManager.liveRoom()?.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, dataSet.first()))
                linearLayout.addView(surfaceView)

                val surfaceView2 = SurfaceView(activity())
                val surfaceView2Params = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)
                surfaceView2Params.weight = 1f
                surfaceView2.layoutParams = surfaceView2Params
                ProviderManager.liveRoom()?.setupRemoteVideo(VideoCanvas(surfaceView2, VideoCanvas.RENDER_MODE_HIDDEN, dataSet.last()))
                linearLayout.addView(surfaceView2)
                liveViewHolder.removeAllViews()
                liveViewHolder.addView(linearLayout)
            }
        }
    }
}