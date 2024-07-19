package id.eve.mygithubapp.presenter.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.eve.core.domain.model.UserData
import id.eve.mygithubapp.R
import id.eve.mygithubapp.databinding.UserItemLayoutBinding
import id.eve.mygithubapp.utils.AdapterDiffCallback

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val mainList = ArrayList<UserData>()
    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemCLickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    fun setData(mainList: List<UserData>) {
        val diffCallback = AdapterDiffCallback(this.mainList, mainList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.mainList.clear()
        this.mainList.addAll(mainList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserItemLayoutBinding.bind(itemView)
        fun bind(userData: UserData) {
            binding.apply {
                tvLogin.text = userData.login
                Picasso.get().load(userData.avatarUrl).into(imgAvatar)
            }

            itemView.setOnClickListener {
                onItemClickCallBack.onItemClicked(userData)
            }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: UserData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(mainList[position])
    }

    override fun getItemCount(): Int = mainList.size
}