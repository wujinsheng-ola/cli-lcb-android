package sg.olaparty.network

import androidx.lifecycle.liveData
import com.salton123.coroutine.Ret
import com.salton123.log.XLog
import sg.partying.lcb.api.resp.Resp

private const val TAG = "LiveDataUtils"

fun <T> liveDataModel(block: suspend () -> Resp<T>) =
    liveData {
        val result = try {
            val baseModel = block()
            if (baseModel.success) {
                val model = baseModel.data
                Ret.onSuccess(model)
            } else {
                XLog.e(TAG, "[liveDataModel] load error:${baseModel.msg}")
                Ret.onFailure(-1, baseModel.msg)
            }
        } catch (e: Exception) {
            XLog.e(TAG, "[liveDataModel] catch error:$e")
            Ret.onFailure(-1, e.stackTraceToString(), e)
        }
        emit(result)
    }

fun <T> liveDataFire(block: suspend () -> Result<T>) =
    liveData {
        val result = try {
            block()
        } catch (e: Exception) {
            XLog.e(TAG, "[liveDataFire] catch error:$e")
            Result.failure(e)
        }
        emit(result)
    }