package com.salton123.base.feature

import android.Manifest
import android.annotation.SuppressLint
import com.blankj.utilcode.util.PermissionUtils

/**
 * User: newSalton@outlook.com
 * Date: 2018/12/25 4:59 PM
 * ModifyTime: 4:59 PM
 * Description:
 */
@SuppressLint("ValidFragment")
class PermissionFeature(var callback: ((Boolean) -> Unit)? = null) : IFeature, PermissionUtils.SimpleCallback {

    private val permissionArr: Array<String>
        get() = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA, Manifest.permission.VIBRATE,
        )

    override fun onBind() {
        PermissionUtils.permission(*permissionArr).callback(this).request()
    }

    override fun onGranted() {
        callback?.invoke(true)
    }

    override fun onDenied() {
        callback?.invoke(false)
    }


    override fun onUnBind() {

    }
}