package sg.partying.lcb.android.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.salton123.base.BaseFragment
import com.salton123.profile.databinding.ActivityUserProfileBinding
import com.salton123.soulove.api.RouterManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kt.singleClick
import pb.ReqGiftConfig
import sg.olaparty.network.RequestCenter.liveRoomService
import sg.olaparty.network.base.NetworkConfigProvider
import sg.olaparty.network.base.ServiceCreator
import sg.partying.lcb.android.R
import sg.partying.lcb.android.Session
import sg.partying.lcb.util.ImageLoader
import sg.partying.lcb.AppProperty
import sg.partying.lcb.android.databinding.FragmentPartyMeBinding

class PartyMeFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_party_me

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        val binding = FragmentPartyMeBinding.bind(rootView)
        binding.tvName.text = Session.name.ifEmpty { "未登录" }
        binding.tvUid.text = "${Session.uid}"
        ImageLoader.loadCenterCrop(binding.ivAvatar, AppProperty.RESOURCE_PREFIX_URL + Session.icon)
        binding.tvRecharge.singleClick {

        }
        binding.ivAvatar.singleClick {
            RouterManager.goEditProfile(activity())
//            PictureSelector.create(this@PartyMeFragment)
//                .openGallery(SelectMimeType.ofImage())
//                .isDisplayCamera(true)
//                .setCropEngine(ImageFileCropEngine())
//                .isCameraForegroundService(true)
//                .setMaxSelectNum(1)
//                .setImageEngine(GlideEngine.createGlideEngine())
//                .forResult(object : OnResultCallbackListener<LocalMedia> {
//                    override fun onResult(result: ArrayList<LocalMedia>?) {
//                        println(result)
//                        Glide.with(ivAvatar).load(result?.get(0)!!.realPath).into(ivAvatar)
//                    }
//
//                    override fun onCancel() {
//                    }
//                })
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK) {
//            when (requestCode) {
//                PictureConfig.CHOOSE_REQUEST,
//                PictureConfig.REQUEST_CAMERA -> {
//                    val phoneList = PictureSelector.obtainMultipleResult(data)
//                }
//            }
//        }
//    }
}
