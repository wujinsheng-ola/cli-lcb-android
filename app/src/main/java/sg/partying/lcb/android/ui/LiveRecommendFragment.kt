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
import com.salton123.eleph.NetworkViewModel
import com.salton123.log.XLog
import com.salton123.utils.ScreenUtils
import com.salton123.widget.itemdecoration.provider.GridItemDecoration
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.tmall.ultraviewpager.UltraViewPager
import me.hgj.jetpackmvvm.state.ResultState
import sg.partying.lcb.android.R
import sg.partying.lcb.android.model.IMultiType
import sg.partying.lcb.android.model.LiveRecommendContent
import sg.partying.lcb.android.ui.adapter.UltraPagerAdapter
import sg.partying.lcb.android.ui.adapter.RecommendInfoAdapter

const val VIDEO_LIST_SPAN_COUNT = 2
const val VIDEO_LIST_DIVIDER_SIZE = 10f

class LiveRecommendFragment : BaseFragment(), OnRefreshLoadMoreListener {
    private val TAG = "LiveRecommendFragment"
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
    }

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
        refreshLayout.setEnableLoadMore(false)
        refreshLayout.setOnRefreshLoadMoreListener(this)
        ultraViewPager = f(R.id.ultraViewPager)
        ultraPagerAdapter.apply {
            itemImageClick = { position, colorBanner ->
                XLog.i(this, "colorBanner:$colorBanner")
            }
        }
        viewModel.videoLiveFeed().observe(this) {
            if (it is ResultState.Success && it.data.data != null) {
                cardView.visibility = View.VISIBLE
                ultraPagerAdapter.update(it.data.data!!)
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
        getData()
    }

    private fun getData() {
//        viewModel.test()
        viewModel.getRecommend().observe(this) {
            if (it is ResultState.Success && it.data.data != null) {
                val datas = it.data.data.liveRecommendRoomInfos.map { item ->
                    LiveRecommendContent(item)
                }
                dataList.clear()
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
        refreshLayout.finishLoadMore(1000)
    }

}