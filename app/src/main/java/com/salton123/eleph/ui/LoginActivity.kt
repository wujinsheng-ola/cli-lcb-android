package com.salton123.eleph.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.salton123.app.BaseApplication
import com.salton123.base.DelegateActivity
import com.salton123.base.feature.ImmersionFeature
import com.salton123.eleph.NetworkViewModel
import com.salton123.eleph.dialog.PhoneLoginDialog
import kt.getString
import me.hgj.jetpackmvvm.state.ResultState
import sg.partying.lcb.android.R

class LoginActivity : DelegateActivity() {
    private val viewModel by viewModels<NetworkViewModel>()
    private lateinit var llLoginType: LinearLayout
    override fun getLayout(): Int = R.layout.activity_login

    override fun initVariable(savedInstanceState: Bundle?) {
        addFeature(ImmersionFeature(this))
    }

    override fun initViewAndData() {
        llLoginType = f(R.id.llLoginType)
        viewModel.loginOptions().observe(this) {
            if (it is ResultState.Success) {
                it.data.data.main
                it.data.data.sub.forEach { sub ->
                    when (sub) {
                        "google" -> {
                            val loginTypeView = LayoutInflater.from(BaseApplication.sInstance).inflate(R.layout.login_type_view_holder, null)
                            val ivLoginIcon = loginTypeView.findViewById<ImageView>(R.id.ivLoginIcon)
                            val tvLoginName = loginTypeView.findViewById<TextView>(R.id.tvLoginName)
                            ivLoginIcon.setImageResource(R.drawable.ic_google_login)
                            tvLoginName.text = String.format(R.string.login_via_type.getString(), "Google")
                            loginTypeView.setOnClickListener {
                            }
                            llLoginType.addView(loginTypeView)
                        }

                        "phone" -> {
                            val loginTypeView = LayoutInflater.from(BaseApplication.sInstance).inflate(R.layout.login_type_view_holder, null)
                            val ivLoginIcon = loginTypeView.findViewById<ImageView>(R.id.ivLoginIcon)
                            val tvLoginName = loginTypeView.findViewById<TextView>(R.id.tvLoginName)
                            ivLoginIcon.setImageResource(R.drawable.ic_phone_login)
                            tvLoginName.text = String.format(R.string.login_via_type.getString(), "Phone")
                            loginTypeView.setOnClickListener {
                                PhoneLoginDialog.show(activity()) {
                                }
                            }
                            llLoginType.addView(loginTypeView)
                        }
                    }
                }
            }
        }
    }

}
