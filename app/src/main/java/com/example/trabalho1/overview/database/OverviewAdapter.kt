package com.example.trabalho1.overview.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho1.databinding.ViewBookBinding

class OverviewAdapter(private val onClickListener: (BookEntity) -> Unit) :
    ListAdapter<BookEntity, OverviewAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        private val binding: ViewBookBinding,
        private val onClickListener: (BookEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(info: BookEntity) {
            with(binding) {
                autor.text = info.authorName
                book.text = info.bookName
                root.setOnClickListener {
                    onClickListener(info)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ViewBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickListener
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object DiffCallback : DiffUtil.ItemCallback<BookEntity>() {
        override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity) =
            oldItem.uid == newItem.uid

        override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity) =
            oldItem == newItem
    }
}