package com.example.crypto.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.crypto.adapters.viewpager.CoinPagerAdapter
import com.example.crypto.adapters.viewpager.ExchangePagerAdapter
import com.example.crypto.databinding.FragmentCoinBinding
import com.example.crypto.databinding.FragmentExchangeBinding
import com.google.android.material.tabs.TabLayoutMediator

class CoinFragment: Fragment() {
    private lateinit var binding: FragmentCoinBinding
    private val tabTitles = arrayListOf("Price Chart", "Exchanges", "Portfolio", "Info")
    lateinit var coinId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinBinding.inflate(inflater)
        setupTabLayoutWithViewPager()
        setClickListeners()

        val args: CoinFragmentArgs by navArgs()
        coinId = args.coinId

        return binding.root
    }

    private fun setClickListeners() {
        binding.apply {
            upButtonImage.setOnClickListener{ findNavController().navigateUp() }
            searchImage.setOnClickListener { findNavController().navigate(CoinFragmentDirections.actionCoinFragmentToSearchFragment()) }
        }
    }

    private fun setupTabLayoutWithViewPager() {
        binding.viewPager.adapter = CoinPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}