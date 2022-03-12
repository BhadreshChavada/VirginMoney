package com.virginmoney.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.virginmoney.R
import com.virginmoney.connectivity.ResultWrapper
import com.virginmoney.databinding.FragmentUserBinding
import com.virginmoney.ui.adapter.UserAdapter
import com.virginmoney.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }


    private fun setObserver() {

        viewModel.userData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    if (result.value != null) {
                        val userAdapter = UserAdapter(requireActivity())
                        val dividerItemDecoration = DividerItemDecoration(
                            requireActivity(),
                            LinearLayout.VERTICAL
                        )
                        binding.rvUser.addItemDecoration(dividerItemDecoration)
                        binding.rvUser.adapter = userAdapter
                        userAdapter.submitList(result.value)
                    }
                }
                is ResultWrapper.Error -> {
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.something_went_wrong),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }
}