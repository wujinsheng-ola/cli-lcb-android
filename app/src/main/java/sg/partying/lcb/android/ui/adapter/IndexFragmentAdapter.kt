package sg.partying.lcb.android.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * User: newSalton@outlook.com
 * Date: 2017/8/14 15:42
 * ModifyTime: 15:42
 * Description:
 */
class IndexFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var mData: MutableList<Pair<Fragment, String>> = ArrayList()

    fun setData(data: MutableList<Pair<Fragment, String>>) {
        mData = data
        notifyDataSetChanged()
    }

    fun add(position: Int, item: Pair<Fragment, String>) {
        mData.add(position, item)
    }

    override fun getItem(position: Int): Fragment {
        return mData[position].first
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mData[position].second
    }
}
