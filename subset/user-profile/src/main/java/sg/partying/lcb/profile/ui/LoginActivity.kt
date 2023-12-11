package sg.partying.lcb.profile.ui

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.salton123.app.BaseApplication
import com.salton123.base.DelegateActivity
import com.salton123.base.feature.ImmersionFeature
import com.salton123.config.AppModeEnum
import com.salton123.coroutine.Ret
import com.salton123.profile.R
import com.salton123.soulove.CommonClassPath
import com.salton123.soulove.Constants
import com.salton123.soulove.api.RouterManager
import kt.getString
import kt.singleClick
import sg.olaparty.network.viewmodel.NetworkViewModel
import sg.partying.lcb.profile.dialog.PhoneLoginDialog

@Route(path = Constants.Router.Profile.LOGIN)
class LoginActivity : DelegateActivity() {
    private val viewModel by viewModels<NetworkViewModel>()
    private lateinit var llLoginType: LinearLayout
    private lateinit var tvUserService: TextView
    private lateinit var tvDevelopMode: TextView
    private lateinit var tvPrivatePolicy: TextView
    override fun getLayout(): Int = R.layout.activity_login

    override fun initVariable(savedInstanceState: Bundle?) {
        addFeature(ImmersionFeature(this))
    }

    override fun initViewAndData() {
        llLoginType = f(R.id.llLoginType)
        tvUserService = f(R.id.tvUserService)
        tvPrivatePolicy = f(R.id.tvPrivatePolicy)
        tvDevelopMode = f(R.id.tvDevelopMode)
        when (CommonClassPath.appMode) {
            AppModeEnum.Debug -> {
                tvDevelopMode.text = "Debug模式"
            }

            AppModeEnum.Alpha -> {
                tvDevelopMode.text = "Alpha模式"
            }

            AppModeEnum.Product -> {
                tvDevelopMode.text = "Product模式"
            }

            else -> {
                tvDevelopMode.text = "Debug模式"
            }
        }
        tvDevelopMode.singleClick {
            //RouterManager.goDev(activity())
            RouterManager.goLiveHostRoom(activity())
        }
        viewModel.loginOptions().observe(this) {
            if (it is Ret.Success) {
                it.value.data?.sub?.forEach { sub ->
                    when (sub) {
                        "google" -> {
                            val loginTypeView = LayoutInflater.from(
                                BaseApplication.sInstance
                            ).inflate(R.layout.login_type_view_holder, null)
                            val ivLoginIcon = loginTypeView.findViewById<ImageView>(R.id.ivLoginIcon)
                            val tvLoginName = loginTypeView.findViewById<TextView>(R.id.tvLoginName)
                            ivLoginIcon.setImageResource(com.salton123.resources.R.drawable.ic_google_login)
                            tvLoginName.text = String.format(com.salton123.translate.R.string.login_via_type.getString(), "Google")
                            loginTypeView.setOnClickListener {
                            }
                            llLoginType.addView(loginTypeView)
                        }

                        "phone" -> {
                            val loginTypeView = LayoutInflater.from(
                                BaseApplication.sInstance
                            ).inflate(R.layout.login_type_view_holder, null)
                            val ivLoginIcon = loginTypeView.findViewById<ImageView>(R.id.ivLoginIcon)
                            val tvLoginName = loginTypeView.findViewById<TextView>(R.id.tvLoginName)
                            ivLoginIcon.setImageResource(com.salton123.resources.R.drawable.ic_phone_login)
                            tvLoginName.text = String.format(com.salton123.translate.R.string.login_via_type.getString(), "Phone")
                            loginTypeView.setOnClickListener {
                                PhoneLoginDialog.show(activity()) {
                                    RouterManager.goHome(activity())
                                }
                            }
                            llLoginType.addView(loginTypeView)
                        }
                    }
                }
            }
        }
        tvUserService.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        tvUserService.paint.isAntiAlias = true
        tvUserService.setOnClickListener {
            RouterManager.goWeb(this@LoginActivity, Bundle().apply {
                putString("url", "https://www.salton123.com/terms-of-service")
                putString("title", com.salton123.translate.R.string.terms_of_services.getString())
            })
        }

        tvPrivatePolicy.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        tvPrivatePolicy.paint.isAntiAlias = true
        tvPrivatePolicy.setOnClickListener {
            RouterManager.goWeb(this@LoginActivity, Bundle().apply {
                putString("url", "https://www.salton123.com/privacy-policy")
                putString("title", com.salton123.translate.R.string.privacy_policy.getString())
            })
        }
    }
}
