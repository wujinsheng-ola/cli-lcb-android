package sg.olaparty.network.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salton123.coroutine.Ret
import com.salton123.log.XLog
import pb.ReqFeedRecommendRoom
import pb.ReqFeedRoom
import sg.olaparty.network.RequestCenter.homePageService
import sg.olaparty.network.RequestCenter.loginService
import sg.olaparty.network.request
import sg.olaparty.network.success
import sg.partying.lcb.api.resp.BannerItem
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
    private val videoLiveFeedRet by lazy { MutableLiveData<Ret<Resp<MutableList<BannerItem>>>>() }
    private val recommendLiveChatRoom by lazy { MutableLiveData<Ret<ReqFeedRecommendRoom>>() }
    private val liveLiveRecommendModel by lazy { MutableLiveData<Ret<Resp<LiveRecommendModel>>>() }

    fun loginOptions(): MutableLiveData<Ret<Resp<LoginOption>>> {
        request({
            loginService.loginOptions()
        }, { apiResponse ->
            loginOptionsRet.success(apiResponse)
        }, { errorCode: Int, errorMessage: String, throwable: Throwable? ->
            XLog.e(TAG, "[onFailed] error:$errorCode,$errorMessage,$throwable")
        })
        return loginOptionsRet
    }

    fun recommendBanner(type: String): MutableLiveData<Ret<Resp<MutableList<BannerItem>>>> {
        request({
            if (type == "liveroom") {
                homePageService.videoLiveFeed()
            } else {
                homePageService.recommendedRoomList()
            }
        }, { apiResponse ->
            videoLiveFeedRet.success(apiResponse)
        }, { errorCode: Int, errorMessage: String, throwable: Throwable? ->
            XLog.e(TAG, "[onFailed] error:$errorCode,$errorMessage,$throwable")
        })
        return videoLiveFeedRet
    }

    fun recommendLiveChatRoom(req: ReqFeedRoom): MutableLiveData<Ret<ReqFeedRecommendRoom>> {
        request({
            homePageService.recommendLiveChatRoom(req)
        }, { apiResponse ->
            recommendLiveChatRoom.success(apiResponse)
        }, { errorCode: Int, errorMessage: String, throwable: Throwable? ->
            XLog.e(TAG, "[onFailed] error:$errorCode,$errorMessage,$throwable")
        })
        return recommendLiveChatRoom
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