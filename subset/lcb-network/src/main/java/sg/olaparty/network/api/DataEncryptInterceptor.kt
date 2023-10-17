package sg.olaparty.network.api

import com.salton123.log.XLog
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSink
import sg.olaparty.network.Constants
import sg.olaparty.network.RequestHelper
import sg.olaparty.network.util.DeviceInfoHelper
import sg.olaparty.network.util.RC4Util
import kotlin.jvm.internal.Intrinsics


/**
 * User: newSalton@outlook.com
 * Date: 2019/8/24 23:39
 * ModifyTime: 23:39
 * Description:
 */
class DataEncryptInterceptor : Interceptor {
    private val mediaType = MediaType.parse("application/octet-stream")
    override fun intercept(chain: Interceptor.Chain): Response {
        val method = chain.request().method()
        return if (method == "GET") {
            getRequest(chain)
        } else {
            postRequest(chain)
        }
    }

    //test code
    @Volatile private var isRequest =5

    fun getRequest(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val proceed = chain.proceed(request.newBuilder()
                .header("Content-Type", "application/octet-stream")
                .header("x-type", "protobuf")
                .header("x-method", request.method())
                .header("x-token", RequestHelper.mToken)
                .header("x-api-version", "2").build())
        val x_errno = proceed.header("x-errno")
        var code = proceed.code()
        when (x_errno) {
            "16", "17" -> {
                code = 500
                XLog.i(this, "500 refreshToken" )
                return refreshToken(chain)
            }
        }
        if (isRequest < 1) {
            XLog.i(this, "isRequest refreshToken" )
            refreshToken(chain)
            isRequest += 1
        }
        val body = proceed.body()
        val byteStream = body?.byteStream()
        val build = proceed.newBuilder().body(
                ResponseBody.create(mediaType,
                        RC4Util.RC4Base(byteStream!!.readBytes(), Constants.getAppSecret())))
                .code(code).build()
        build.close()
        return build
    }

    fun postRequest(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val proceed = chain.proceed(processing(request))
        val x_errno = proceed.header("x-errno")
        var code = proceed.code()
        when (x_errno) {
            "16", "17" -> {
                code = 500
                XLog.i(this, "500 refreshToken" )
                return refreshToken(chain)
            }
        }
        if (isRequest < 1) {
            XLog.i(this, "isRequest refreshToken" )
            refreshToken(chain)
            isRequest += 1
        }
        val body = proceed.body()
        val byteStream = body!!.byteStream()
        val build = proceed.newBuilder().body(ResponseBody.create(mediaType,
                RC4Util.RC4Base(byteStream.readBytes(), Constants.getAppSecret())))
                .code(code).build()
        build.close()
        return build
    }

    private fun refreshToken(chain: Interceptor.Chain): Response {
        RequestHelper.mToken = ""
        XLog.i(this, "getNewToken:" + RequestHelper.mToken)
        var request = chain.request()
        request = processing(request)
        val body = chain.proceed(chain.request()).body()
        if (body == null) {
            Intrinsics.throwNpe()
        }
        body!!.close()
        var proceed = chain.proceed(request)
        val body2 = proceed.body()
        if (body2 == null) {
            Intrinsics.throwNpe()
        }
        val byteStream = body2!!.byteStream()
        proceed = proceed.newBuilder()
                .body(ResponseBody.create(this.mediaType,
                        RC4Util.RC4Base(byteStream.readBytes(), Constants.getAppSecret())))
                .build()
        proceed.close()
        return proceed
    }

    fun processing(request: Request): Request {
        var method = "GET"
        var replaceUrl = request.url().toString()
        if (replaceUrl.contains("/(GET)")) {
            replaceUrl = replaceUrl.replace("/(GET)", "")
        }
        if (replaceUrl.contains("/(POST)")) {
            replaceUrl = replaceUrl.replace("/(POST)", "")
            method = "POST"
        }

        val body = request.body()
        val buffer = Buffer()
        body!!.writeTo(buffer as BufferedSink)
        val readByteArray = buffer.readByteArray()
        buffer.close()
        val build = request.newBuilder()
                .header("Content-Type", "application/octet-stream")
                .header("x-type", "protobuf")
                .url(replaceUrl)
                .method(request.method(), RequestBody.create(mediaType,
                        RC4Util.RC4Base(readByteArray, Constants.getAppSecret())))
                .header("User-Agent", DeviceInfoHelper.getInstance().getUAInfo())
                .header("x-method", method)
                .header("x-token", RequestHelper.mToken)
                .header("x-api-version", "2").build()
        return build
    }
}