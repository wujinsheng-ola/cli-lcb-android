package sg.partying.lcb.android.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sg.partying.lcb.android.R
import sg.partying.lcb.android.model.IMultiType
import sg.partying.lcb.android.model.LiveRecommendContent
import sg.partying.lcb.android.model.TYPE_LIVE_RECOMMEND_CONTENT
import sg.partying.lcb.android.ui.adapter.holder.SectionContentViewHolder

/**
 * Time:2022/3/25 8:26 下午
 * Author:
 * Description:
 */
class RecommendInfoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var dataList: MutableList<IMultiType> = mutableListOf()
        private set
    var onItemClick: ((type: IMultiType, position: Int) -> Unit)? = null

    fun setData(dataList: MutableList<IMultiType>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LIVE_RECOMMEND_CONTENT -> {
                SectionContentViewHolder(View.inflate(parent.context, R.layout.adapter_item_live_recomemd, null))
            }

            else -> {
                SectionContentViewHolder(View.inflate(parent.context, viewType, null))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mutableType = dataList[position]
        if (mutableType is LiveRecommendContent) {
            if (holder is SectionContentViewHolder) {
                holder.updateUi(mutableType)
            }
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(mutableType, position) ?: run {
            }
        }
    }

    override fun getItemCount(): Int = dataList.size
}
