package com.salton123.eleph.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.salton123.base.DelegateActivity
import com.salton123.base.feature.ImmersionFeature
import com.salton123.eleph.NetworkViewModel
import sg.partying.lcb.android.R

class LoginActivity : DelegateActivity() {
    private val viewModel by viewModels<NetworkViewModel>()
    lateinit var phoneLogin: View
    override fun getLayout(): Int = R.layout.activity_login

    override fun initVariable(savedInstanceState: Bundle?) {
        addFeature(ImmersionFeature(this))
    }

    override fun initViewAndData() {
        phoneLogin = f(R.id.phoneLogin)
        phoneLogin.setOnClickListener {
            viewModel.loginOptions()
//            PhoneLoginDialog.show(activity()) {
//
//            }
        }
    }

}
