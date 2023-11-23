package sg.partying.lcb.profile.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.salton123.profile.R
import com.salton123.soulove.Constants
import sg.partying.lcb.base.BaseTitleActivity

/**
 * Time:2023/11/20 17:54
 * Author:
 * Description:
 */
@Route(path = Constants.Router.Profile.CONTACT_US)
class ContactUsActivity : BaseTitleActivity() {
    override fun getLayoutId(): Int = R.layout.activity_contact_us


    override fun initViewAndData() {
        super.initViewAndData()
        tvTitle.setText(R.string.contact_us)
    }
}