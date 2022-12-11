package com.example.crypto.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.crypto.adapters.SearchCoinAdapter
import com.example.crypto.adapters.SearchExchangeAdapter
import com.example.crypto.databinding.FragmentSearchBinding
import com.example.crypto.ui.viewmodels.SearchViewModel

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchBinding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val searchViewModel: SearchViewModel by viewModels()

        binding.search.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        if (query.isNotBlank()) searchViewModel.search(query)
                        Log.i("SearchFragment", "$query")
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { if (newText.isNotBlank()) searchViewModel.search(newText) }
                    return true
                }
            }
        )

        val coinAdapter = SearchCoinAdapter().also {
            it.setOnclickListener { coinId ->
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToDetailFragment(coinId)
                )
            }
        }

        binding.coinResults.adapter = coinAdapter

        searchViewModel.coins.observe(viewLifecycleOwner) {
            it?.let {
                coinAdapter.submitList(it)
            }
        }

        val exchangeAdapter = SearchExchangeAdapter().also {
            it.setOnclickListener { exchangeId ->
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToExchangeFragment(exchangeId)
                )
            }
        }

        binding.exchangeResults.adapter = exchangeAdapter

        searchViewModel.exchanges.observe(viewLifecycleOwner) {
            it?.let {
                exchangeAdapter.submitList(it)
            }
        }

        return binding.root
    }
}