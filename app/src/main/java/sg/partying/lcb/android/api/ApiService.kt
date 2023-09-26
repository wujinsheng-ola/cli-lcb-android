package sg.partying.lcb.android.api

import retrofit2.http.GET
import sg.partying.lcb.android.api.resp.LoginOption
import sg.partying.lcb.android.api.resp.Resp

interface ApiService{
    @GET("go/ps/account/loginOptions")
    suspend fun loginOptions(): Resp<LoginOption>
}
