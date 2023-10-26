package sg.olaparty.network.service

import pb.ReqFeedRecommendRoom
import pb.ReqFeedRoom
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import sg.partying.lcb.api.resp.BannerItem
import sg.partying.lcb.api.resp.LiveRecommendModel
import sg.partying.lcb.api.resp.Resp

interface HomePageService {

    @POST("banner/videoLiveFeed")
    suspend fun videoLiveFeed(): Resp<MutableList<BannerItem>>

    @POST("homepage/recommendedRoomList")
    suspend fun recommendedRoomList(): Resp<MutableList<BannerItem>>

    @Headers("Content-Type: application/octet-stream")
    @POST("go/ps/feed/recommendLiveChatRoom/(PB)")
    suspend fun recommendLiveChatRoom(@Body req: ReqFeedRoom): ReqFeedRecommendRoom

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


}