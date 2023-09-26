package sg.partying.lcb.android.api

import io.reactivex.Observable
import retrofit2.http.*
import sg.partying.lcb.android.api.resp.LoginOption
import sg.partying.lcb.android.api.resp.Resp
import sg.partying.lcb.android.config.NetworkConfigProvider

/**
 * Time:2022/12/14 16:40
 * Author:
 * Description:
 */
interface Api {
    @GET("go/ps/account/loginOptions")
    fun loginOptions(): Observable<Resp<LoginOption>>
}

//https://open-api.tiktok.com/oauth/access_token/
//双重校验锁式-单例 封装NetApiService 方便直接快速调用简单的接口
val partyApi: Api by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    RetrofitManager.of(Api::class.java, NetworkConfigProvider.API_BASE_URL)
}