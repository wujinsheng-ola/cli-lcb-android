package com.salton123.eleph.ui

import android.widget.RadioButton
import android.widget.RadioGroup
import com.salton123.base.BaseActivity
import com.salton123.config.AppModeEnum
import com.salton123.soulove.CommonClassPath
import sg.partying.lcb.android.R

class DevelopModeActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.avtivity_develop_mode

    private lateinit var radioGroup: RadioGroup
    private lateinit var rbProduct: RadioButton
    private lateinit var rbAlpha: RadioButton
    private lateinit var rbDebug: RadioButton
    override fun initViewAndData() {
        super.initViewAndData()
        radioGroup = f(R.id.radioGroup)
        rbProduct = f(R.id.rbProduct)
        rbAlpha = f(R.id.rbAlpha)
        rbDebug = f(R.id.rbDebug)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbProduct -> {
                    CommonClassPath.appMode = AppModeEnum.Product
                }

                R.id.rbAlpha -> {
                    CommonClassPath.appMode = AppModeEnum.Alpha
                }

                else -> {
                    CommonClassPath.appMode = AppModeEnum.Debug
                }
            }
        }
        when (CommonClassPath.appMode) {
            AppModeEnum.Product -> {
                radioGroup.check(R.id.rbProduct)
            }

            AppModeEnum.Alpha -> {
                radioGroup.check(R.id.rbAlpha)
            }

            AppModeEnum.Debug -> {
                radioGroup.check(R.id.rbDebug)
            }

            else -> {
                radioGroup.check(R.id.rbDebug)
            }
        }

    }
}
