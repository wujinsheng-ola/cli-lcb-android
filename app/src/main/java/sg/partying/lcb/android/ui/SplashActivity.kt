package sg.partying.lcb.android.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.salton123.base.feature.ImmersionFeature
import sg.partying.lcb.android.Session

/**
 * Time:2022/1/27 10:52
 * Author:
 * Description:
 */
class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        ImmersionFeature(this).onBind()
        super.onCreate(savedInstanceState)
        if (Session.isLogined) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
    }
}