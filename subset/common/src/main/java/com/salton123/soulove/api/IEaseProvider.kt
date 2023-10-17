package com.salton123.soulove.api

import androidx.lifecycle.LiveData
import com.alibaba.android.arouter.facade.template.IProvider
import com.salton123.coroutine.RetDef

/**
 * Time:2022/7/17 22:14
 * Author:
 * Description:
 */
interface IEaseProvider : IProvider {
    suspend fun autoLogin(): RetDef

    fun login(account: String, password: String): LiveData<RetDef>

    fun register(account: String, password: String): LiveData<RetDef>
}