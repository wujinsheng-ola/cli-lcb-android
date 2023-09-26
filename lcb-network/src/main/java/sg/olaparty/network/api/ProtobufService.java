package sg.olaparty.network.api;

import com.dragonplus.network.api.protocol.Account;
import com.dragonplus.network.api.protocol.ColorFeverApi;
import com.dragonplus.network.api.protocol.Others;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * User: newSalton@outlook.com
 * Date: 2019/8/12 14:08
 * ModifyTime: 14:08
 * Description:
 */
public interface ProtobufService {

    @POST("/api/account/login/(POST)")
    Observable<Account.SLogin> login(
            @HeaderMap Map<String, String> headers,
            @Body RequestBody body);

    @Headers({
            "x-debug-platform:0",
            "x-debug-player-id: 10"
    })
    @GET("/api/color_fever/color_banner/list")
    Observable<ColorFeverApi.ColorBanner> colorBannerList();

    @POST("/api/account/login/(POST)")
    Observable<Account.SLogin> loginNew(@Body RequestBody body);

    @POST("/api/color_fever/color_card/feeds/(GET)")
    Observable<ColorFeverApi.SGetFeeds> CGetFeeds();


    @POST("/api/color_fever/color_card/feeds/v2/(GET)")
    Observable<ColorFeverApi.SGetFeedsV2> CGetFeedsV2();

    @POST("/api/color_fever/color_card/daily/(GET)")
    Observable<ColorFeverApi.SGetDailyCards> CGetDailyCards();

    @POST("/api/color_fever/color_card/by_category/(GET)")
    Observable<ColorFeverApi.SListByCategory> CListByCategory();

    @POST("/api/color_fever/color_banner/list/(GET)")
    Observable<ColorFeverApi.SListColorBannersForFever> CListColorBannersForFever();

    @POST("/client_config/(GET)")
    Observable<Others.SGetConfig> CGetConfig();

    @POST("/api/account/login/(POST)")
    Call<Account.SLogin> loginSync(@Body RequestBody body);
}
