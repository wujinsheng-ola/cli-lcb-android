package sg.olaparty.network

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salton123.coroutine.Ret
import com.salton123.log.XLog
import kotlinx.coroutines.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/4/8
 * 描述　:BaseViewModel请求协程封装
 */

/**
 *  不过滤请求结果
 * @param block 请求体 必须要用suspend关键字修饰
 * @param success 成功回调
 * @param error 失败回调 可不给
 *
 */
fun <T> ViewModel.request(
    block: suspend () -> T,
    success: (T) -> Unit,
    failed: (errorCode: Int, errorMessage: String, throwable: Throwable?) -> Unit = { errorCode, errorMessage, throwable ->
        XLog.e("Network", "[requestNoCheck] error:$errorCode,$errorMessage,$throwable")
    }
): Job {
    return viewModelScope.launch {
        runCatching {
            //请求体
            block()
        }.onSuccess {
            //成功回调
            success(it)
        }.onFailure {
            //打印错误栈信息
            it.printStackTrace()
            //失败回调
            failed(-1, it.stackTraceToString(), null)
        }
    }
}

/**
 *  调用携程
 * @param block 操作耗时操作任务
 * @param success 成功回调
 * @param error 失败回调 可不给
 */
fun <T> ViewModel.launch(
    block: () -> T,
    success: (T) -> Unit,
    error: (Throwable) -> Unit = {}
) {
    viewModelScope.launch {
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                block()
            }
        }.onSuccess {
            success(it)
        }.onFailure {
            error(it)
        }
    }
}


fun <T> MutableLiveData<Ret<T>>.success(result: T) {
    value = Ret.onSuccess(result)
}

fun <T> MutableLiveData<Ret<T>>.failed(errorCode: Int = -1, errorMessage: String = "", throwable: Throwable? = null) {
    value = Ret.onFailure(errorCode, errorMessage, throwable)
}


