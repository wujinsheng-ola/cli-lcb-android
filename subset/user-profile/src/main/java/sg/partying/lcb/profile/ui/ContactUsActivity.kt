package sg.partying.lcb.profile.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.salton123.base.ViewBindingActivity
import com.salton123.profile.R
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
class ContactUsActivity : ViewBindingActivity<ActivityContactUsBinding>() {
    override fun getViewBinding() = ActivityContactUsBinding.inflate(layoutInflater)
    override fun initViewAndData() {
        viewBind.apply {
            ivIcon.singleClick { toast("hello") }
        }
        tvTitle?.setText(R.string.contact_us)
    }
}