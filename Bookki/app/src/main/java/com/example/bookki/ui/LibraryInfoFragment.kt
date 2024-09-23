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
import com.example.bookki.databinding.FragmentLibraryInfoBinding
import com.example.bookki.widget.BestBookAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LibraryInfoFragment : Fragment(), OnMapReadyCallback {
    private var _binding: FragmentLibraryInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LibraryInfoViewModel by viewModels()
    private lateinit var adapter: BestBookAdapter
    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BestBookAdapter()

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter

        viewModel.bestBooks.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        viewModel.libraryPlace.observe(viewLifecycleOwner) { item ->
            item?.let {
                binding.textViewBook.text = item.LIBRARY
                try {
                    val lat = item.LATITUDE.toDouble()
                    val lng = item.LONGITUDE.toDouble()
                    val marker = LatLng(lat, lng)
                    map.addMarker(MarkerOptions().position(marker).title(item.LIBRARY))
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15f))
                } catch (e: NumberFormatException) {
                    Log.e("LibraryInfoFragment", "Invalid latitude or longitude: ${e.message}")
                }
            }
        }

        val code = arguments?.getString("code")
        Log.d("frag", "$code")
        if (code != null) {
            viewModel.getBestBooks(code)
            viewModel.getLibraryPlace(code)
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.imageViewBack.setOnClickListener {
            findNavController().navigate(R.id.action_libraryInfoFragment_to_libraryListFragment)
        }

        binding.buttonSearch.setOnClickListener {
            var searchText = binding.editTextSearch.text.toString()
            if (searchText != "") {
                val bundle = Bundle().apply {
                    putString("code", code)
                    putString("searchText", binding.editTextSearch.text.toString())
                }
                findNavController().navigate(R.id.action_libraryInfoFragment_to_SearchBooksFragment, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

}