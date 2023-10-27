package sg.olaparty.network.custom

/**
 * Time:2023/10/27 15:30
 * Author:
 * Description:
 */
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.http.Body
import sg.olaparty.network.annotation.CustomParam
import java.lang.reflect.Type

class CustomParamFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        annotations.forEach { annotation ->
            if (annotation is Body) {
                return object : CallAdapter<Any, Any> {
                    override fun adapt(call: retrofit2.Call<Any>): Any {
                        return call
                    }

                    override fun responseType(): Type {
                        return returnType
                    }
                }
            }
        }
        return null
    }
}