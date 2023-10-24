package com.salton123.crash

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.salton123.crash.core.R

/**
 * @Time:2021/1/22 14:33
 * @Author:wujinsheng
 * @Description:
 */
class TextViewerHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val mTextView: TextView by lazy { view.findViewById<TextView>(R.id.text) }

    fun updateText(msg: String) {
        mTextView.text = msg
    }
}