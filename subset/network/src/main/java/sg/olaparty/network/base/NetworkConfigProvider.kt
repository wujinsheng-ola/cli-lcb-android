package sg.olaparty.network.base

import com.salton123.config.IConfigProvider

/**
 * Time:2023/9/26 15:12
 * Author:
 * Description:
 */
object NetworkConfigProvider : IConfigProvider {
    var API_BASE_URL = ""
    var RESOURCE_PREFIX_URL = "https://xs-aws-proxy.starcloud.rocks/"
    override fun beforeInit() {
    }

    override fun initDebug() {
        API_BASE_URL = "http://partystar-dev.iambanban.com/"
        RESOURCE_PREFIX_URL = "https://xs-aws-proxy.starcloud.rocks/"
    }

    override fun initAlpha() {
        API_BASE_URL = ""
        RESOURCE_PREFIX_URL = "https://xs-aws-proxy.starcloud.rocks/"
    }

    override fun initProduct() {
        API_BASE_URL = "https://api.partyother.com/"
        RESOURCE_PREFIX_URL = "https://xs-aws-proxy.starcloud.rocks/"
    }

    override fun afterInit() {
    }
}