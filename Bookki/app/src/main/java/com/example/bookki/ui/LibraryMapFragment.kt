package com.example.bookki.ui

import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookki.R
import com.example.bookki.databinding.FragmentLibraryMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LibraryMapFragment : Fragment(), OnMapReadyCallback {
    private var _binding: FragmentLibraryMapBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LibraryListViewModel by viewModels()
    private lateinit var map: GoogleMap

    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Log.d("MapFragment", "Permission granted")
            getLocation()
        } else {
            Log.d("MapFragment", "Location permission denied")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.libraries.observe(viewLifecycleOwner) {
            it.forEach {
                try {
                    map.addMarker(
                        MarkerOptions()
                            .position(LatLng(it.LATITUDE.toDouble(), it.LONGITUDE.toDouble()))
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            .title(it.LIBRARY)
                    )
                } catch (e: NumberFormatException) {
                    Log.e("LibraryInfoFragment", "Invalid latitude or longitude: ${e.message}")
                }
            }
        }

        viewModel.getLibraries()

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.imageViewBack.setOnClickListener {
            findNavController().navigate(R.id.action_libraryMapFragment_to_libraryListFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        var marker = LatLng(35.8714, 128.6014)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 12f))

        Log.d("map", "데")

        getLocation()
    }

    private fun getLocation() {
        Log.d("map", "헷")

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            val locationManager = requireContext().getSystemService(LocationManager::class.java)
            val provider = LocationManager.GPS_PROVIDER
            val location = locationManager.getLastKnownLocation(provider)
            if (location != null) {
                val userLocation = LatLng(location.latitude, location.longitude)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 14f))
                map.addMarker(
                    MarkerOptions()
                        .position(userLocation)
                )
            }
        }
    }

}