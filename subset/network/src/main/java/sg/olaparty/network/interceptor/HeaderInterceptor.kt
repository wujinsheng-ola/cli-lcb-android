package sg.olaparty.network.interceptor

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.GsonUtils
import okhttp3.Interceptor
import okhttp3.Response
import sg.partying.lcb.android.Session
import java.io.IOException

/**
 * 自定义头部参数拦截器，传入heads
 */
class HeaderInterceptor : Interceptor {
    companion object {
        val ABI = GsonUtils.toJson(DeviceUtils.getABIs())
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("User-Agent", "Agent / Xs android V${AppUtils.getAppVersionCode()} / Js V1.0.0.0 / Login V0")
        builder.addHeader("User-Tag", DeviceUtils.getUniqueDeviceId())
        builder.addHeader("User-Imei", DeviceUtils.getAndroidID())
        builder.addHeader("User-Oaid", "")
        builder.addHeader("User-Aaid", "")
        builder.addHeader("User-Idfa", "")
        builder.addHeader("User-Idfv", "")
        builder.addHeader("User-Channel", Session.userChannel)
        builder.addHeader("User-Model", DeviceUtils.getModel())
        builder.addHeader("User-Language", Session.language)
        builder.addHeader("User-IsSimulator", "${DeviceUtils.isEmulator()}")
        builder.addHeader("User-IsRoot", "false")
        builder.addHeader("User-Mac", "${DeviceUtils.getAndroidID()}")
        builder.addHeader("User-Did", "DurZjlM75zMTGa+WdrjrCQTmmpe+OOttMGkRwB+lrbd+85mxz/qE5uD7fgnoBQs6OKkvMqI4zqOlXqmerPmRHqDA")
        builder.addHeader("User-ABI", ABI)
        builder.addHeader("User-refer", "com.android.shell")
        if (Session.isLogined) {
            builder.addHeader("user-token", Session.token)
        }
        return chain.proceed(builder.build())
    }
}