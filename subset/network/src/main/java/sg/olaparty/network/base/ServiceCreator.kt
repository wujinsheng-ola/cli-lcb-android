package sg.olaparty.network.base

import android.util.Log
import com.blankj.utilcode.util.GsonUtils
import com.salton123.log.XLog
import com.salton123.soulove.CommonClassPath
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.wire.WireConverterFactory
import sg.olaparty.network.interceptor.HeaderInterceptor
import sg.olaparty.network.interceptor.SignInterceptor
import sg.partying.lcb.android.Prop
import java.io.File
import java.util.concurrent.TimeUnit


object ServiceCreator {

    private const val TAG = "ServiceCreator"
    private const val CONNECT_TIMEOUT = 60L
    private const val READ_TIMEOUT = 30L
    private const val WRITE_TIMEOUT = 30L
    private val okHttpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        OkHttpClient().newBuilder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            connectionPool(ConnectionPool(32, 5, TimeUnit.MINUTES))
            //设置缓存配置 缓存最大10M
            cache(Cache(File(Prop.netCachePath), 10 * 1024 * 1024))
            addInterceptor(HeaderInterceptor())
            addInterceptor(SignInterceptor())
            if (CommonClassPath.isDebugAppMode) {
                addInterceptor(HttpLoggingInterceptor {
                    Log.e(TAG, it)
                }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }.build()

    }

    fun <T> getApi(serviceClass: Class<T>, baseUrl: String): T {
        return Retrofit.Builder().apply {
            baseUrl(baseUrl)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create(GsonUtils.getGson()))
            addConverterFactory(WireConverterFactory.create())
        }.build().create(serviceClass)
    }

}

