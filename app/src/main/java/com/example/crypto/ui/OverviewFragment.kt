package com.example.crypto.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.crypto.R
import com.example.crypto.adapter.OverviewAdapter
import com.example.crypto.databinding.FragmentOverviewBinding
import com.example.crypto.viewmodels.OverviewViewModel

class OverviewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentOverviewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)
        binding.lifecycleOwner = this

        val overviewViewModel = ViewModelProvider(this)[OverviewViewModel::class.java]
        binding.overviewViewModel = overviewViewModel

        val adapter = OverviewAdapter().also {
            it.setOnclickListener { coinId ->
                findNavController().navigate(
                    OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(coinId)
                )
            }
        }

        binding.cryptoList.adapter = adapter

        overviewViewModel.overview.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
        return binding.root
    }
}