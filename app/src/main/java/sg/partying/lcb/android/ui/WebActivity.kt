package sg.partying.lcb.android.ui

import android.text.TextUtils
import android.view.KeyEvent
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.salton123.app.BaseApplication
import sg.partying.lcb.android.R
import sg.partying.lcb.base.BaseActivity

/**
 * User: newSalton@outlook.com
 * Date: 2019/8/17 16:40
 * ModifyTime: 16:40
 * Description:
 */
class WebActivity : BaseActivity() {
    var url = "https://www.salton123.com/privacy-policy"
    var title = BaseApplication.sInstance.getString(R.string.privacy_policy)
    override fun getLayoutId(): Int = R.layout.aty_web_view

    private lateinit var mAgentWeb: AgentWeb
    private lateinit var llContainer: FrameLayout
    override fun initViewAndData() {
        super.initViewAndData()

        var targetUrl = intent?.getStringExtra("url") ?: ""
        if (!TextUtils.isEmpty(targetUrl)) {
            url = targetUrl
        }
        var targetTitle = intent?.getStringExtra("title") ?: ""
        if (!TextUtils.isEmpty(targetTitle)) {
            title = targetTitle
        }
        llContainer = f(R.id.llContainer)
        tvTitle.text = title
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(llContainer, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
//            .setWebChromeClient(mWebChromeClient)
//            .setWebViewClient(mWebViewClient)
//            .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
//            .setWebLayout(WebLayout(this))
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK) // 打开其他应用时，弹窗咨询用户是否前往其他应用
            .interceptUnkownUrl() // 拦截找不到相关页面的Scheme
            .createAgentWeb()
            .ready()
            .go(url)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mAgentWeb.webLifeCycle.onDestroy()
    }
}
