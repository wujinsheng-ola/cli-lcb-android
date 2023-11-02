package sg.olaparty.network

import sg.olaparty.network.base.NetworkConfigProvider
import sg.olaparty.network.base.ServiceCreator
import sg.olaparty.network.service.HomePageService
import sg.olaparty.network.service.LiveRoomService
import sg.olaparty.network.service.ProfileService

/**
 * Time:2023/10/26 15:22
 * Author:
 * Description:
 */
object RequestCenter {
    val profileService: ProfileService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        ServiceCreator.getApi(ProfileService::class.java, NetworkConfigProvider.API_BASE_URL)
    }

    val homePageService: HomePageService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        ServiceCreator.getApi(HomePageService::class.java, NetworkConfigProvider.API_BASE_URL)
    }

    val liveRoomService: LiveRoomService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        ServiceCreator.getApi(LiveRoomService::class.java, NetworkConfigProvider.API_BASE_URL)
    }

}