package sg.partying.lcb.api.interceptor

import android.net.Uri
import android.os.Build
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.EncryptUtils
import com.salton123.app.BaseApplication
import com.salton123.log.XLog
import okhttp3.Interceptor
import okhttp3.Response
import sg.partying.lcb.android.Session


/**
 * Time:2023/9/26 17:32
 * Author:
 * Description:
 */
class SignInterceptor : Interceptor {
    private val tag = "SignInterceptor"

    companion object {
        var isVerify: Boolean = false
        var queryIndex: Int = 0
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var url = chain.request().url().toString()
        var isPb = false
        if (url.contains("/(PB)")) {
            url = url.replace("/(PB)", "")
            isPb = true
        }
        val builder = chain.request().newBuilder().url(generateSignUrl(url, null, isPb))
        return chain.proceed(builder.build())
    }

    fun generateSignUrl(url: String, params: Map<String, Any>?, isPb: Boolean): String {
        val args = hashMapOf<String, String>(
            "package" to "sg.partying.lcb.android",
            "_ipv" to if (isVerify) "1" else "0",
            "_platform" to "android",
            "_index" to "${++queryIndex}",
            "_model" to DeviceUtils.getAndroidID(),
            "_timestamp" to "${System.currentTimeMillis() / 1000}",
            "_abi" to Build.CPU_ABI
        )
        if (isPb) {
            args["format"] = "pb"
        } else {
            if (url.contains("/go")) {
                args["format"] = "json"
            }
        }
        val argsList = mutableListOf<String>()
        args.toSortedMap().asIterable().forEach {
            argsList.add("${it.key}=" + Uri.encode(it.value))
        }
        val sign = EncryptUtils.encryptMD5ToString("${argsList.joinToString("&")}!rilegoule#").toLowerCase()

        val targetArgsList = mutableListOf<String>()
        args.asIterable().forEach {
            targetArgsList.add("${it.key}=" + Uri.encode(it.value))
        }
        targetArgsList.add("_sign=$sign")
        if (Session.uid > 0) {
            targetArgsList.add("_blid=${Session.uid}")
        }
        if (!params.isNullOrEmpty()) {
            params.keys.forEachIndexed { index, s ->
                if (params[s] != null) {
                    val param = Uri.encode(params[s].toString())
                    targetArgsList.add("$s=$param")
                }
            }
        }
        val finalUrl = url + (if (url.indexOf("?") > -1) '&' else "?") + targetArgsList.joinToString("&")
        XLog.i(tag, "[generateSignUrl] $finalUrl")
        return finalUrl
    }
}