package com.useruser.followay.domain.addapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.useruser.followay.databinding.StartInformationListItemBinding
import com.useruser.followay.domain.models.StartInformationItemModel

class StartInfoListAdapter :
    ListAdapter<StartInformationItemModel, StartInfoViewHolder>(StartInfoListAdapterDiffCallback()) {

    companion object

    val TAG = "StartInfoListAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartInfoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StartInformationListItemBinding.inflate(layoutInflater, parent, false)
        return StartInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StartInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class StartInfoViewHolder(private val binding: StartInformationListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val infoModel = ObservableField<StartInformationItemModel>()

    fun bind(model: StartInformationItemModel) {
        infoModel.set(model)
        binding.viewHolder = this
    }
}

private class StartInfoListAdapterDiffCallback :
    DiffUtil.ItemCallback<StartInformationItemModel>() {
    override fun areItemsTheSame(
        oldItem: StartInformationItemModel,
        newItem: StartInformationItemModel
    ): Boolean {

        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: StartInformationItemModel,
        newItem: StartInformationItemModel
    ): Boolean {

        return oldItem == newItem
    }
}