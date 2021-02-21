package com.mpg.shaadidemoapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mpg.shaadidemoapp.data.entity.UserEntity
import com.mpg.shaadidemoapp.databinding.HomeListRowBinding
import com.mpg.shaadidemoapp.ui.home.HomeViewModel
import javax.inject.Inject

/**
 * Adapter for the UserEntity list. Has a reference to the [HomeViewModel] to send actions back to it.
 */
class UserListAdapter @Inject constructor(private val viewModel: HomeViewModel) :
    ListAdapter<UserEntity, UserListAdapter.ViewHolder>(DeviceDiffCallback()) {
    private var itemClickListener: OnDeviceItemClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, viewModel, itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: HomeListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: UserEntity,
            viewModel: HomeViewModel,
            itemClickListener: OnDeviceItemClickListener?
        ) {
            binding.viewModel = viewModel
            binding.user = item
//            binding.ivDeleteRow.setOnClickListener {
//                itemClickListener?.onItemClicked(adapterPosition, item)
//            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HomeListRowBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    fun setItemClickListener(itemClicked: OnDeviceItemClickListener) {
        this.itemClickListener = itemClicked
    }
}

interface OnDeviceItemClickListener {
    fun onItemClicked(position: Int, item: UserEntity)
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class DeviceDiffCallback : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem.email == newItem.email
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }
}
