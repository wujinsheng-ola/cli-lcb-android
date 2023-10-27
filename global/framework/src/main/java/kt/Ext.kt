package kt

import android.view.View
import android.widget.Toast
import com.salton123.app.BaseApplication
import com.salton123.callback.SingleClickListener
import com.salton123.log.XLog
import com.salton123.utils.task.ThreadQueue


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