package sg.olaparty.network.annotation

/**
 * Time:2023/10/27 15:29
 * Author:
 * Description:
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class CustomParam(val value: String)
