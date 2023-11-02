package sg.olaparty.network.base

import com.salton123.config.IConfigProvider
import sg.partying.lcb.AppProperty

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
        AppProperty.RESOURCE_PREFIX_URL = "https://xs-aws-proxy.starcloud.rocks/"
    }

    override fun initAlpha() {
        API_BASE_URL = ""
        AppProperty.RESOURCE_PREFIX_URL = "https://xs-aws-proxy.starcloud.rocks/"
    }

    override fun initProduct() {
        API_BASE_URL = "https://api.partyother.com/"
        AppProperty.RESOURCE_PREFIX_URL = "https://xs-aws-proxy.starcloud.rocks/"
    }

    override fun afterInit() {
    }
}