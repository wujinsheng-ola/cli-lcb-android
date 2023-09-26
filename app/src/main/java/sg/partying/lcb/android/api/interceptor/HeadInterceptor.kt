package sg.partying.lcb.android.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 自定义头部参数拦截器，传入heads
 */
class HeadInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val builder = chain.request().newBuilder()
        builder.addHeader("User-Agent", "Agent / Xs android V2.20.0.1 / Js V1.0.0.0 / Login V0")
        builder.addHeader("User-Tag", "9f6d581813458bd8")
        builder.addHeader("User-Imei", "9f6d581813458bd8")
        builder.addHeader("User-Oaid", "")
        builder.addHeader("User-Aaid", "87186145-e5d1-4537-92b5-1d97555eb740")
        builder.addHeader("User-Idfa", "")
        builder.addHeader("User-Idfv", "")
        builder.addHeader("User-Channel", "gp_ps")
        builder.addHeader("User-Model", "M2011K2C")
        builder.addHeader("User-Language", "en")
        builder.addHeader("User-IsSimulator", "false")
        builder.addHeader("User-IsRoot", "false")
        builder.addHeader("User-Mac", "9f6d581813458bd8")
        builder.addHeader("User-Did", "DurZjlM75zMTGa+WdrjrCQTmmpe+OOttMGkRwB+lrbd+85mxz/qE5uD7fgnoBQs6OKkvMqI4zqOlXqmerPmRHqDA")
        builder.addHeader("User-ABI", "arm64-v8a")
        return chain.proceed(builder.build())
    }

}