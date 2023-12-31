package sg.partying.lcb.api

import pb.ReqFeedRecommendRoom
import pb.ReqFeedRoom
import pb.RspActionEnterV2
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import sg.partying.lcb.api.resp.BannerItem
import sg.partying.lcb.api.resp.LoginOption
import sg.partying.lcb.api.resp.LoginRet
import sg.partying.lcb.api.resp.PbResp
import sg.partying.lcb.api.resp.LiveRecommendModel
import sg.partying.lcb.api.resp.Resp

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

    @Headers("Content-Type: application/octet-stream")
    @POST("go/ps/feed/recommendLiveChatRoom/(PB)")
    suspend fun recommendLiveChatRoom(@Body req: ReqFeedRoom): PbResp<ReqFeedRecommendRoom>

//    @POST("foryou/recommend?page=1&version=3&limit=100&nearby=0&feed_type=liveroom")
    @POST("foryou/recommend")
    @FormUrlEncoded
    suspend fun getRecommend(
        @Query("page") page: Int,
        @Query("version") version: Int,
        @Query("limit") limit: Int,
        @Query("nearby") nearby: Int,
        @Query("feed_type") feedType: String,
        @Field("browseRids") browseRids: String,
        @Field("browseUids") browseUids: String,
        @Field("sex") sex: String,
        @Field("abTestDebug") abTestDebug: String,
        @Field("cityCode") cityCode: String,
        @Field("longitude") longitude: String,
        @Field("latitude") latitude: String,
    ): Resp<LiveRecommendModel>

    @FormUrlEncoded
    @POST("go/room/action/enter/(PB)")
    @Headers("Content-Type: application/octet-stream")
    suspend fun joinRoom(
        @Field("rid") rid: String,
        @Field("password") password: String,
        @Field("inviter_uid") inviterUid: String,
        @Field("user_memory") userMemory: String,
    ): PbResp<RspActionEnterV2>

}
