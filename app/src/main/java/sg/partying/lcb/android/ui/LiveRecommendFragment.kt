package sg.partying.lcb.android.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.salton123.base.BaseFragment
import com.salton123.eleph.NetworkViewModel
import com.salton123.log.XLog
import com.salton123.utils.ScreenUtils
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.tmall.ultraviewpager.UltraViewPager
import kotlinx.coroutines.launch
import kt.toast
import me.hgj.jetpackmvvm.state.ResultState
import sg.partying.lcb.android.R
import sg.partying.lcb.android.ui.adapter.UltraPagerAdapter

class LiveRecommendFragment : BaseFragment(), OnRefreshLoadMoreListener {
    private val TAG = "LiveRecommendFragment"
    private val viewModel by viewModels<NetworkViewModel>()
    private lateinit var refreshLayout: SmartRefreshLayout
    private val ultraPagerAdapter by lazy { UltraPagerAdapter() }
    private lateinit var ultraViewPager: UltraViewPager
    override fun getLayout(): Int = R.layout.fragment_live_recommend

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
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
            if (it is ResultState.Success) {
                ultraPagerAdapter.update(it.data.data)
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

            }
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        refreshLayout.finishRefresh(0)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        refreshLayout.finishLoadMore(0)
    }

}