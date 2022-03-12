package com.virginmoney.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.virginmoney.R
import com.virginmoney.connectivity.ResultWrapper
import com.virginmoney.databinding.FragmentRoomBinding
import com.virginmoney.model.RoomData
import com.virginmoney.ui.adapter.RoomsAdapter
import com.virginmoney.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RoomFragment : Fragment() {

    private lateinit var binding: FragmentRoomBinding

    private val viewModel: RoomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
    }

    private fun setObserver() {
        viewModel.roomsData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    if (result.value != null) {
                        val roomsAdapter = RoomsAdapter(
                            requireActivity(),
                            object : RoomsAdapter.ItemClickListener {
                                override fun roomClicked(room: RoomData) {
                                    RoomDetailDialog(
                                        requireActivity(),
                                        room
                                    ).show(childFragmentManager, "")
                                }
                            })
                        val numberOfColumns = 6
                        binding.rvRoom.setLayoutManager(
                            GridLayoutManager(
                                requireActivity(),
                                numberOfColumns
                            )
                        )
                        binding.rvRoom.adapter = roomsAdapter
                        roomsAdapter.submitList(result.value)
                    }
                }
                is ResultWrapper.Error -> {
                    Toast.makeText(requireActivity(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }


    }

}