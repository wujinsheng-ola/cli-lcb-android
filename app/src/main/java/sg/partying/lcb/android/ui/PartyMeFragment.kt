package sg.partying.lcb.android.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.salton123.base.BaseFragment
import kt.singleClick
import sg.partying.lcb.android.R
import sg.partying.lcb.android.Session
import sg.partying.lcb.android.util.ImageLoader
import sg.olaparty.network.base.NetworkConfigProvider

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
        f<TextView>(R.id.tvLogout).singleClick {
            Session.uid = 0
            Session.token = ""
            activity?.finish()
            openActivity(LoginActivity::class.java, Bundle())
        }
        ImageLoader.loadCenterCrop(ivAvatar, NetworkConfigProvider.RESOURCE_PREFIX_URL + Session.icon)
    }
}