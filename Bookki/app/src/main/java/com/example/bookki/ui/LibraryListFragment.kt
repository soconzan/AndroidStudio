package com.example.bookki.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookki.R
import com.example.bookki.databinding.FragmentLibraryListBinding
import com.example.bookki.model.LibItem
import com.example.bookki.widget.LibraryAdapter

class LibraryListFragment : Fragment() {
    private var _binding: FragmentLibraryListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LibraryListViewModel by viewModels()
    private lateinit var adapter: LibraryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = LibraryAdapter { library -> onLibraryItemClicked(library) }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.libraries.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        viewModel.getLibraries()

        binding.buttonMap.setOnClickListener {
            Log.d("list", "안 돼 왜??????")
            findNavController().navigate(R.id.action_libraryListFragment_to_libraryMapFragment)
        }
    }

    private fun onLibraryItemClicked(library: LibItem) {
        val bundle = Bundle().apply {
            putString("code", library.CODE)
        }
        findNavController().navigate(R.id.action_libraryListFragment_to_libraryInfoFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}