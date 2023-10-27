package com.salton123.coroutine

import androidx.lifecycle.MutableLiveData

/**
 * @Time:2021/3/26 17:49
 * @Author:wujinsheng
 * @Description:
 */
sealed class Ret<T> {

    companion object {
        fun <T> onSuccess(data: T): Ret<T> = Success(data)
        fun <T> onFailure(errorCode: Int = -1, errorMessage: String = "", throwable: Throwable? = null): Ret<T> = Failure(errorCode, errorMessage, throwable)
    }

    /**
     * 调用执行成功，返回结果[value]
     */
    data class Success<T>(val value: T) : Ret<T>()

    /**
     * 调用执行失败
     * 错误码[errorCode]，不填默认-1
     * 错误信息[errorMessage]，不填默认为空字符串
     * 异常[throwable]，不填默认为空
     */
    data class Failure<T>(
        val errorCode: Int = -1, val errorMessage: String = "", val throwable: Throwable? = null
    ) : Ret<T>()

    /**
     * 不关心成功与否，只要取值
     */
    fun getOrNull() = if (this is Success) value else null
}

/**
 * 有些操作只需返回成功[Ret.Success]或者失败[Ret.Failure]，成功的时候没有额外的数据
 */
typealias RetDef = Ret<Unit>