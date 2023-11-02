package sg.olaparty.network.service

import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import sg.partying.lcb.api.resp.AccountInfo
import sg.partying.lcb.api.resp.LoginOption
import sg.partying.lcb.api.resp.LoginRet
import sg.partying.lcb.api.resp.Resp
import sg.partying.lcb.api.resp.UploadAvatarStatus

interface ProfileService {
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

    @POST("upload/image")
    @Multipart
    suspend fun uploadAvatar(@Part file: MultipartBody.Part): Resp<UploadAvatarStatus>


    @GET("account/info")
    suspend fun accountInfo(@Query("version") version: Int = 3): Resp<AccountInfo>


}