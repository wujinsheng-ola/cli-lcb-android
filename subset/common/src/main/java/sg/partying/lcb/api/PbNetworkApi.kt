package sg.partying.lcb.api

import retrofit2.Retrofit
import retrofit2.converter.wire.WireConverterFactory

class PbNetworkApi : NetworkApi() {
    companion object {
        val INSTANCE: NetworkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkApi()
        }
    }
    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder.apply {
            addConverterFactory(WireConverterFactory.create())
        }
    }
}



