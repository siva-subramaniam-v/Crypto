package com.example.crypto.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto.databinding.ListItemSearchCoinBinding
import com.example.crypto.network.SearchResult.Coin

class SearchCoinAdapter : ListAdapter<Coin, SearchCoinAdapter.ViewHolder>(CoinDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchCoinAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    private var clickListener: ((id: String) -> Unit)? = null

    fun setOnclickListener(clickListener: ((id: String) -> Unit)) {
        this.clickListener = clickListener
    }

    class ViewHolder private constructor(private val binding: ListItemSearchCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Coin,
            clickListener: ((coinId: String) -> Unit)?
        ) {
            binding.coin = item
            clickListener?.let {
                binding.root.setOnClickListener {
                    clickListener.invoke(item.id)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSearchCoinBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CoinDiffCallback : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }
}