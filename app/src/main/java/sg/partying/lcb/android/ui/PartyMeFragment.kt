package sg.partying.lcb.android.ui

import android.os.Bundle
import android.widget.TextView
import com.salton123.base.BaseFragment
import com.salton123.eleph.ui.LoginActivity
import kt.singleClick
import sg.partying.lcb.android.R
import sg.partying.lcb.android.Session

class PartyMeFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.fragment_party_me

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        f<TextView>(R.id.tvLogout).singleClick {
            Session.uid = 0
            Session.token = ""
            activity?.finish()
            openActivity(LoginActivity::class.java, Bundle())
        }

    }
}