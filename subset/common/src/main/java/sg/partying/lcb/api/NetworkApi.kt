package sg.partying.lcb.api

import android.util.Log
import com.blankj.utilcode.util.GsonUtils
import com.google.gson.GsonBuilder
import com.salton123.app.BaseApplication
import com.salton123.log.XLog
import me.hgj.jetpackmvvm.network.BaseNetworkApi
import me.hgj.jetpackmvvm.network.interceptor.CacheInterceptor
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.wire.WireConverterFactory
import sg.partying.lcb.api.interceptor.ErrorInterceptor
import sg.partying.lcb.api.interceptor.HeadInterceptor
import sg.partying.lcb.api.interceptor.SignInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

open class NetworkApi : BaseNetworkApi() {
    private val CONNECT_TIMEOUT: Long = 60
    private val READ_TIMEOUT: Long = 100
    private val WRITE_TIMEOUT: Long = 60

    companion object {
        val INSTANCE: NetworkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkApi()
        }
    }

    /**
     * 实现重写父类的setHttpClientBuilder方法，
     * 在这里可以添加拦截器，可以对 OkHttpClient.Builder 做任意操作
     */
    override fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor {
            Log.d("NetworkApi",it)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.apply {
            //设置缓存配置 缓存最大10M
            cache(Cache(File(BaseApplication.sInstance.cacheDir, "cxk_cache"), 10 * 1024 * 1024))
            addInterceptor(HeadInterceptor())
            addInterceptor(SignInterceptor())
            addInterceptor(ErrorInterceptor())
            addInterceptor(CacheInterceptor())
            builder.addInterceptor(interceptor)
            //超时时间 连接、读、写
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            connectionPool(ConnectionPool(32, 5, TimeUnit.MINUTES))
        }
        return builder
    }

    /**
     * 实现重写父类的setRetrofitBuilder方法，
     * 在这里可以对Retrofit.Builder做任意操作，比如添加GSON解析器，protobuf等
     */
    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder.apply {
            addConverterFactory(WireConverterFactory.create())
            addConverterFactory(GsonConverterFactory.create(GsonUtils.getGson()))
        }
    }
}



