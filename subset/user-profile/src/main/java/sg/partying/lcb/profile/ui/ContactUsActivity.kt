package sg.partying.lcb.profile.ui

import android.graphics.Paint
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.utils.PackageUtils
import com.blankj.utilcode.util.AppUtils
import com.salton123.base.ViewBindingActivity
import com.salton123.profile.R
import com.salton123.profile.databinding.ActivityContactUsBinding
import com.salton123.soulove.Constants
import com.salton123.soulove.api.RouterManager
import kt.getString
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
            tvVersion.text = "Version ${AppUtils.getAppVersionName()} (${AppUtils.getAppVersionCode()})"

            tvUserService.paint.flags = Paint.UNDERLINE_TEXT_FLAG
            tvUserService.paint.isAntiAlias = true
            tvUserService.setOnClickListener {
                RouterManager.goWeb(activity(), Bundle().apply {
                    putString("url", "https://www.salton123.com/terms-of-service")
                    putString("title", R.string.terms_of_services.getString())
                })
            }
            tvWebSite.paint.flags = Paint.UNDERLINE_TEXT_FLAG
            tvWebSite.paint.isAntiAlias = true
            tvWebSite.setOnClickListener {
                RouterManager.goWeb(activity(), Bundle().apply {
                    putString("url", "https://www.hellopartygo.com")
                    putString("title", R.string.official_website.getString())
                })
            }
            tvPrivatePolicy.paint.flags = Paint.UNDERLINE_TEXT_FLAG
            tvPrivatePolicy.paint.isAntiAlias = true
            tvPrivatePolicy.setOnClickListener {
                RouterManager.goWeb(activity(), Bundle().apply {
                    putString("url", "https://www.salton123.com/privacy-policy")
                    putString("title", R.string.privacy_policy.getString())
                })
            }
        }
        tvTitle?.setText(R.string.contact_us)
    }
}