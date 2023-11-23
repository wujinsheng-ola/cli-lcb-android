package sg.partying.lcb.android.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.salton123.soulove.Constants
import sg.partying.lcb.android.R
import sg.partying.lcb.base.BaseTitleActivity

/**
 * Time:2023/11/20 17:54
 * Author:
 * Description:
 */
@Route(path = Constants.Router.Profile.ACCOUNT_SETTING)
class AccountSettingActivity : BaseTitleActivity() {
    override fun getLayoutId(): Int = R.layout.activity_contact_us


}