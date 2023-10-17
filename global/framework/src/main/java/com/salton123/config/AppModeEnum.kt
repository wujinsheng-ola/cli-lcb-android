package com.salton123.config

import androidx.annotation.IntDef

/**
 * Time:2023/10/17 17:57
 * Author:
 * Description:
 */

object AppModeEnum {
    const val Debug = 2
    const val Alpha = 1
    const val Product = 0

    @IntDef(value = [Debug, Alpha, Product])
    @Retention(AnnotationRetention.SOURCE)
    annotation class AppMode
}

