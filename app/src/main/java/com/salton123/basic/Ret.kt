package com.salton123.basic

import androidx.annotation.Keep


sealed class Ret<T> {

    /**
     * 调用执行成功，返回结果[value]
     */
    @Keep
    data class Success<T>(val value: T) : Ret<T>()

    /**
     * 调用执行失败
     * 错误码[errorCode]，不填默认-1
     * 错误信息[errorMessage]，不填默认为空字符串
     * 异常[throwable]，不填默认为空
     * 额外错误信息[extras]，不填默认为空
     */
    @Keep
    data class Failure<T> constructor(
        val errorCode: Int = -1,
        val errorMessage: String = "",
        val throwable: Throwable? = null
    ) : Ret<T>() {
        var extras: Any? = null

        constructor(
            errorCode: Int = -1,
            errorMessage: String = "",
            throwable: Throwable? = null,
            extras: Any? = null
        ) : this(errorCode, errorMessage, throwable) {
            this.extras = extras
        }
    }

    /**
     * 不关心成功与否，只要取值
     */
    fun getOrNull() = if (this is Success) value else null
}

/**
 * 有些操作只需返回成功[Ret.Success]或者失败[Ret.Failure]，成功的时候没有额外的数据
 */
typealias ResultWithoutValue = Ret<Unit>