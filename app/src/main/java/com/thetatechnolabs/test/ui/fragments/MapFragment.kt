package com.thetatechnolabs.test.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.thetatechnolabs.test.R


class MapFragment : Fragment(), OnMapReadyCallback {
    var currentLocation: Location?=null
    private var mapFragment: SupportMapFragment? = null
    var fusedLocationProviderClient: FusedLocationProviderClient?=null
    private var   REQUEST_CODE = 101
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_map, container, false)
        mapFragment = childFragmentManager
                .findFragmentById(R.id.myMap) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        return v
    }


    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(23.0225, 72.5714)
        val markerOptions = MarkerOptions().position(latLng).title("I am here!")
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))
        googleMap.addMarker(markerOptions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mapFragment != null)
            childFragmentManager.beginTransaction().remove(mapFragment!!).commitAllowingStateLoss()
    }
}