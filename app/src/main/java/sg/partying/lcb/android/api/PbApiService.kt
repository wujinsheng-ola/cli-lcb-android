package sg.partying.lcb.android.api

import pb.ReqFeedRecommendRoom
import pb.ReqFeedRoom
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Streaming
import sg.partying.lcb.android.api.resp.PbResp

interface PbApiService {
    @Headers("Content-Type:application/octet-stream")
    @POST("go/ps/feed/recommendLiveChatRoom")
    suspend fun recommendLiveChatRoom(@Body req: ReqFeedRoom): PbResp<ReqFeedRecommendRoom>

}
