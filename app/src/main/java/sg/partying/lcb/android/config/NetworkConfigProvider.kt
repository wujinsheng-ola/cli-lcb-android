package sg.partying.lcb.android.config

/**
 * Time:2023/9/26 15:12
 * Author:
 * Description:
 */
class NetworkConfigProvider : IConfigProvider {
    companion object {
        var API_BASE_URL = "";
    }

    override fun beforeInit() {
    }

    override fun initDebug() {
//        http://partystar-dev.iambanban.com/homepage/recommendedRoomList
        API_BASE_URL = "http://partystar-dev.iambanban.com/";
    }

    override fun initAlpha() {
        API_BASE_URL = "";
    }

    override fun initProduct() {
        API_BASE_URL = "";
    }

    override fun afterInit() {
    }
}