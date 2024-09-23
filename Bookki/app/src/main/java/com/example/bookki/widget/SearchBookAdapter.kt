package com.example.bookki.widget

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.bookki.R
import com.example.bookki.api.SrchItem
import com.example.bookki.databinding.ItemSearchbookBinding

class SearchBookAdapter : RecyclerView.Adapter<SearchBookAdapter.SearchBookViewHolder>() {

    private var data: List<SrchItem> = listOf()

    fun updateData(data: List<SrchItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchBookAdapter.SearchBookViewHolder {
        val binding =
            ItemSearchbookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchBookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchBookAdapter.SearchBookViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class SearchBookViewHolder(private val binding: ItemSearchbookBinding) :
        ViewHolder(binding.root) {

        private lateinit var searchBook: SrchItem

        fun bind(searchBook: SrchItem) {
            this.searchBook = searchBook
            binding.textViewTitle.text = searchBook.TITLE_INFO
            binding.textViewPublisher.text = searchBook.PUBLISHER
            if (searchBook.BOOK_STATUS == "0") {
                binding.textViewStatus.text = "대출중"
                binding.textViewStatus.setTextColor(Color.GRAY)
            }
            searchBook.IMAGE?.let {
                Glide.with(binding.root.context)
                    .load(it)
                    .into(binding.imageViewThumb)
            }
        }
    }
}