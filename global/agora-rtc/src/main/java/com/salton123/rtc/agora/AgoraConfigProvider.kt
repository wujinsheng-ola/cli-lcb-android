package com.salton123.rtc.agora

import com.salton123.config.IConfigProvider

/**
 * Time:2023/9/26 15:12
 * Author:
 * Description:
 */
object AgoraConfigProvider : IConfigProvider {
    var AGORA_APP_ID = ""

    override fun beforeInit() {
    }

    override fun initDebug() {
        AGORA_APP_ID = "https://api.partyother.com/"
    }

    override fun initAlpha() {
        AGORA_APP_ID = ""
    }

    override fun initProduct() {
        AGORA_APP_ID = "https://api.partyother.com/"
    }

    override fun afterInit() {
    }
}