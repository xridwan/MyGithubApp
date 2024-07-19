package id.eve.mygithubapp.presenter.following

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.eve.core.domain.model.FollowingData
import id.eve.mygithubapp.R
import id.eve.mygithubapp.databinding.UserItemLayoutBinding
import id.eve.mygithubapp.utils.FollowingAdapterDiffCallback

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    private val followingList = ArrayList<FollowingData>()

    fun setData(followingList: List<FollowingData>) {
        val diffCallback = FollowingAdapterDiffCallback(this.followingList, followingList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.followingList.clear()
        this.followingList.addAll(followingList)
        diffResult.dispatchUpdatesTo(this)
    }

    class FollowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserItemLayoutBinding.bind(itemView)
        fun bind(userData: FollowingData) {
            binding.apply {
                tvLogin.text = userData.login
                Picasso.get().load(userData.avatarUrl).into(imgAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item_layout, parent, false)
        return FollowingViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(followingList[position])
    }

    override fun getItemCount(): Int = followingList.size
}