package sg.olaparty.network.adapter

import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.http.Body
import java.lang.reflect.Type

/**
 * Time:2023/10/27 14:32
 * Author:
 * Description:
 */
class WireCallAdapterFactory : CallAdapter.Factory() {
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

//    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
//        val rawType = getRawType(returnType)
//        println(rawType)
//        return null
//    }
}