package com.salton123.base

import android.view.View
import androidx.viewbinding.ViewBinding

/**
 * Time:2023/11/23 16:12
 * Author:
 * Description:
 */
abstract class ViewBindingFragment<VB : ViewBinding> : BaseFragment() {
    lateinit var viewBind: VB
    abstract fun getViewBinding(): VB

    override fun getContentView(): View {
        viewBind = lazy { getViewBinding() }.value
        return viewBind.root
    }

    override fun getLayout(): Int = 0
}