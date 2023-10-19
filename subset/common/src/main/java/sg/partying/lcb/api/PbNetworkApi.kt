package sg.partying.lcb.api

import com.google.gson.GsonBuilder
import com.salton123.app.BaseApplication
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

class PbNetworkApi : NetworkApi() {
    companion object {
        val INSTANCE: NetworkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkApi()
        }
    }
    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder.apply {
            addConverterFactory(WireConverterFactory.create())
        }
    }
}



