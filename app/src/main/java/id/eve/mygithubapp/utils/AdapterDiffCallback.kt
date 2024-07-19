package id.eve.mygithubapp.utils

import androidx.recyclerview.widget.DiffUtil
import id.eve.core.domain.model.FollowerData
import id.eve.core.domain.model.FollowingData
import id.eve.core.domain.model.UserData

class AdapterDiffCallback(
    private val mOldDataList: List<UserData>,
    private val mNewDataList: List<UserData>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = mOldDataList.size

    override fun getNewListSize(): Int = mNewDataList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        mOldDataList[oldItemPosition].id == mNewDataList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldDataList[oldItemPosition]
        val newEmployee = mNewDataList[newItemPosition]
        return oldEmployee.login == newEmployee.login
    }
}

class FollowAdapterDiffCallback(
    private val mOldDataList: List<FollowerData>,
    private val mNewDataList: List<FollowerData>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = mOldDataList.size

    override fun getNewListSize(): Int = mNewDataList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        mOldDataList[oldItemPosition].id == mNewDataList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldDataList[oldItemPosition]
        val newEmployee = mNewDataList[newItemPosition]
        return oldEmployee.login == newEmployee.login
    }
}

class FollowingAdapterDiffCallback(
    private val mOldDataList: List<FollowingData>,
    private val mNewDataList: List<FollowingData>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = mOldDataList.size

    override fun getNewListSize(): Int = mNewDataList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        mOldDataList[oldItemPosition].id == mNewDataList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldDataList[oldItemPosition]
        val newEmployee = mNewDataList[newItemPosition]
        return oldEmployee.login == newEmployee.login
    }
}