package com.salton123.base

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.salton123.base.feature.ImmersionFeature
import com.salton123.common.R
import kt.singleClick

/**
 * Time:2023/11/23 16:12
 * Author:
 * Description:
 */
abstract class ViewBindingActivity<VB : ViewBinding> : DelegateActivity() {
    lateinit var viewBind: VB
    abstract fun getViewBinding(): VB

    override fun getContentView(): View {
        viewBind = lazy { getViewBinding() }.value
        return viewBind.root
    }
    
    override fun initVariable(savedInstanceState: Bundle?) {
        addFeature(ImmersionFeature(this))
    }

    override fun getLayout(): Int = 0
    open var tvBack: TextView? = null
    open var tvMore: TextView? = null
    open var tvTitle: TextView? = null
    override fun getTitleBar(): View {
        val titleView = layoutInflater.inflate(R.layout.common_title_bar, null)
        tvTitle = titleView.findViewById(R.id.tvTitle)
        tvMore = titleView.findViewById(R.id.tvMore)
        tvBack = titleView.findViewById(R.id.tvBack)
        tvBack?.singleClick { finish() }
        return titleView
    }
}