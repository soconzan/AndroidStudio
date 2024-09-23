package com.example.bookki.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.bookki.databinding.ItemBestbookBinding
import com.example.bookki.model.Item

class BestBookAdapter : RecyclerView.Adapter<BestBookAdapter.BestBookViewHolder>() {

    private var data: List<Item> = listOf()

    fun updateData(data: List<Item>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestBookViewHolder {
        val binding =
            ItemBestbookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BestBookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BestBookViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class BestBookViewHolder(private val binding: ItemBestbookBinding) :
        ViewHolder(binding.root) {

        private lateinit var bestBook: Item

        fun bind(bestBook: Item) {
            this.bestBook = bestBook
            binding.textViewTitle.text = bestBook.TITLE
            binding.textViewAuthor.text = bestBook.AUTHOR
            bestBook.IMAGE?.let {
                Glide.with(binding.root.context)
                    .load(it)
                    .into(binding.imageViewThumb)
            }
        }
    }
}