package com.salton123.base.feature

import android.view.View
import android.view.ViewGroup
import androidx.asynclayoutinflater.view.AsyncLayoutInflater

/**
 * Time:2023/11/23 09:49
 * Author:
 * Description:
 */
abstract class TitleBarFeature(val parent: ViewGroup, val layoutId: Int) : IFeature {
    override fun onBind() {
        parent.removeAllViews()
        AsyncLayoutInflater(parent.context).inflate(layoutId,
            parent
        ) { view, _, _ ->
            onViewInited(view)
        }
    }

    abstract fun onViewInited(view: View)
    override fun onUnBind() {
    }

}