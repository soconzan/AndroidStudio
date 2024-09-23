package com.example.bookki.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookki.Library
import com.example.bookki.databinding.ItemLibraryBinding
import com.example.bookki.model.LibItem

class LibraryAdapter(private val clickListener: (LibItem) -> Unit) :
    RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder>() {

    private var data: List<LibItem> = listOf()
    private var listener: OnItemSelected? = null

    fun interface OnItemSelected {
        fun onItemSelected(library: Library)
    }

    fun updateData(data: List<LibItem>) {
        this.data = data
        notifyItemRangeChanged(0, data.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LibraryViewHolder {
        val binding = ItemLibraryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibraryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }

    override fun getItemCount(): Int = data.size

    inner class LibraryViewHolder(private val binding: ItemLibraryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var library: LibItem

        fun bind(library: LibItem, clickListener: (LibItem) -> Unit) {
            this.library = library
            binding.textViewAddr.text = library.LOCATION
            binding.textViewName.text = library.LIBRARY
            binding.textViewCode.text = library.CODE
            binding.root.setOnClickListener { clickListener(library) }
        }
    }
}