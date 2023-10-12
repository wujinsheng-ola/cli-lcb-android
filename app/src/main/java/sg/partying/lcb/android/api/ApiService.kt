package sg.partying.lcb.android.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import sg.partying.lcb.android.api.resp.BannerItem
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

    @POST("banner/videoLiveFeed")
    suspend fun videoLiveFeed(): Resp<MutableList<BannerItem>>
//    http://partystar-dev.iambanban.com/banner/videoLiveFeed?package=sg.partying.lcb.android&_ipv=0&_platform=android&_index=19&_model=M2011K2C&_timestamp=1697020045&_abi=arm64-v8a&_sign=527c5b2880854611763152a04d1edf77&_blid=816288718
//    http://partystar-dev.iambanban.com/banner/videoLiveFeed?_abi=arm64-v8a&_index=3&_ipv=0&_model=6bfb46f09e93211b&_platform=android&_timestamp=1697019739&package=sg.partying.lcb.android&_sign=3ffc50cb9adf5e0ffb748dc12225d781&_blid=816260300
    @POST("homepage/recommendedRoomList")
    suspend fun recommendedRoomList(): Resp<MutableList<BannerItem>>
}
