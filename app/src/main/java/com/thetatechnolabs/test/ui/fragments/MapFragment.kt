package com.thetatechnolabs.test.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.thetatechnolabs.test.R
import kotlinx.android.synthetic.main.fragment_dashboard.*


class MapFragment : Fragment(), OnMapReadyCallback {
    var mGoogleMap: GoogleMap? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_dashboard, container, false)

       // val mapFragment = requireActivity()!!.supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
       // mapFragment!!.getMapAsync(this)
        return v
    }



    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap;

        // Enable Zoom
        mGoogleMap?.uiSettings?.isZoomGesturesEnabled = true;
        //set Map TYPE
        mGoogleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL;
        //enable Current location Button

    }
}