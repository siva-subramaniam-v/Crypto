package com.example.crypto.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.crypto.ui.fragments.MarketFragment
import com.example.crypto.ui.fragments.VolumeFragment

class ExchangePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VolumeFragment()
            else -> MarketFragment()
        }
    }
}