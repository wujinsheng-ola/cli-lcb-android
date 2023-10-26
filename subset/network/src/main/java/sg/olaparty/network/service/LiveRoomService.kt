package sg.olaparty.network.service

import pb.RspActionEnterV2
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import sg.partying.lcb.api.resp.LoginOption
import sg.partying.lcb.api.resp.LoginRet
import sg.partying.lcb.api.resp.Resp

interface LiveRoomService {

    @FormUrlEncoded
    @POST("go/room/action/enter/(PB)")
    @Headers("Content-Type: application/x-www-form-urlencoded; charset=utf-8")
    suspend fun joinRoom(
        @Field("rid") rid: String,
        @Field("password") password: String,
        @Field("inviter_uid") inviterUid: String,
        @Field("user_memory") userMemory: String,
    ): RspActionEnterV2

}