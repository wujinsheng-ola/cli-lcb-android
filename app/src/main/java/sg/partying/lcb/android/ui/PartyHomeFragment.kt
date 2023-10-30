package sg.partying.lcb.android.ui

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.salton123.base.BaseFragment
import kt.getColor
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView
import sg.partying.lcb.android.R
import sg.partying.lcb.android.ui.adapter.IndexFragmentAdapter

class PartyHomeFragment : BaseFragment() {
    private val mIndexFragmentAdapter: IndexFragmentAdapter by lazy { IndexFragmentAdapter(childFragmentManager) }
    private val fragments: MutableList<Pair<Fragment, String>> = mutableListOf()
    private lateinit var mCommonNavigator: CommonNavigator
    private lateinit var fragmentPagerIndicator: MagicIndicator
    private lateinit var mAdapter: CommonNavigatorAdapter
    private lateinit var viewPager: ViewPager
    override fun getLayout(): Int = R.layout.fragment_party_home

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        fragments.add(
            Pair(
                LiveRecommendFragment().apply {
                    arguments = Bundle().apply {
                        putString("type", "liveroom")
                    }
                },
                getString(R.string.live)
            )
        )
        fragments.add(
            Pair(
                LiveRecommendFragment().apply {
                    arguments = Bundle().apply {
                        putString("type", "chatroom")
                    }
                },
                getString(R.string.chat)
            )
        )
        fragments.add(Pair(HelloFragment(), getString(R.string.relate)))
        fragmentPagerIndicator = f(R.id.fragmentPagerIndicator)
        viewPager = f(R.id.viewPager)
        mIndexFragmentAdapter.setData(fragments)
        viewPager.adapter = mIndexFragmentAdapter
        mCommonNavigator = CommonNavigator(context)
        mAdapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return fragments.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView? {
                if (index < fragments.size) {
                    val simplePagerTitleView = SimplePagerTitleView(getContext())
                    simplePagerTitleView.text = fragments[index].second
                    simplePagerTitleView.normalColor = R.color.white60.getColor()
                    simplePagerTitleView.selectedColor = R.color.white.getColor()
                    simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                    simplePagerTitleView.setOnClickListener { viewPager.currentItem = index }
                    return simplePagerTitleView
                }
                return null
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.lineWidth = UIUtil.dip2px(context, 15.0).toFloat()
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.yOffset = UIUtil.dip2px(context, 8.0).toFloat()
                indicator.setColors(R.color.white.getColor(), R.color.white.getColor())
                return indicator
            }
        }
        mCommonNavigator.adapter = mAdapter
        fragmentPagerIndicator.navigator = mCommonNavigator
        ViewPagerHelper.bind(fragmentPagerIndicator, viewPager)
    }
}
