package sg.partying.lcb.android.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import sg.partying.lcb.android.api.resp.LoginOption
import sg.partying.lcb.android.api.resp.LoginRet
import sg.partying.lcb.android.api.resp.Resp

interface ApiService {
    @GET("go/ps/account/loginOptions")
    suspend fun loginOptions(): Resp<LoginOption>

    @POST("account/passwordLogin?v=1")
    @FormUrlEncoded
    suspend fun passwordLogin(
        @Field("mobile") mobile: String,
        @Field("password") password: String,
        @Field("area") area: String,
        @Field("dtoken") dtoken: String,
        @Field("country_code") country_code: String,
    ): Resp<LoginRet>

}
