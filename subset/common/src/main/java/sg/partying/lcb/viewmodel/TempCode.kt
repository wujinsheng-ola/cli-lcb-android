package sg.partying.lcb.viewmodel

import com.blankj.utilcode.util.GsonUtils
import com.salton123.app.BaseApplication
import me.hgj.jetpackmvvm.network.interceptor.CacheInterceptor
import okhttp3.Cache
import okhttp3.Call
import okhttp3.Callback
import okhttp3.ConnectionPool
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import pb.ApiFeed
import sg.partying.lcb.api.interceptor.ErrorInterceptor
import sg.partying.lcb.api.interceptor.HeadInterceptor
import sg.partying.lcb.api.interceptor.SignInterceptor
import sg.partying.lcb.config.NetworkConfigProvider
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Time:2023/10/25 15:16
 * Author:
 * Description:
 */
object TempCode {

    fun test() {
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.apply {
            //设置缓存配置 缓存最大10M
            cache(Cache(File(BaseApplication.sInstance.cacheDir, "cxk_cache"), 10 * 1024 * 1024))
            addInterceptor(HeadInterceptor())
            addInterceptor(SignInterceptor())
            addInterceptor(ErrorInterceptor())
            addInterceptor(CacheInterceptor())
            builder.addInterceptor(interceptor)
            connectionPool(ConnectionPool(32, 5, TimeUnit.MINUTES))
        }
        val req = ApiFeed.ReqFeedRoom.newBuilder().setPage(1).setPageSize(20).build()
        val client = builder.build()
        client.newCall(
            Request.Builder()
                .url(NetworkConfigProvider.API_BASE_URL + "go/ps/feed/recommendLiveChatRoom/(PB)")
                .post(RequestBody.create(MediaType.parse("application/protobuf"), req.toByteArray()))
                .build())
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println(e)
                }

                override fun onResponse(call: Call, response: Response) {
//                        println(response)
//                    val adapter = ProtoAdapter.get(ReqFeedRecommendRoom::class.java)
//                    adapter.decode(response.body()!!.byteStream())
//                    val data = ReqFeedRecommendRoom::class.java.newInstance().adapter.decode(response.body()!!.byteStream())
                    if (response.isSuccessful) {
                        val retStream = response.body()?.byteStream()
                        retStream?.let {
                            val data = ApiFeed.ResFeedRecommendRoom.parseFrom(retStream)
                            println(GsonUtils.toJson(data))
                        }
                    }
                }
            })

//        client.newCall(
//            Request.Builder()
//                .url(NetworkConfigProvider.API_BASE_URL + "go/ps/feed/recommendLiveChatRoom")
//                .post(RequestBody.create(MediaType.parse("application/octet-stream"),req.encode()))
//                .build())
//            .enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    println(e)
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    println(response)
////                    ReqFeedRecommendRoom::class.java.newInstance().adapter.decode(response.body()!!.byteStream())
//                    if(response.isSuccessful){
//                        val result = response.body()?.string()
//                        println(result)
//                    }
//                }
//            })
    }


//    fun passwordLogin(
//        mobile: String,
//        password: String,
//        area: String,
//    ): MutableLiveData<ResultState<Resp<LoginOption>>> {
//        requestNoCheck({
//            apiService.passwordLogin(mobile, password, area, "", "")
//        }, { apiResponse ->
//            passwordLoginRet.paresResult(apiResponse)
//        }, {
//            it.printStackTrace()
//        }, true)
//        return passwordLoginRet
//    }
}