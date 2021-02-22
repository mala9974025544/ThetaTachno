package com.thetatechnolabs.test.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thetatechnolabs.test.R


class MapFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_map, container, false)

       // val mapFragment = requireActivity()!!.supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
       // mapFragment!!.getMapAsync(this)
        return v
    }




}