package sg.partying.lcb.android.api

import com.salton123.log.XLog
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Time:3/15/21 7:54 PM
 * Author:
 * Description:
 */
object RetrofitManager {
     val okHttpClient by lazy {
        OkHttpClient.Builder().apply {
            followRedirects(true)
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor {
                XLog.i("HttpLoggingInterceptor", it)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
//            hostnameVerifier { _, _ -> true }
        }.build()
    }

    fun <T> of(tClass: Class<T>, baseUrl: String): T {
        return Retrofit.Builder()
            .callFactory(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(tClass)
    }
}