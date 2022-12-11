package com.example.crypto.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.crypto.adapters.NewsAdapter
import com.example.crypto.databinding.FragmentNewsBinding
import com.example.crypto.ui.viewmodels.NewsViewModel
import com.example.crypto.ui.viewmodels.factories.NewsViewModelFactory


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val factory = NewsViewModelFactory(requireActivity().application)
        val newsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        val adapter = NewsAdapter().also {
            it.setOnClickListener { newsUrl ->
                findNavController().navigate(
                    NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(newsUrl)
                )
            }
        }

        binding.newsList.adapter = adapter

        newsViewModel.news.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        return binding.root
    }
}