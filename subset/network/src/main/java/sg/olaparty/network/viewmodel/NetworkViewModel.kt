package sg.olaparty.network.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kt.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState
import me.hgj.jetpackmvvm.state.paresResult
import pb.ReqFeedRecommendRoom
import pb.ReqFeedRoom
import sg.olaparty.network.RequestCenter.homePageService
import sg.olaparty.network.RequestCenter.loginService
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
    private val loginOptionsRet by lazy { MutableLiveData<ResultState<Resp<LoginOption>>>() }
    private val videoLiveFeedRet by lazy { MutableLiveData<ResultState<Resp<MutableList<BannerItem>>>>() }
    private val recommendLiveChatRoom by lazy { MutableLiveData<ResultState<ReqFeedRecommendRoom>>() }
    private val liveLiveRecommendModel by lazy { MutableLiveData<ResultState<Resp<LiveRecommendModel>>>() }

    fun loginOptions(): MutableLiveData<ResultState<Resp<LoginOption>>> {
        requestNoCheck({
            loginService.loginOptions()
        }, { apiResponse ->
            loginOptionsRet.paresResult(apiResponse)
        }, {
            it.printStackTrace()
        })
        return loginOptionsRet
    }

    fun videoLiveFeed(): MutableLiveData<ResultState<Resp<MutableList<BannerItem>>>> {
        requestNoCheck({
            homePageService.videoLiveFeed()
        }, { apiResponse ->
            videoLiveFeedRet.paresResult(apiResponse)
        }, {
            it.printStackTrace()
        })
        return videoLiveFeedRet
    }

    fun recommendLiveChatRoom(req: ReqFeedRoom): MutableLiveData<ResultState<ReqFeedRecommendRoom>> {
        requestNoCheck({
            homePageService.recommendLiveChatRoom(req)
        }, { apiResponse ->
            recommendLiveChatRoom.paresResult(apiResponse)
        }, {
            it.printStackTrace()
        })
        return recommendLiveChatRoom
    }

    fun getRecommend(page: Int, limit: Int, feedType: String): MutableLiveData<ResultState<Resp<LiveRecommendModel>>> {
        requestNoCheck({
            homePageService.getRecommend(page, 3, limit, 0, feedType,
                "0", "0", "1", "0", "", "", "")
        }, { apiResponse ->
            liveLiveRecommendModel.paresResult(apiResponse)
        }, {
            it.printStackTrace()
        })
        return liveLiveRecommendModel
    }
}