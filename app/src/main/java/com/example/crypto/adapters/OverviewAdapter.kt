package com.example.crypto.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto.databinding.ListItemOverviewNewBinding
import com.example.crypto.domain.CryptoOverview

class OverviewAdapter :
    ListAdapter<CryptoOverview, OverviewAdapter.ViewHolder>(OverviewDiffCallback()) {

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

    class ViewHolder private constructor(private val binding: ListItemOverviewNewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: CryptoOverview,
            clickListener: ((coinId: String) -> Unit)?
        ) {
            binding.cryptoOverview = item
            clickListener?.let {
                binding.root.setOnClickListener {
                    clickListener.invoke(item.id)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemOverviewNewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class OverviewDiffCallback : DiffUtil.ItemCallback<CryptoOverview>() {
    override fun areItemsTheSame(oldItem: CryptoOverview, newItem: CryptoOverview): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CryptoOverview, newItem: CryptoOverview): Boolean {
        return oldItem == newItem
    }
}