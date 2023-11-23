package com.salton123.base.feature

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.salton123.common.R

/**
 * Time:2023/11/23 09:49
 * Author:
 * Description:
 */
class CommonTitleBarFeature(parent: ViewGroup)
    : TitleBarFeature(parent, R.layout.common_title_bar) {
    lateinit var tvBack: TextView
    lateinit var tvMore: TextView
    lateinit var tvTitle: TextView
    override fun onViewInited(view: View) {
        tvBack = view.findViewById(R.id.tvBack)
        tvMore = view.findViewById(R.id.tvMore)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvBack.setOnClickListener {

        }
        tvMore.setOnClickListener {
        }
    }

}