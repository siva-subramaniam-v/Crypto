package com.example.crypto.adapters.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto.databinding.ListItemSearchExchangeBinding
import com.example.crypto.network.SearchResult.Exchange

class SearchExchangeAdapter : ListAdapter<Exchange, SearchExchangeAdapter.ViewHolder>(
    ExchangeDiffCallback()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    private var clickListener: ((id: String) -> Unit)? = null

    fun setOnclickListener(clickListener: ((id: String) -> Unit)) {
        this.clickListener = clickListener
    }

    class ViewHolder private constructor(private val binding: ListItemSearchExchangeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Exchange,
            clickListener: ((coinId: String) -> Unit)?
        ) {
            binding.exchange = item
            clickListener?.let {
                binding.root.setOnClickListener {
                    clickListener.invoke(item.id)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSearchExchangeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ExchangeDiffCallback : DiffUtil.ItemCallback<Exchange>() {
    override fun areItemsTheSame(oldItem: Exchange, newItem: Exchange): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Exchange, newItem: Exchange): Boolean {
        return oldItem == newItem
    }
}