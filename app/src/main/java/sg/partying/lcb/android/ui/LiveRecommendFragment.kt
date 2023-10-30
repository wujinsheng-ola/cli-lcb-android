package sg.partying.lcb.android.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.salton123.base.BaseFragment
import com.salton123.coroutine.Ret
import com.salton123.log.XLog
import com.salton123.rtc.agora.AgoraFacade
import com.salton123.soulove.api.RouterManager
import com.salton123.utils.ScreenUtils
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.tmall.ultraviewpager.UltraViewPager
import com.zyyoona7.itemdecoration.provider.GridItemDecoration
import sg.olaparty.network.viewmodel.NetworkViewModel
import sg.partying.lcb.android.Prop
import sg.partying.lcb.android.R
import sg.partying.lcb.android.Session
import sg.partying.lcb.android.model.IMultiType
import sg.partying.lcb.android.model.LiveRecommendContent
import sg.partying.lcb.android.ui.adapter.RecommendInfoAdapter
import sg.partying.lcb.android.ui.adapter.UltraPagerAdapter
import sg.partying.lcb.model.RoomInfo

const val VIDEO_LIST_SPAN_COUNT = 2
const val VIDEO_LIST_DIVIDER_SIZE = 10f

class LiveRecommendFragment : BaseFragment(), OnRefreshLoadMoreListener {
//    companion object {
//        private const val TAG = "LiveRecommendFragment"
//    }

    private val viewModel by viewModels<NetworkViewModel>()
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var cardView: CardView
    private lateinit var recyclerView: RecyclerView
    private lateinit var recommendInfoAdapter: RecommendInfoAdapter
    private var dataList: MutableList<IMultiType> = mutableListOf()

    var page = 1
    private val ultraPagerAdapter by lazy { UltraPagerAdapter() }
    private lateinit var ultraViewPager: UltraViewPager
    override fun getLayout(): Int = R.layout.fragment_live_recommend

    override fun initVariable(savedInstanceState: Bundle?) {
        type = arguments?.getString("type") ?: "liveroom"
    }

    var type = "liveroom"
    override fun initViewAndData() {
        cardView = f(R.id.cardView)
        recyclerView = f(R.id.recyclerView)
        recommendInfoAdapter = RecommendInfoAdapter()
        recyclerView.adapter = recommendInfoAdapter
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        recommendInfoAdapter.setData(dataList)
        val gridLayoutManager = GridLayoutManager(activity, VIDEO_LIST_SPAN_COUNT)
        recyclerView.layoutManager = gridLayoutManager
        GridItemDecoration.Builder()
            .includeEdge()
            .asSpace()
            .dividerSize(ScreenUtils.dp2px(VIDEO_LIST_DIVIDER_SIZE))
            .build().addTo(recyclerView)

        refreshLayout = f(R.id.refreshLayout)
        refreshLayout.setEnableRefresh(true)
        refreshLayout.setEnableLoadMore(true)
        refreshLayout.setOnRefreshLoadMoreListener(this)
        ultraViewPager = f(R.id.ultraViewPager)
        ultraPagerAdapter.apply {
            itemImageClick = { position, colorBanner ->
                XLog.i(this, "colorBanner:$colorBanner")
            }
        }
        viewModel.recommendBanner(type).observe(this) {
            if (it is Ret.Success && it.value.data != null) {
                cardView.visibility = View.VISIBLE
                ultraPagerAdapter.update(it.value.data!!)
                ultraViewPager.apply {
                    setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL)
                    setMultiScreen(1f)
                    initIndicator()
                    indicator
                        .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                        .setFocusColor(Color.WHITE)
                        .setNormalColor(Color.parseColor("#99FFFFFF"))
                        .setMargin(0, 0, 0, ScreenUtils.dp2px(20f))
                        .setRadius(ScreenUtils.dp2px(3f))
                    indicator.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
                    indicator.build()
                    setInfiniteLoop(true)
                    setAutoScroll(2000)
                    adapter = ultraPagerAdapter
                }
            } else {
                cardView.visibility = View.GONE
            }
        }
        recommendInfoAdapter.onItemClick = { type, position ->
            if (type is LiveRecommendContent) {
                val content = type.liveRecommendRoomInfo
                Prop.currentRoomInfo = RoomInfo("agora", content.agoraToken, content.rid, content.password, content.uid)
                AgoraFacade.joinChannel(content.agoraToken, content.rid, Session.uid)
                RouterManager.goLiveRoom(activity())
            }
        }
        getData()
    }

    private fun getData(refresh: Boolean = true) {
//        viewModel.recommendLiveChatRoom(ReqFeedRoom(1, 20)).observe(this) {
//            if (it is Ret.Success) {
//                println(it.value)
//            } else {
//                it.toString()
//            }
//        }
        viewModel.getRecommend(page++, 100, type).observe(this) {
            if (it is Ret.Success && it.value.data != null) {
                val datas = it.value.data.liveRecommendRoomInfos.map { item ->
                    LiveRecommendContent(item)
                }
                if (refresh) {
                    page = 1
                    dataList.clear()
                }
                dataList.addAll(datas)
                recommendInfoAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        refreshLayout.finishRefresh(1000)
        getData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        refreshLayout.finishLoadMore(0)
        getData(false)
    }
}
