package sg.partying.lcb.android.dialog

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.blankj.utilcode.util.EncodeUtils
import com.blankj.utilcode.util.EncryptUtils
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.CenterPopupView
import com.salton123.log.XLog
import com.salton123.utils.PreferencesUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kt.singleClick
import kt.toast
import sg.olaparty.network.RequestCenter.loginService
import sg.partying.lcb.android.R
import sg.partying.lcb.android.Session

/**
 * Time:2023/9/26 11:54
 * Author:
 * Description:
 */
class PhoneLoginDialog(context: Context) : CenterPopupView(context) {

    companion object {
        private const val TAG = "PhoneLoginDialog"
        fun show(activity: Context, confirmListener: (() -> Unit)?) {
            val contentView = PhoneLoginDialog(activity)
            contentView.confirmListener = confirmListener
            XPopup.Builder(activity)
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(true)
                .autoDismiss(false)
                .hasShadowBg(true)
                .isLightStatusBar(true)
                .asCustom(contentView)
                .show()
        }
    }

    override fun getImplLayoutId(): Int = R.layout.dialog_phone_login
    var confirmListener: (() -> Unit)? = null
    override fun init() {
        super.init()
        val tvNext = findViewById<TextView>(R.id.tvNext)
        val llPassword = findViewById<LinearLayout>(R.id.llPassword)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)
        val etMobile = findViewById<EditText>(R.id.etMobile)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        etMobile.setText("18688367977")
        etPassword.setText("111111")
        tvNext.singleClick {
            llPassword.visibility = View.VISIBLE
        }
        tvLogin.singleClick {
            val mobile = etMobile.text.toString().trim()
            val password = etPassword.text.toString().trim()
            if (mobile.isNullOrEmpty()) {
                toast("请填写手机号")
                return@singleClick
            }
            if (password.isNullOrEmpty()) {
                toast("请填写密码")
                return@singleClick
            }
            dispatchPhoneLogin(mobile, password)
        }
    }

    fun dispatchPhoneLogin(mobile: String, password: String) {
        val area = Session.area
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val encryptPassword = EncryptUtils.encryptSHA1ToString(
                    EncodeUtils.urlDecode(password, "UTF-8")
                ).lowercase()
                XLog.i(TAG, "encryptPassword:$encryptPassword")
                val cachedDToken = PreferencesUtils.getString("dtoken", "")
                val ret = loginService.passwordLogin(mobile, encryptPassword, area, cachedDToken, "")
                if (ret.success) {
                    XLog.i(TAG, "${ret.data}")
                    PreferencesUtils.putInt("age", ret.data?.age ?: 0)
                    PreferencesUtils.putInt("app", ret.data?.app ?: 0)
                    PreferencesUtils.putString("birthday", ret.data?.birthday ?: "")
                    PreferencesUtils.putInt("cash_min", ret.data?.cashMin ?: 0)
                    PreferencesUtils.putInt("cash_rate", ret.data?.cashRate ?: 0)
                    PreferencesUtils.putString("city", ret.data?.city ?: "")
                    PreferencesUtils.putInt("city_code", ret.data?.cityCode ?: 0)
                    PreferencesUtils.putInt("dateline", ret.data?.dateline ?: 0)
                    PreferencesUtils.putInt("deleted", ret.data?.deleted ?: 0)
                    PreferencesUtils.putString("dtoken", ret.data?.dtoken ?: "")
                    PreferencesUtils.putInt("friend", ret.data?.friend ?: 0)
                    PreferencesUtils.putString(
                        "game_login_token",
                        ret.data?.gameLoginToken
                            ?: ""
                    )
                    PreferencesUtils.putInt("god_num", ret.data?.godNum ?: 0)
//                        PreferencesUtils.putString("icon", ret.data?.icon)
                    Session.icon = ret.data?.icon ?: ""
                    PreferencesUtils.putInt("job", ret.data?.job ?: 0)
//                        PreferencesUtils.putInt("latitude", ret.data?.latitude)
                    Session.latitude = ret.data?.latitude ?: 0
//                        PreferencesUtils.putInt("longitude", ret.data?.longitude)
                    Session.longitude = ret.data?.longitude ?: 0
//                        PreferencesUtils.putString("name", ret.data?.name)
                    Session.name = ret.data?.name ?: ""

                    PreferencesUtils.putInt("online_dateline", ret.data?.onlineDateline ?: 0)
                    PreferencesUtils.putInt("pay_money", ret.data?.payMoney ?: 0)
                    PreferencesUtils.putInt("pay_num", ret.data?.payNum ?: 0)
                    PreferencesUtils.putString("position", ret.data?.position ?: "")
                    PreferencesUtils.putInt("role", ret.data?.role ?: 0)
                    PreferencesUtils.putInt("server_time", ret.data?.serverTime ?: 0)
                    PreferencesUtils.putInt("sex", ret.data?.sex ?: 0)
                    PreferencesUtils.putString("sign", ret.data?.sign ?: "")
                    PreferencesUtils.putInt("star", ret.data?.star ?: 0)
                    PreferencesUtils.putInt("title", ret.data?.title ?: 0)
                    PreferencesUtils.putInt("title_new", ret.data?.titleNew ?: 0)
                    PreferencesUtils.putString("tmp_icon", ret.data?.tmpIcon ?: "")
//                        PreferencesUtils.putString("token", ret.data?.token)
                    Session.token = ret.data?.token ?: ""
//                        PreferencesUtils.putInt("uid", ret.data?.uid)
                    Session.uid = ret.data?.uid ?: 0
                    PreferencesUtils.putInt("version", ret.data?.version ?: 0)
                    PreferencesUtils.putInt("vip", ret.data?.vip ?: 0)
                    PreferencesUtils.putInt("vip_new", ret.data?.vipNew ?: 0)
                    confirmListener?.invoke()
                } else {
                    withContext(Dispatchers.Main) {
                        toast(ret.msg)
                    }
                }
            }
        }
    }
}
