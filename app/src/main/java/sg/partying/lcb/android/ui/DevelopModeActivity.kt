package sg.partying.lcb.android.ui

import android.widget.RadioButton
import android.widget.RadioGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.salton123.config.AppModeEnum
import com.salton123.soulove.CommonClassPath
import com.salton123.soulove.Constants
import sg.partying.lcb.android.R
import sg.partying.lcb.base.BaseTitleActivity

@Route(path = Constants.Router.App.DEV)
class DevelopModeActivity : BaseTitleActivity() {
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
