package com.example.bookki.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookki.R
import com.example.bookki.databinding.FragmentSearchBooksBinding
import com.example.bookki.widget.SearchBookAdapter

class SearchBooksFragment : Fragment() {
    private var _binding: FragmentSearchBooksBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchBooksViewModel by viewModels()
    private lateinit var adapter: SearchBookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SearchBookAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.searchBooks.observe(viewLifecycleOwner) {
            adapter.updateData(it)
            binding.textViewEmpty.isVisible = it.isEmpty()
        }

        val code = arguments?.getString("code")
        val searchText = arguments?.getString("searchText")
        if (code != null && searchText != null) {
            Log.d("search", "${code} : ${searchText}")
            viewModel.getSearchBooks(code, searchText)
            binding.textViewBook.text = "'${searchText}'의 검색 결과"
        }

        binding.imageViewBack.setOnClickListener {
            if (code != null) {
                val bundle = Bundle().apply {
                    putString("code", code)
                }
                findNavController().navigate(
                    R.id.action_SearchBooksFragment_to_libraryInfoFragment, bundle
                )
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}