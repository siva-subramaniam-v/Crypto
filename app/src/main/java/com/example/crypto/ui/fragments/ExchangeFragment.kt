package com.example.crypto.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.crypto.adapters.ExchangePagerAdapter
import com.example.crypto.databinding.FragmentExchangeBinding
import com.example.crypto.ui.CryptoActivity
import com.example.crypto.ui.viewmodels.ExchangeViewModel
import com.google.android.material.tabs.TabLayoutMediator

class ExchangeFragment: Fragment() {
    private lateinit var binding: FragmentExchangeBinding
    private val tabTitles = arrayListOf("Volume", "Market")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.let {
            val activity = it as CryptoActivity
        }

        binding = FragmentExchangeBinding.inflate(inflater)
        setupTabLayoutWithViewPager()

        val args: ExchangeFragmentArgs by navArgs()
        Log.i("ExchangeFragment", args.exchangeId)

        val exchangeViewModel : ExchangeViewModel by viewModels()
        exchangeViewModel.getExchangeDetail(args.exchangeId)

        // TODO: add loading animation to exchange fragment
//        exchangeViewModel.exchangeDetail.observe(viewLifecycleOwner) {
//            it?.let {
//                Toast.makeText(requireContext(), "${it.trustScore}", Toast.LENGTH_SHORT).show()
//                binding.apply {
//                    rankText.text = "Rank #${it.rank}"
//                    categoryText.text = if (it.centralized) "Centralized" else "Decentralized"
//                    trustText.text = when(it.trustScore) {
//                        in 9..10 -> "Trust \uD83D\uDFE2 ${it.trustScore}/10"
//                        in 5..8 -> "Trust \uD83D\uDFE0 ${it.trustScore}/10"
//                        else -> "Trust \uD83D\uDD34 ${it.trustScore}/10"
//                    }
//                }
//            }
//        }

        return binding.root
    }

    private fun setupTabLayoutWithViewPager() {
        binding.viewPager.adapter = ExchangePagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}