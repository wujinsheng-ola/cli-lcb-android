package sg.partying.lcb.profile.ui

import android.os.Bundle
import android.view.View
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
import kt.singleClick
import sg.partying.lcb.AppProperty
import sg.partying.lcb.android.Session
import sg.partying.lcb.util.GlideEngine
import sg.partying.lcb.util.ImageFileCompressEngine
import sg.partying.lcb.util.ImageFileCropEngine
import sg.partying.lcb.util.ImageLoader
import sg.partying.lcb.util.PartyPictureSelectorStyle

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
                                ImageLoader.loadCenterCrop(ivAvatar, result[0].realPath)
                            }
                        }

                        override fun onCancel() {
                        }
                    })
            }
        }

    }
}