package sg.partying.lcb.api

import sg.partying.lcb.config.NetworkConfigProvider


//双重校验锁式-单例 封装NetApiService 方便直接快速调用简单的接口
val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiService::class.java, NetworkConfigProvider.API_BASE_URL)
}

val pbApiService: PbApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    PbNetworkApi.INSTANCE.getApi(PbApiService::class.java, NetworkConfigProvider.API_BASE_URL)
}

