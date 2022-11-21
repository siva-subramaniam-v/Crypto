package com.example.crypto.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.crypto.R
import com.example.crypto.databinding.FragmentDetailBinding
import com.example.crypto.viewmodels.DetailViewModel

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.lifecycleOwner = this

        val args: DetailFragmentArgs by navArgs()

        val detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        binding.detailViewModel = detailViewModel
        detailViewModel.getDetail(args.coinId)
        Log.i("DetailFragment", args.coinId)

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
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToWebViewFragment("https://$it")
            )
        }
    }
}