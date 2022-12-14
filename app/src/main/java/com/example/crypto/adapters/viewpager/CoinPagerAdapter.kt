package com.example.crypto.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.crypto.ui.fragments.InfoFragment

class CoinPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InfoFragment()
            1 -> InfoFragment()
            2 -> InfoFragment()
            else -> InfoFragment()
        }
    }
}