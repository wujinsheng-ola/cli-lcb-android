package com.salton123.eleph.dialog

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.CenterPopupView
import com.salton123.eleph.NetworkViewModel
import com.salton123.log.XLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kt.singleClick
import sg.partying.lcb.android.R
import sg.partying.lcb.android.Session
import sg.partying.lcb.android.api.apiService
import sg.partying.lcb.android.api.resp.Resp

/**
 * Time:2023/9/26 11:54
 * Author:
 * Description:
 */
class PhoneLoginDialog(context: Context) : CenterPopupView(context) {
    private val TAG = "PhoneLoginDialog"
    companion object {
        fun show(activity: Context, confirmListener: (() -> Unit)?) {
            val contentView = PhoneLoginDialog(activity)
            contentView.confirmListener = confirmListener
            XPopup.Builder(activity)
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(true)
                .autoDismiss(false)
                .hasShadowBg(true)
                .isLightStatusBar(true)
                .asCustom(contentView)
                .show()
        }
    }

    override fun getImplLayoutId(): Int = R.layout.dialog_phone_login
    var confirmListener: (() -> Unit)? = null
    override fun init() {
        super.init()
        val tvNext = findViewById<TextView>(R.id.tvNext)
        val llPassword = findViewById<LinearLayout>(R.id.llPassword)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)
        val etMobile = findViewById<EditText>(R.id.etMobile)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        tvNext.singleClick {
            llPassword.visibility = View.VISIBLE
        }
        tvLogin.singleClick {
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
//                    val mobile = etMobile.text.toString().trim()
//                    val password = etPassword.text.toString().trim()
                    val mobile = "18688367977"
                    val password = "3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d"
                    val area = "86"
                    val ret = apiService.passwordLogin(mobile, password, area, "", "")
                    if (ret.success){
                        XLog.i(TAG,"${ret.data}")
                        Session.uid = ret.data.uid
                    }
                }
            }

        }
    }
}