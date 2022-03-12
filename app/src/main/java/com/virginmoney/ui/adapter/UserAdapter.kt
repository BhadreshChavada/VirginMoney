package com.virginmoney.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.virginmoney.R
import com.virginmoney.databinding.AdapterUserBinding
import com.virginmoney.model.UserData

class UserAdapter(private val context: Context) :
    ListAdapter<UserData, RecyclerView.ViewHolder>(USER_DETAIL_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            AdapterUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), context
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as UserViewHolder).bind(item)
    }

    class UserViewHolder(
        private val binding: AdapterUserBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemUserData: UserData) {
            binding.apply {
                userData = itemUserData
                executePendingBindings()

                Glide.with(context).load(itemUserData.avatar)
                    .error(R.drawable.ic_user)
                    .placeholder(R.drawable.ic_user)
                    .into(binding.ivUser)

            }


        }


    }

    companion object {
        private val USER_DETAIL_UTIL = object : DiffUtil.ItemCallback<UserData>() {
            override fun areItemsTheSame(
                oldItem: UserData,
                newItem: UserData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UserData,
                newItem: UserData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}