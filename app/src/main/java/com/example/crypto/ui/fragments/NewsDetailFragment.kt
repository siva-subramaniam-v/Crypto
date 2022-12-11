package com.example.crypto.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.crypto.databinding.FragmentNewsDetailBinding

class NewsDetailFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewsDetailBinding.inflate(inflater)
        val args: NewsDetailFragmentArgs by navArgs()

        binding.newsDetailWebView.apply {
            webViewClient = WebViewClient()
            loadUrl(args.newsUrl)
            settings.javaScriptEnabled = true
        }

        return binding.root
    }
}