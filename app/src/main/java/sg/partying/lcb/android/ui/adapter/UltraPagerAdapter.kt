package sg.partying.lcb.android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import sg.partying.lcb.android.R
import sg.partying.lcb.android.util.ImageLoader
import sg.partying.lcb.api.resp.BannerItem

/**
 * User: newSalton@outlook.com
 * Date: 2019/9/26 10:44
 * ModifyTime: 10:44
 * Description:
 */
interface ImageClick {
    fun onClick(position: Int, item: BannerItem)
}

class UltraPagerAdapter : PagerAdapter() {
    private var mDatas: MutableList<BannerItem> = mutableListOf()
    fun update(datas: MutableList<BannerItem>) {
        mDatas.clear()
        mDatas.addAll(datas)
        notifyDataSetChanged()
    }

    var itemImageClick: ((position: Int, item: BannerItem) -> Unit)? = null
    override fun getCount(): Int {
        return mDatas.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val viewGroup = LayoutInflater.from(container.context).inflate(R.layout.banner_item, null) as ViewGroup
        val imageView = viewGroup.findViewById<ImageView>(R.id.bannerImage)
        imageView.setOnClickListener { view: View? ->
            itemImageClick?.invoke(position, mDatas[position])
        }
        ImageLoader.loadFitCenter(imageView, mDatas[position].image)
        container.addView(viewGroup)
        return viewGroup
    }

    override fun destroyItem(container: ViewGroup, position: Int, item: Any) {
        val view = item as View
        container.removeView(view)
    }
}
