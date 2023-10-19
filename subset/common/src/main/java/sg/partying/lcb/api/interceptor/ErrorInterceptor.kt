package sg.partying.lcb.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Time:2023/2/21 17:46
 * Author:
 * Description:
 */
class ErrorInterceptor : Interceptor {

    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            val response = chain.proceed(request)

            val bodyString = response.body()!!.string()

            return response.newBuilder()
                .body(ResponseBody.create(response.body()?.contentType(), bodyString))
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            var msg: String
            when (e) {
                is SocketTimeoutException -> {
                    msg = "Timeout - Please check your internet connection"
                }
                is UnknownHostException -> {
                    msg = "Unable to make a connection. Please check your internet"
                }
                is ConnectionShutdownException -> {
                    msg = "Connection shutdown. Please check your internet"
                }
                is IOException -> {
                    msg = "Server is unreachable, please try again later."
                }
                is IllegalStateException -> {
                    msg = "${e.message}"
                }
                else -> {
                    msg = "${e.message}"
                }
            }

            return Response.Builder()
                .request(request)
                .code(999)
                .message(msg)
                .body(ResponseBody.create(null, "{${e}}")).build()
        }
    }
}