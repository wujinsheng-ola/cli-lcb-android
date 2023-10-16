package com.salton123.eleph

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.GsonUtils
import com.salton123.app.BaseApplication
import com.squareup.wire.Message
import com.squareup.wire.ProtoAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.network.interceptor.CacheInterceptor
import me.hgj.jetpackmvvm.state.ResultState
import me.hgj.jetpackmvvm.state.paresResult
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
import pb.ReqFeedRecommendRoom
import pb.ReqFeedRoom
import pb.ResFeedRecommendRoom
import sg.partying.lcb.android.api.apiService
import sg.partying.lcb.android.api.interceptor.ErrorInterceptor
import sg.partying.lcb.android.api.interceptor.HeadInterceptor
import sg.partying.lcb.android.api.interceptor.SignInterceptor
import sg.partying.lcb.android.api.resp.BannerItem
import sg.partying.lcb.android.api.resp.LoginOption
import sg.partying.lcb.android.api.resp.PbResp
import sg.partying.lcb.android.api.resp.Resp
import sg.partying.lcb.android.config.NetworkConfigProvider
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Time:2023/9/26 16:49
 * Author:
 * Description:
 */
class NetworkViewModel : BaseViewModel() {
    private val loginOptionsRet by lazy { MutableLiveData<ResultState<Resp<LoginOption>>>() }
    private val videoLiveFeedRet by lazy { MutableLiveData<ResultState<Resp<MutableList<BannerItem>>>>() }
    private val recommendLiveChatRoom by lazy { MutableLiveData<ResultState<PbResp<ReqFeedRecommendRoom>>>() }

    fun loginOptions(): MutableLiveData<ResultState<Resp<LoginOption>>> {
        requestNoCheck({
            apiService.loginOptions()
        }, { apiResponse ->
            loginOptionsRet.paresResult(apiResponse)
        }, {
            it.printStackTrace()
        }, true)
        return loginOptionsRet
    }

    fun videoLiveFeed(): MutableLiveData<ResultState<Resp<MutableList<BannerItem>>>> {
        requestNoCheck({
            apiService.videoLiveFeed()
        }, { apiResponse ->
            videoLiveFeedRet.paresResult(apiResponse)
        }, {
            it.printStackTrace()
        }, true)
        return videoLiveFeedRet
    }

    fun recommendLiveChatRoom(req: ReqFeedRoom): MutableLiveData<ResultState<PbResp<ReqFeedRecommendRoom>>> {
        requestNoCheck({

            apiService.recommendLiveChatRoom(req)
        }, { apiResponse ->
            recommendLiveChatRoom.paresResult(apiResponse)
        }, {
            it.printStackTrace()
        }, true)
        return recommendLiveChatRoom
    }

    fun getRecommend() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
//                foryou/recommend?page=1&version=3&limit=5&nearby=0&feed_type=liveroom
//                apiService.getRecommend("1","3","0","liveroom","0","0","1","0",),
            }
        }
    }

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
        val req = ReqFeedRoom(1, 20)
        val client = builder.build()
        client.newCall(
            Request.Builder()
                .url(NetworkConfigProvider.API_BASE_URL + "go/ps/feed/recommendLiveChatRoom")
                .post(RequestBody.create(MediaType.parse("text/json; charset=utf-8"), GsonUtils.toJson(req)))
                .build())
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    println(response)
//                    val adapter = ProtoAdapter.get(ReqFeedRecommendRoom::class.java)
//                    adapter.decode(response.body()!!.byteStream())
//                    val data = ReqFeedRecommendRoom::class.java.newInstance().adapter.decode(response.body()!!.byteStream())
                    if (response.isSuccessful) {
                        val retStream = response.body()?.string()
                        retStream?.let {
                            val adapter = ProtoAdapter.get(ResFeedRecommendRoom::class.java)
                            val data = adapter.decode(retStream.toByteArray())
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