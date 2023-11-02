package sg.olaparty.network.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salton123.coroutine.Ret
import com.salton123.log.XLog
import pb.ReqFeedRoom
import pb.ResFeedRecommendRoom
import sg.olaparty.network.RequestCenter.homePageService
import sg.olaparty.network.RequestCenter.profileService
import sg.olaparty.network.request
import sg.olaparty.network.success
import sg.partying.lcb.api.resp.ChatBannerType
import sg.partying.lcb.api.resp.IBannerType
import sg.partying.lcb.api.resp.LiveBannerType
import sg.partying.lcb.api.resp.LiveRecommendModel
import sg.partying.lcb.api.resp.LoginOption
import sg.partying.lcb.api.resp.Resp

/**
 * Time:2023/9/26 16:49
 * Author:
 * Description:
 */
class NetworkViewModel : ViewModel() {
    companion object {
        private const val TAG = "NetworkViewModel"
    }

    private val loginOptionsRet by lazy { MutableLiveData<Ret<Resp<LoginOption>>>() }
    private val videoLiveFeedRet by lazy { MutableLiveData<Ret<MutableList<IBannerType>?>>() }
    private val recommendHomeRet by lazy { MutableLiveData<Ret<ResFeedRecommendRoom>>() }
    private val liveLiveRecommendModel by lazy { MutableLiveData<Ret<Resp<LiveRecommendModel>>>() }
//    private val accountInfoRet by lazy { MutableLiveData<Ret<Resp<LiveRecommendModel>>>() }

    fun loginOptions(): MutableLiveData<Ret<Resp<LoginOption>>> {
        request({
            profileService.loginOptions()
        }, { apiResponse ->
            loginOptionsRet.success(apiResponse)
        }, { errorCode: Int, errorMessage: String, throwable: Throwable? ->
            XLog.e(TAG, "[onFailed] error:$errorCode,$errorMessage,$throwable")
        })
        return loginOptionsRet
    }

    fun recommendBanner(type: String): MutableLiveData<Ret<MutableList<IBannerType>?>> {
        request({
            if (type == "liveroom") {
                homePageService.recommendLiveBanner().data?.map { LiveBannerType(it) }
            } else {
                homePageService.recommendChatBanner().data?.bannerList?.map { ChatBannerType(it) }
            }
        }, { apiResponse ->
            videoLiveFeedRet.success(apiResponse?.toMutableList())
        }, { errorCode: Int, errorMessage: String, throwable: Throwable? ->
            XLog.e(TAG, "[onFailed] error:$errorCode,$errorMessage,$throwable")
        })
        return videoLiveFeedRet
    }

    fun recommendHome(type: String, req: ReqFeedRoom): MutableLiveData<Ret<ResFeedRecommendRoom>> {
        request({
            if (type == "liveroom") {
                homePageService.recommendLiveChatRoom(req)
            } else {
                homePageService.recommendVoiceChatRoom(req)
            }
        }, { apiResponse ->
            recommendHomeRet.success(apiResponse)
        }, { errorCode: Int, errorMessage: String, throwable: Throwable? ->
            XLog.e(TAG, "[onFailed] error:$errorCode,$errorMessage,$throwable")
        })
        return recommendHomeRet
    }

    fun getRecommend(page: Int, limit: Int, feedType: String): MutableLiveData<Ret<Resp<LiveRecommendModel>>> {
        request({
            homePageService.getRecommend(page, 3, limit, 0, feedType,
                "0", "0", "1", "0", "", "", "")
        }, { apiResponse ->
            liveLiveRecommendModel.success(apiResponse)
        }, { errorCode: Int, errorMessage: String, throwable: Throwable? ->
            XLog.e(TAG, "[onFailed] error:$errorCode,$errorMessage,$throwable")
        })
        return liveLiveRecommendModel
    }
}