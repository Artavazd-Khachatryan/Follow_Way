package com.useruser.followay.ui.fragment.locationMapFragment

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.useruser.followay.R

import com.useruser.followay.databinding.LocationMapFragmentBinding
import com.useruser.followay.domain.utils.createBitmap
import com.useruser.followay.domain.utils.createViewFromLayout
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LocationMapFragment : DaggerFragment(), OnMapReadyCallback {


    companion object {

        private val TAG = "LocationMapFragment"
        fun newInstance() =
            LocationMapFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LocationMapViewModel
    private lateinit var binding: LocationMapFragmentBinding
    private lateinit var map: GoogleMap

    lateinit var markerView: View

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModelAndBinding(inflater, container)

        initMap()



        viewModel.getLocationForUpdates().subscribe {
            Log.d(TAG, "----------------------------------------------------${it}")
        }

        return binding.root
    }

    private fun initViewModelAndBinding(inflater: LayoutInflater, container: ViewGroup?) {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LocationMapViewModel::class.java)
        binding = LocationMapFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(map: GoogleMap?) {
        this.map = map!!
        map.run {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            isMyLocationEnabled = true

            clear()

            val cameraPosition = CameraPosition.builder()
                .target(LatLng(37.4219999, -122.0862462))
                .zoom(10f)
                .bearing(0f)
                .tilt(45f)
                .build()

            animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1000, null)


            markerView = createViewFromLayout(context!!, R.layout.map_info_marker)

            addMarker(
                MarkerOptions()
                    .snippet("Snippet")
                    .position(LatLng(37.4219999, -122.0862462))
                    .title("Spider Man")
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(markerView.createBitmap())
                    )
            )


            val polilineoptions = PolylineOptions()
            polilineoptions.addAll(
                listOf(
                    LatLng(37.4219999, -122.0862462),
                    LatLng(15.4219999, -94.0862462)
                )
            )
            polilineoptions.width(10f)
            polilineoptions.color(Color.MAGENTA)

            addPolyline(polilineoptions)



            setOnMapClickListener {

                Log.d(
                    TAG,
                    "-----------------------------setOnMapClickListener-------------------------------"
                )
            }

        }

    }


}
