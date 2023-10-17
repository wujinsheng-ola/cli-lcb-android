package sg.partying.lcb.android.config

import com.salton123.config.IConfigProvider

/**
 * Time:2023/9/26 15:12
 * Author:
 * Description:
 */
object NetworkConfigProvider : IConfigProvider {
    var API_BASE_URL = ""
    override fun beforeInit() {
    }

    override fun initDebug() {
        API_BASE_URL = "http://partystar-dev.iambanban.com/"
    }

    override fun initAlpha() {
        API_BASE_URL = ""
    }

    override fun initProduct() {
        API_BASE_URL = "https://api.partyother.com/"
    }

    override fun afterInit() {
    }
}