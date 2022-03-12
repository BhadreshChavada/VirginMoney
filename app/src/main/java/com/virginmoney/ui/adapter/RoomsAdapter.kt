package com.virginmoney.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.virginmoney.R
import com.virginmoney.databinding.AdapterRoomBinding
import com.virginmoney.model.RoomData

class RoomsAdapter(
    private val context: Context,
    private val itemClickListener: ItemClickListener
) :
    ListAdapter<RoomData, RecyclerView.ViewHolder>(ROOM_DETAIL_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RoomViewHolder(
            AdapterRoomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), context, itemClickListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as RoomViewHolder).bind(item)
    }

    class RoomViewHolder(
        private val binding: AdapterRoomBinding, private val context: Context,
        private val itemClickListener: ItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemRoomData: RoomData) {
            binding.apply {
                binding.roomData = itemRoomData

                if (itemRoomData.isOccupied) {
                    binding.txtRoomNumber.setBackgroundColor(context.resources.getColor(R.color.red))
                } else {
                    binding.txtRoomNumber.setBackgroundColor(context.resources.getColor(R.color.green))
                }
                executePendingBindings()

            }

            binding.root.setOnClickListener {

                itemClickListener.roomClicked(itemRoomData)

            }


        }


    }

    companion object {
        private val ROOM_DETAIL_UTIL = object : DiffUtil.ItemCallback<RoomData>() {
            override fun areItemsTheSame(
                oldItem: RoomData,
                newItem: RoomData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RoomData,
                newItem: RoomData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface ItemClickListener {

        fun roomClicked(room: RoomData)
    }
}