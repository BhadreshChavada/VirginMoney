package com.virginmoney.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.virginmoney.R
import com.virginmoney.databinding.DialogRoomDetailsBinding
import com.virginmoney.model.RoomData

class RoomDetailDialog(
    private val mContext: Context,
    private val roomData: RoomData
) : DialogFragment() {

    var binding: DialogRoomDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.dialog_room_details,
            null,
            false
        )
        binding?.roomData = roomData
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }


    private fun setListener() {
        binding?.btnClose?.setOnClickListener {
            this.dismiss()

        }
    }
}