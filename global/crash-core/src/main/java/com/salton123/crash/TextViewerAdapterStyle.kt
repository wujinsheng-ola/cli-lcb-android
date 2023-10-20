package com.salton123.crash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kt.runOnUi

/**
 * @Time:2021/1/22 14:33
 * @Author:wujinsheng
 * @Description:
 */
class TextViewerAdapterStyle : RecyclerView.Adapter<TextViewerHolder>() {
    val mData: MutableList<String> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewerHolder {
        return TextViewerHolder(LayoutInflater.from(parent.context).inflate(R.layout.dk_item_text_content, null))
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: TextViewerHolder, position: Int) {
        holder.updateText(mData[position])
    }

    fun addItemAndNotify(msg: String) {
        mData.add(msg)
        runOnUi {
            notifyItemInserted(mData.size)
        }
    }
}

