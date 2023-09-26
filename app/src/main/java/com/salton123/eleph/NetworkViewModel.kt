package com.salton123.eleph

import androidx.lifecycle.MutableLiveData
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState
import me.hgj.jetpackmvvm.state.paresResult
import sg.partying.lcb.android.api.apiService
import sg.partying.lcb.android.api.resp.LoginOption
import sg.partying.lcb.android.api.resp.Resp

/**
 * Time:2023/9/26 16:49
 * Author:
 * Description:
 */
class NetworkViewModel: BaseViewModel() {
    val niceNameData by lazy { MutableLiveData<ResultState<Resp<LoginOption>>>() }

    fun loginOptions() {
        requestNoCheck({
            apiService.loginOptions()
        }, { apiResponse ->
            niceNameData.paresResult(apiResponse)
        }, {}, true)
    }
}