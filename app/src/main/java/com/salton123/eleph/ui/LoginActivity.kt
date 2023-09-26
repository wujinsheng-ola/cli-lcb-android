package com.salton123.eleph.ui

import android.os.Bundle
import android.view.View
import com.salton123.base.DelegateActivity
import com.salton123.base.feature.ImmersionFeature
import com.salton123.eleph.dialog.PhoneLoginDialog
import sg.partying.lcb.android.R

class LoginActivity : DelegateActivity() {
    lateinit var phoneLogin: View
    override fun getLayout(): Int = R.layout.activity_login

    override fun initVariable(savedInstanceState: Bundle?) {
        addFeature(ImmersionFeature(this))
    }

    override fun initViewAndData() {
        phoneLogin = f(R.id.phoneLogin)
        phoneLogin.setOnClickListener {
            PhoneLoginDialog.show(activity()) {

            }
        }
    }

}
