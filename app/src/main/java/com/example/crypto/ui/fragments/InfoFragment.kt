package com.example.crypto.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.crypto.R
import com.example.crypto.databinding.FragmentInfoBinding
import com.example.crypto.ui.viewmodels.DetailViewModel

class InfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentInfoBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        binding.lifecycleOwner = this

        //val args: InfoFragmentArgs by navArgs()

        val detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        binding.detailViewModel = detailViewModel

        // TODO: (Fixed) java.lang.ClassCastException: androidx.navigation.fragment.NavHostFragment cannot be cast to com.example.crypto.ui.fragments.CoinFragment
        val coinFragment = parentFragment as CoinFragment
        val coinId = coinFragment.coinId

        detailViewModel.getDetail(coinId)
        Log.i("DetailFragment", coinId)

        detailViewModel.detail.observe(viewLifecycleOwner) {
            if (it == null) {
                //binding.mainScroll.visibility = View.INVISIBLE
                binding.detailCardView.visibility = View.INVISIBLE
                binding.descriptionText.visibility = View.INVISIBLE
            } else {
                //binding.mainScroll.visibility = View.VISIBLE
                binding.detailCardView.visibility = View.VISIBLE
                binding.descriptionText.visibility = View.VISIBLE
                //binding.loadingAnimation.visibility = View.INVISIBLE
            }
        }

        binding.apply {
            homepageUrl.setOnClickListener { onUrlClicked(it) }
            twitterUrl.setOnClickListener { onUrlClicked(it) }
            facebookUrl.setOnClickListener { onUrlClicked(it) }
        }

        return binding.root
    }

    private fun onUrlClicked(view: View) {
        val textView = view as TextView

        textView.text?.let {
            val uri = Uri.parse("https://$it")

            uri?.let {
                val customTabsBuilder = CustomTabsIntent.Builder()
                val colorSchemeParams = CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(ContextCompat.getColor(requireContext(), R.color.blue_grey_dark))
                    .build()

                //TODO: set navbar color for custom chrome tab
                customTabsBuilder.apply {
                    setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_DARK, colorSchemeParams)
                    setStartAnimations(requireContext(), R.anim.slide_in_right, R.anim.slide_out_left)
                    setExitAnimations(requireContext(), R.anim.slide_in_left, R.anim.slide_out_right)
                }

                val customTabsIntent = customTabsBuilder.build()
                customTabsIntent.launchUrl(requireContext(), uri)
            }
        }
    }
}