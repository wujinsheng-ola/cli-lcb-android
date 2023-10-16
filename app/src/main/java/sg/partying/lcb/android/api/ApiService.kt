package sg.partying.lcb.android.api

import pb.ReqFeedRecommendRoom
import pb.ReqFeedRoom
import pb.ResFeedRecommendRoom
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import sg.partying.lcb.android.api.resp.BannerItem
import sg.partying.lcb.android.api.resp.LoginOption
import sg.partying.lcb.android.api.resp.LoginRet
import sg.partying.lcb.android.api.resp.PbResp
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

    @POST("homepage/recommendedRoomList")
    suspend fun recommendedRoomList(): Resp<MutableList<BannerItem>>

    @Headers("Content-Type:text/json; charset=utf-8")
    @POST("go/ps/feed/recommendLiveChatRoom")
    suspend fun recommendLiveChatRoom(@Body req: ReqFeedRoom): PbResp<ReqFeedRecommendRoom>

    //    String url = '${System.domain}foryou/recommend?page=$page&version=3&nearby=$nearby&feed_type=${isVideoLive ? 'liveroom' : 'chat'}';
    @POST("foryou/recommend")
    @FormUrlEncoded
    suspend fun getRecommend(
        @Field("page") page: String,
        @Field("version") version: String,
        @Field("nearby") nearby: String,
        @Field("feed_type") feedType: String,
        @Field("browseRids") browseRids: String,
        @Field("browseUids") browseUids: String,
        @Field("sex") sex: String,
        @Field("abTestDebug") abTestDebug: String,
        @Field("cityCode") cityCode: String,
        @Field("longitude") longitude: String,
        @Field("latitude") latitude: String,
    ): Resp<String>
}
