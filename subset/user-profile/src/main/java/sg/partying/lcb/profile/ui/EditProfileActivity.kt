package sg.partying.lcb.profile.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.salton123.base.DelegateActivity
import com.salton123.base.feature.ImmersionFeature
import com.salton123.profile.R
import com.salton123.profile.databinding.ActivityUserProfileBinding
import com.salton123.soulove.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kt.singleClick
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import sg.olaparty.network.RequestCenter
import sg.partying.lcb.AppProperty
import sg.partying.lcb.android.Session
import sg.partying.lcb.util.GlideEngine
import sg.partying.lcb.util.ImageFileCompressEngine
import sg.partying.lcb.util.ImageFileCropEngine
import sg.partying.lcb.util.ImageLoader
import sg.partying.lcb.util.PartyPictureSelectorStyle
import java.io.File


/**
 * Time:2023/11/1 14:25
 * Author:
 * Description:
 */
@Route(path = Constants.Router.Profile.EDIT)
class EditProfileActivity : DelegateActivity() {
    override fun getLayout(): Int = R.layout.activity_user_profile

    override fun initVariable(savedInstanceState: Bundle?) {
        addFeature(ImmersionFeature(this))
    }

    override fun initViewAndData() {
//        tvTitle.setText("修改个人资料")
        val binding = ActivityUserProfileBinding.bind(rootView)
        binding.apply {
            tvBack.singleClick { finish() }
            tvMore.visibility = View.GONE
            tvTitle.text = getString(R.string.edit_profile)
            tvSubTitle.text = getString(R.string.complete_status, "30%")
            ImageLoader.loadCenterCrop(ivAvatar, AppProperty.RESOURCE_PREFIX_URL + Session.icon)
            ivAvatar.singleClick {
                PictureSelector.create(this@EditProfileActivity)
                    .openGallery(SelectMimeType.ofImage())
                    .isDisplayCamera(true)
                    .setCropEngine(ImageFileCropEngine(true))
                    .isCameraForegroundService(true)
                    .setMaxSelectNum(1)
                    .setSelectorUIStyle(PartyPictureSelectorStyle(activity()))
                    .setCompressEngine(ImageFileCompressEngine())
                    .setImageEngine(GlideEngine.createGlideEngine())
                    .forResult(object : OnResultCallbackListener<LocalMedia> {
                        override fun onResult(result: ArrayList<LocalMedia>?) {
                            if (!result.isNullOrEmpty()) {
                                val path = result[0].cutPath
                                ImageLoader.loadCenterCrop(ivAvatar, path)
                                uploadAvatar(path)
                            }
                        }

                        override fun onCancel() {
                        }
                    })
            }
        }
        accountInfo()
    }

    private fun accountInfo() {
        lifecycleScope.launch(Dispatchers.IO) {
            RequestCenter.profileService.accountInfo()
        }
    }

    private fun uploadAvatar(localFilePath: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val file = File(localFilePath)
            val requestFile = file.asRequestBody("image/*".toMediaType())
            val body = MultipartBody.Part.createFormData("image", file.name, requestFile)
            val resp = RequestCenter.profileService.uploadAvatar(body)
            println(resp.data)
//            {"success":true,"data":{"name":"202311\/02\/816260300_654355fbcd32f1.33504396.jpg","width":900,"height":900}}
        }
    }
}