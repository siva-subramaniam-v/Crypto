package com.example.crypto.adapters.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto.R
import com.example.crypto.databinding.ListItemMarketBinding
import com.example.crypto.network.ExchangeDetail.Ticker

class MarketAdapter :
    ListAdapter<Ticker, MarketAdapter.ViewHolder>(MarketDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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

    class ViewHolder private constructor(private val binding: ListItemMarketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Ticker,
            clickListener: ((coinId: String) -> Unit)?
        ) {
            binding.market = item

            binding.trustScoreImage.setImageResource(
                when (item.trustScoreColor) {
                    "green" -> R.drawable.trust_score_green
                    "yellow" -> R.drawable.trust_score_yellow
                    "red" -> R.drawable.trust_score_red
                    else -> R.drawable.trust_score_null
                }
            )

//            clickListener?.let {
//                binding.root.setOnClickListener {
//                    clickListener.invoke(item.id)
//                }
//            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemMarketBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class MarketDiffCallback : DiffUtil.ItemCallback<Ticker>() {
    override fun areItemsTheSame(oldItem: Ticker, newItem: Ticker): Boolean {
        return (oldItem.base == newItem.base && oldItem.target == newItem.target)
    }

    override fun areContentsTheSame(oldItem: Ticker, newItem: Ticker): Boolean {
        return oldItem == newItem
    }
}