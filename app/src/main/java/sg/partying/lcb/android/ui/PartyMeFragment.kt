package sg.partying.lcb.android.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.salton123.base.BaseFragment
import com.salton123.soulove.api.RouterManager
import kt.singleClick
import sg.olaparty.network.base.NetworkConfigProvider
import sg.partying.lcb.android.R
import sg.partying.lcb.android.Session
import sg.partying.lcb.util.ImageLoader
import sg.partying.lcb.AppProperty

class PartyMeFragment : BaseFragment() {
    private lateinit var ivAvatar: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvUid: TextView
    override fun getLayout(): Int = R.layout.fragment_party_me

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        ivAvatar = f(R.id.ivAvatar)
        tvName = f(R.id.tvName)
        tvUid = f(R.id.tvUid)
        tvName.text = Session.name.ifEmpty { "未登录" }
        tvUid.text = "${Session.uid}"
//        f<TextView>(R.id.tvLogout).singleClick {
//            Session.uid = 0
//            Session.token = ""
//            activity?.finish()
//            openActivity(LoginActivity::class.java, Bundle())
//        }
        ImageLoader.loadCenterCrop(ivAvatar, AppProperty.RESOURCE_PREFIX_URL + Session.icon)
        ivAvatar.singleClick {
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
