package sg.partying.lcb.android.config

import com.salton123.config.AppModeEnum
import com.salton123.config.IConfigProvider
import com.salton123.soulove.CommonClassPath

object AppConfig {
    private var sConfigProvider: IConfigProvider? = null
    fun init(configProvider: IConfigProvider?) {
        sConfigProvider = configProvider
        if (sConfigProvider == null) {
            throw IllegalArgumentException("you must setProvider first")
        }
        val appMode = CommonClassPath.appMode
        sConfigProvider!!.beforeInit()
        when (appMode) {
            AppModeEnum.Debug -> sConfigProvider!!.initDebug()
            AppModeEnum.Alpha -> sConfigProvider!!.initAlpha()
            else -> sConfigProvider!!.initProduct()
        }
        sConfigProvider!!.afterInit()
    }
}
