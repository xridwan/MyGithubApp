package id.eve.mygithubapp.presenter.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.eve.mygithubapp.presenter.follow.FollowFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    var username: String? = null

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowFragment.newInstance(username, 1)
            1 -> fragment = FollowFragment.newInstance(username, 2)
        }
        return fragment as Fragment
    }
}