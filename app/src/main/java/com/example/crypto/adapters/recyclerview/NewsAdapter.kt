package com.example.crypto.adapters.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto.database.DbNews
import com.example.crypto.databinding.ListItemNewsBinding

class NewsAdapter :
    ListAdapter<DbNews, NewsAdapter.ViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    private var clickListener: ((newsUrl: String) -> Unit)? = null

    fun setOnClickListener(clickListener: ((newsUrl: String) -> Unit)) {
        this.clickListener = clickListener
    }

    class ViewHolder private constructor(private val binding: ListItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: DbNews,
            clickListener: ((newsUrl: String) -> Unit)?
        ) {
            binding.dbNews = item
            clickListener?.let {
                binding.root.setOnClickListener {
                    clickListener.invoke(item.url)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemNewsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class NewsDiffCallback : DiffUtil.ItemCallback<DbNews>() {
    override fun areItemsTheSame(oldItem: DbNews, newItem: DbNews): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: DbNews, newItem: DbNews): Boolean {
        return oldItem == newItem
    }
}