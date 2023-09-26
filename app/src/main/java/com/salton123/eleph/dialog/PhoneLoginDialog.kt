package com.salton123.eleph.dialog

import android.content.Context
import android.widget.TextView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.CenterPopupView
import sg.partying.lcb.android.R

/**
 * Time:2023/9/26 11:54
 * Author:
 * Description:
 */
class PhoneLoginDialog(context: Context) : CenterPopupView(context) {
    companion object {
        fun show(activity: Context, confirmListener: (() -> Unit)?) {
            val contentView = PhoneLoginDialog(activity)
            contentView.confirmListener = confirmListener
            XPopup.Builder(activity)
                .hasShadowBg(true)
                .isLightStatusBar(true)
                .asCustom(contentView)
                .show()
        }
    }


    override fun getImplLayoutId(): Int = R.layout.dialog_phone_login
    var confirmListener: (() -> Unit)? = null
    var cancelListener: (() -> Unit)? = null
    override fun init() {
        super.init()
//        val tvDialogTitle = findViewById<TextView>(R.id.tvDialogTitle)
//        tvDialogTitle.text = R.string.warning.getString()
//
//        val tvDialogContent = findViewById<TextView>(R.id.tvDialogContent)
//        tvDialogContent.text = R.string.clear_caches_tips.getString()
//
//        val tvCancel = findViewById<TextView>(R.id.tvCancel)
//        tvCancel.text = R.string.cancel.getString()
//        tvCancel.setOnClickListener {
//            cancelListener?.invoke()
//            dismiss()
//        }
//        val tvCommit = findViewById<TextView>(R.id.tvCommit)
//        tvCommit.text = R.string.ok.getString()
//        tvCommit.setOnClickListener {
//            confirmListener?.invoke()
//            dismiss()
//        }
    }
}