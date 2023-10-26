package sg.olaparty.network.service

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import sg.partying.lcb.api.resp.LoginOption
import sg.partying.lcb.api.resp.LoginRet
import sg.partying.lcb.api.resp.Resp

interface LoginService {
    @GET("go/ps/account/loginOptions")
    suspend fun loginOptions(): Resp<LoginOption>

    @POST("account/passwordLogin?v=1")
    @FormUrlEncoded
    suspend fun passwordLogin(
        @Field("mobile") mobile: String,
        @Field("password") password: String,
        @Field("area") area: String,
        @Field("dtoken") dToken: String,
        @Field("country_code") countryCode: String,
    ): Resp<LoginRet>

}