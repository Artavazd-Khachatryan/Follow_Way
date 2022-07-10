package com.useruser.followay.domain.addapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.useruser.followay.databinding.UserTragectoryListItemBinding


data class UserTrajectoryModel(val street: String, val time: String)


class UserTrajectoryListAdapter :
    ListAdapter<UserTrajectoryModel, UserTrajectoryViewHolder>(UserTrajectoryListAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserTrajectoryViewHolder {
        val inflater = LayoutInflater.from(parent.context!!)
        val binding = UserTragectoryListItemBinding.inflate(inflater, parent, false)
        return UserTrajectoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserTrajectoryViewHolder, position: Int) {
        holder.bind(getItem(position))

    }
}

class UserTrajectoryViewHolder(private val binding: UserTragectoryListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: UserTrajectoryModel) {
        binding.model = model
    }

}


private class UserTrajectoryListAdapterDiffCallback : DiffUtil.ItemCallback<UserTrajectoryModel>() {
    override fun areItemsTheSame(
        oldItem: UserTrajectoryModel,
        newItem: UserTrajectoryModel
    ): Boolean {

        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: UserTrajectoryModel,
        newItem: UserTrajectoryModel
    ): Boolean {

        return oldItem == newItem
    }

}