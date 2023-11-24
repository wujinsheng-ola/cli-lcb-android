package sg.partying.lcb.profile.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.salton123.base.ViewBindingActivity
import com.salton123.profile.databinding.ActivityAccountSettingBinding
import com.salton123.soulove.Constants
import com.salton123.soulove.api.RouterManager
import kt.singleClick
import sg.partying.lcb.AppProperty
import sg.partying.lcb.android.Session
import sg.partying.lcb.util.ImageLoader

/**
 * Time:2023/11/20 17:54
 * Author:
 * Description:
 */
@Route(path = Constants.Router.Profile.ACCOUNT_SETTING)
class AccountSettingActivity : ViewBindingActivity<ActivityAccountSettingBinding>() {
    override fun getViewBinding(): ActivityAccountSettingBinding = ActivityAccountSettingBinding.inflate(layoutInflater)
    override fun initViewAndData() {
        viewBind.apply {
            tvName.text = Session.name.ifEmpty { "未登录" }
            tvUid.text = "${Session.uid}"
            ImageLoader.loadCenterCrop(ivAvatar, AppProperty.RESOURCE_PREFIX_URL + Session.icon)
            btnLogout.singleClick {
                Session.uid = 0
                Session.token = ""
                activity()?.finish()
                RouterManager.goLogin(activity())
            }
        }
    }
}