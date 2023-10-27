package sg.olaparty.network.viewmodel

import com.blankj.utilcode.util.GsonUtils
import com.salton123.app.BaseApplication
import okhttp3.Cache
import okhttp3.Call
import okhttp3.Callback
import okhttp3.ConnectionPool
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import pb.ReqFeedRoom
import pb.ResFeedRecommendRoom
import sg.olaparty.network.base.NetworkConfigProvider
import sg.olaparty.network.interceptor.HeaderInterceptor
import sg.olaparty.network.interceptor.SignInterceptor
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
            addInterceptor(HeaderInterceptor())
            addInterceptor(SignInterceptor())
            builder.addInterceptor(interceptor)
            connectionPool(ConnectionPool(32, 5, TimeUnit.MINUTES))
        }
        val req = ReqFeedRoom(1, 20)
        val client = builder.build()
        client.newCall(
            Request.Builder()
                .url(NetworkConfigProvider.API_BASE_URL + "go/ps/feed/recommendLiveChatRoom/(PB)")
                .post(GsonUtils.toJson(req).toRequestBody("application/protobuf".toMediaType()))
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
                        try {
                            val retStream = response.body?.byteStream()
                            retStream?.let {
                                val data = ResFeedRecommendRoom::class.java.newInstance().adapter.decode(retStream)
                                println(GsonUtils.toJson(data))
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            })

    }

//
//    fun testJoinRoom(
//        @Field("rid") rid: String,
//        @Field("password") password: String,
//        @Field("inviter_uid") inviterUid: String,
//        @Field("user_memory") userMemory: String,
//    ) {
//        val memory = App.UserMemory.newBuilder().setAppMem(39).setFreeMem(2092.0898f).setTotalMem(7250.2188f).setLowMemory(false)
//
////        (39, 7250.2188f, 2092.0898f, false)
//        GlobalScope.launch {
//            try {
//                val resp = apiService.joinRoom(rid, password, inviterUid, GsonUtils.toJson(memory))
//                println(resp)
//            } catch (e: Exception) {
//                e.toString()
//            }
//        }
//        val builder = OkHttpClient.Builder()
//        val interceptor = HttpLoggingInterceptor {
//            Log.d("NetworkApi", it)
//        }
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        builder.apply {
//            //设置缓存配置 缓存最大10M
//            cache(Cache(File(BaseApplication.sInstance.cacheDir, "cxk_cache"), 10 * 1024 * 1024))
//            addInterceptor(HeadInterceptor())
//            addInterceptor(SignInterceptor())
////            addInterceptor(ErrorInterceptor())
//            addInterceptor(CacheInterceptor())
//            builder.addInterceptor(interceptor)
//            connectionPool(ConnectionPool(32, 5, TimeUnit.MINUTES))
//        }
//        val stringBuilder = StringBuilder()
//        stringBuilder.append("rid=$rid&password=$password&inviter_uid=&user_memory=$")
//        stringBuilder.append("&inviter_uid=null")
//        stringBuilder.append("&user_memory=${GsonUtils.toJson(memory)}")
//        val requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), stringBuilder.toString())
//        val client = builder.build()
//        client.newCall(
//            Request.Builder()
//                .url(NetworkConfigProvider.API_BASE_URL + "go/room/action/enter/(PB)")
//                .post(requestBody)
//                .build())
//            .enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    println(e)
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    if (response.isSuccessful) {
//                        try {
//                            val inputStream = response.body()?.byteStream()
//                            val data = ActionEnter.RspActionEnterV2.parseFrom(inputStream!!)
//                            println("testJoinRoom:" + GsonUtils.toJson(data))
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                        }
//                    }
//                }
//            })
//
//    }

}