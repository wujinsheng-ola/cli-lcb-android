package com.salton123.eleph

import androidx.lifecycle.MutableLiveData
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.requestNoCheck
import me.hgj.jetpackmvvm.state.ResultState
import me.hgj.jetpackmvvm.state.paresResult
import retrofit2.http.Field
import sg.partying.lcb.android.api.apiService
import sg.partying.lcb.android.api.resp.LoginOption
import sg.partying.lcb.android.api.resp.Resp

/**
 * Time:2023/9/26 16:49
 * Author:
 * Description:
 */
class NetworkViewModel : BaseViewModel() {
    private val loginOptionsRet by lazy { MutableLiveData<ResultState<Resp<LoginOption>>>() }
    private val passwordLoginRet by lazy { MutableLiveData<ResultState<Resp<LoginOption>>>() }

    fun loginOptions(): MutableLiveData<ResultState<Resp<LoginOption>>> {
        requestNoCheck({
            apiService.loginOptions()
        }, { apiResponse ->
            loginOptionsRet.paresResult(apiResponse)
        }, {
            it.printStackTrace()
        }, true)
        return loginOptionsRet
    }

//    fun passwordLogin(
//        mobile: String,
//        password: String,
//        area: String,
//    ): MutableLiveData<ResultState<Resp<LoginOption>>> {
//        requestNoCheck({
//            apiService.passwordLogin(mobile, password, area, "", "")
//        }, { apiResponse ->
//            passwordLoginRet.paresResult(apiResponse)
//        }, {
//            it.printStackTrace()
//        }, true)
//        return passwordLoginRet
//    }
}