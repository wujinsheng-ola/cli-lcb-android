package kt

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salton123.app.BaseApplication
import com.salton123.callback.SingleClickListener
import com.salton123.framework.BuildConfig
import com.salton123.log.XLog
import com.salton123.utils.task.ThreadQueue
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.util.loge
import me.hgj.jetpackmvvm.network.AppException
import me.hgj.jetpackmvvm.network.ExceptionHandle


/**
 * Time:2022/1/29 5:18 上午
 * Author:
 * Description:
 */
fun Any.executeByCached(task: () -> Unit) {
    ThreadQueue.execOnCache {
        task.invoke()
    }
}

fun Any.executeByIo(task: () -> Unit) {
    ThreadQueue.post {
        task.invoke()
    }
}

fun Any.runOnUi(task: () -> Unit) {
    ThreadQueue.postOnUi {
        task.invoke()
    }
}


fun Any.runOnUiDelay(task: () -> Unit, timeMills: Long) {
    ThreadQueue.postOnUiDelay({
        task.invoke()
    }, timeMills)
}

fun Any.log(tag: String, msg: String) {
    XLog.i(tag, msg)
}

fun Any.toast(msg: String) {
    Toast.makeText(BaseApplication.sInstance, msg, Toast.LENGTH_SHORT).show()
}

fun Int.toast() {
    Toast.makeText(BaseApplication.sInstance, this, Toast.LENGTH_SHORT).show()
}


fun Int.getString(): String {
    return BaseApplication.sInstance.getString(this)
}


fun Int.getDimension(): Float {
    return BaseApplication.sInstance.resources.getDimension(this)
}

fun Int.getColor(): Int {
    return BaseApplication.sInstance.resources.getColor(this)
}

fun View.singleClick(interval: Int = 1000, callback: ((View?) -> Unit)?) {
    setOnClickListener(object : SingleClickListener(interval) {
        override fun onSingleClick(v: View?) {
            callback?.invoke(v)
        }
    })
}


/**
 *  不过滤请求结果
 * @param block 请求体 必须要用suspend关键字修饰
 * @param success 成功回调
 * @param error 失败回调 可不给
 */
fun <T> ViewModel.requestNoCheck(
    block: suspend () -> T,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
): Job {
    //如果需要弹窗 通知Activity/fragment弹窗
    return viewModelScope.launch {
        runCatching {
            //请求体
            block()
        }.onSuccess {
            //成功回调
            success(it)
        }.onFailure {
            //打印错误栈信息
            XLog.e("requestNoCheck", it.toString())
        }
    }
}

