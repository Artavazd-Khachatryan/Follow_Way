package com.useruser.followay.domain.addapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.useruser.followay.R
import com.useruser.followay.databinding.InvatedUserListItemBinding


class InvitedUsersAdapter :
    ListAdapter<InvitedUserModel, InvatedUserViewHolder>(InvitedUsersListDiffCallback()) {

    private val footer = InvitedUserModel(-1)
    var listAddClick: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvatedUserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = InvatedUserListItemBinding.inflate(layoutInflater, parent, false)

        return InvatedUserViewHolder(binding)

    }

    override fun onBindViewHolder(holder: InvatedUserViewHolder, position: Int) {
        holder.bind(getItem(position))
        listAddClick?.let { holder.addClick = it }
    }

    override fun submitList(list: MutableList<InvitedUserModel>?) {
        list?.let { it += footer }
        super.submitList(list)
    }
}

class InvatedUserViewHolder(private val binding: InvatedUserListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object

    val TAG = "InvatedUserViewHolder"
    val user = ObservableField<InvitedUserModel>()
    var addClick: (() -> Unit) = {}


    init {
        binding.viewHolder = this
    }


    fun bind(userModel: InvitedUserModel) {
        user.set(userModel)


        //set color
        if (userModel.color != 0 && userModel.color != -1) {
            binding.colorLayout.setBackgroundColor(userModel.color)

            Log.d(TAG, "----------------------------set color---------------------")
        }
        //footer
        else if (userModel.color == -1) {
            binding.colorLayout.setBackgroundResource(R.drawable.ic_add)
            Log.d(TAG, "---------------------------set ic_add----------------------")

        }//load image
        else {
            binding.ivUserImage.load(userModel.image)
            Log.d(TAG, "----------------------------set image---------------------")

        }
    }

    fun itemClick(view: View) {
        addClick()
    }

}

data class InvitedUserModel(val color: Int = 0, val image: String = "")


class InvitedUsersListDiffCallback : DiffUtil.ItemCallback<InvitedUserModel>() {
    override fun areItemsTheSame(oldItem: InvitedUserModel, newItem: InvitedUserModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: InvitedUserModel, newItem: InvitedUserModel): Boolean {
        return oldItem == newItem
    }

}