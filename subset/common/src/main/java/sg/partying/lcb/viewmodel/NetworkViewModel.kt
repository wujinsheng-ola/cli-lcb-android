package sg.partying.lcb.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.GsonUtils
import com.salton123.app.BaseApplication
import com.squareup.wire.ProtoAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import pb.ReqFeedRecommendRoom
import pb.ReqFeedRoom
import pb.RspActionEnterV2
import pb.UserMemory
import retrofit2.http.Field
import sg.partying.lcb.api.apiService
import sg.partying.lcb.api.interceptor.HeadInterceptor
import sg.partying.lcb.api.interceptor.SignInterceptor
import sg.partying.lcb.api.resp.BannerItem
import sg.partying.lcb.api.resp.LoginOption
import sg.partying.lcb.api.resp.PbResp
import sg.partying.lcb.api.resp.LiveRecommendModel
import sg.partying.lcb.api.resp.Resp
import sg.partying.lcb.config.NetworkConfigProvider
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
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
    private val liveLiveRecommendModel by lazy { MutableLiveData<ResultState<Resp<LiveRecommendModel>>>() }

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

    fun getRecommend(page: Int, limit: Int, feedType: String): MutableLiveData<ResultState<Resp<LiveRecommendModel>>> {
        requestNoCheck({
            apiService.getRecommend(page, 3, limit, 0, feedType,
                "0", "0", "1", "0", "", "", "")
        }, { apiResponse ->
            liveLiveRecommendModel.paresResult(apiResponse)
        }, {
            it.printStackTrace()
        }, true)
        return liveLiveRecommendModel
    }

    fun testJoinRoom(
        @Field("rid") rid: String,
        @Field("password") password: String,
        @Field("inviter_uid") inviterUid: String,
        @Field("user_memory") userMemory: String,
    ) {
        val memory = UserMemory(39, 7250.2188f, 2092.0898f, false)
//        viewModelScope.launch {
//            try {
//                val resp = apiService.joinRoom(rid, password, inviterUid, GsonUtils.toJson(memory))
//                println(resp)
//            } catch (e: Exception) {
//                e.toString()
//            }
//        }
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor {
            Log.d("NetworkApi", it)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.apply {
            //设置缓存配置 缓存最大10M
            cache(Cache(File(BaseApplication.sInstance.cacheDir, "cxk_cache"), 10 * 1024 * 1024))
            addInterceptor(HeadInterceptor())
            addInterceptor(SignInterceptor())
            addInterceptor(CacheInterceptor())
            builder.addInterceptor(interceptor)
            connectionPool(ConnectionPool(32, 5, TimeUnit.MINUTES))
        }

        val stringBuilder = StringBuilder()
        stringBuilder.append("rid=$rid&password=$password&inviter_uid=&user_memory=$")
        stringBuilder.append("&inviter_uid=null")
        stringBuilder.append("&user_memory=${GsonUtils.toJson(memory)}")
//        val requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), stringBuilder.toString())
        val requestBody = stringBuilder.toString().toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaType())
        val client = builder.build()
        client.newCall(
            Request.Builder()
                .url(NetworkConfigProvider.API_BASE_URL + "go/room/action/enter/(PB)")
                .post(requestBody)
                .build())
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    println(response)
                    if (response.isSuccessful) {
                        val retStream = response.body?.byteStream()
                        retStream?.let {
                            val adapter = ProtoAdapter.get(RspActionEnterV2::class.java)
                            val data = adapter.decode(retStream)
                            println("testJoinRoom:" + GsonUtils.toJson(data))
                        }
                    }
                }
            })
    }


}