package sg.partying.lcb.profile.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.salton123.base.BaseViewBindingActivity
import com.salton123.profile.databinding.ActivityContactUsBinding
import com.salton123.soulove.Constants
import kt.singleClick
import kt.toast

/**
 * Time:2023/11/20 17:54
 * Author:
 * Description:
 */
@Route(path = Constants.Router.Profile.CONTACT_US)
class ContactUsActivity : BaseViewBindingActivity<ActivityContactUsBinding>() {
    override fun getViewBinding() = ActivityContactUsBinding.inflate(layoutInflater)
    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        viewBind.apply {
            ivIcon.singleClick { toast("hello") }
        }
    }
}