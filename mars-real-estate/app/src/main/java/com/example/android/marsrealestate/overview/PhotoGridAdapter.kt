package com.example.android.marsrealestate.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.databinding.GridViewItemBinding
import com.example.android.marsrealestate.network.MarsProperty

class PhotoGridAdapter(private val onClickListener: OnItemClickListener) : ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {
    companion object DiffCallback: DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return  oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class MarsPropertyViewHolder(private var binding: GridViewItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(property: MarsProperty) {
            binding.property = property
            binding.executePendingBindings()
        }
    }

    class OnItemClickListener(val clickListener: (marsProperty: MarsProperty) -> Unit) {
        fun onClick(marsProperty:MarsProperty) = clickListener(marsProperty)
    }
}