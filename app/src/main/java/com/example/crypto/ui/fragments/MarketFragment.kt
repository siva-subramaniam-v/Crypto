package com.example.crypto.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.crypto.adapters.recyclerview.MarketAdapter
import com.example.crypto.databinding.FragmentMarketBinding
import com.example.crypto.ui.viewmodels.ExchangeViewModel

class MarketFragment : Fragment() {
    private lateinit var binding: FragmentMarketBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarketBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val exchangeViewModel: ExchangeViewModel by viewModels(
            ownerProducer = { requireParentFragment() }
        )

        val adapter = MarketAdapter()
        binding.marketsList.adapter = adapter

        exchangeViewModel.exchangeDetail.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it.tickers)
                Toast.makeText(requireContext(), it.name, Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }
}