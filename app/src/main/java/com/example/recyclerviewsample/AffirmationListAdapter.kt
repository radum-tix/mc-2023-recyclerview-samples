package com.example.recyclerviewsample

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsample.databinding.ViewComplexItemBinding
import com.example.recyclerviewsample.databinding.ViewHeaderBinding
import com.example.recyclerviewsample.databinding.ViewLoadingBinding
import java.lang.IllegalArgumentException

class AffirmationListAdapter(
    private val context: Context
) : ListAdapter<Item, RecyclerView.ViewHolder>(DiffItemCallback()) {
    inner class AffirmationViewHolder(val binding: ViewComplexItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(affirmation: Item.Affirmation) {
            binding.itemImage.setImageResource(affirmation.drawableResId)
            binding.itemTitle.setText(affirmation.stringResourceId)
        }
    }

    inner class HeaderViewHolder(val binding: ViewHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(header: Item.Header) {
            binding.root.setText(header.stringResourceId)
        }
    }

    inner class LoadingViewHolder(val binding: ViewLoadingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Item.Affirmation -> TYPE_AFFIRMATION
            is Item.Header -> TYPE_HEADER
            is Item.Loading -> TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)

        when (viewType) {
            TYPE_AFFIRMATION -> {
                val binding = ViewComplexItemBinding.inflate(inflater)
                return AffirmationViewHolder(binding)
            }
            TYPE_HEADER -> {
                val binding = ViewHeaderBinding.inflate(inflater)
                return HeaderViewHolder(binding)
            }
            TYPE_LOADING -> {
                val binding = ViewLoadingBinding.inflate(inflater)
                return LoadingViewHolder(binding)
            }
        }

        throw IllegalArgumentException("Unknown type: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AffirmationViewHolder -> {
                holder.onBind(getItem(position) as Item.Affirmation)
            }
            is HeaderViewHolder -> {
                holder.onBind(getItem(position) as Item.Header)
            }
        }
    }

    companion object {
        const val TYPE_AFFIRMATION = 1
        const val TYPE_HEADER = 2
        const val TYPE_LOADING = 3
    }
}

class DiffItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return when {
            oldItem is Item.Affirmation && newItem is Item.Affirmation -> {
                oldItem === newItem
            }
            oldItem is Item.Header && newItem is Item.Header -> {
                oldItem === newItem
            }
            oldItem is Item.Loading && newItem is Item.Loading -> {
                true
            }
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return when {
            oldItem is Item.Affirmation && newItem is Item.Affirmation -> {
                oldItem == newItem
            }
            oldItem is Item.Header && newItem is Item.Header -> {
                oldItem == newItem
            }
            oldItem is Item.Loading && newItem is Item.Loading -> {
                true
            }
            else -> false
        }
    }
}

sealed interface Item {
    data class Affirmation(val stringResourceId: Int, val drawableResId: Int) : Item
    data class Header(val stringResourceId: Int) : Item
    object Loading : Item
}