package com.thetatechnolabs.test.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration

import com.thetatechnolabs.test.R
import com.thetatechnolabs.test.ui.adapters.MainAdapterWIthPageList
import com.thetatechnolabs.test.viewModels.MainViewModelWithPaging
import kotlinx.android.synthetic.main.fragment_home.*

class DashboardFragment : Fragment() {

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_home, container, false)

    }


    override fun onResume() {
        super.onResume()

        getUserData()
    }

    private fun getUserData() {

        val adapter = MainAdapterWIthPageList(this)
        val itemViewModel  = ViewModelProviders.of(this)
                .get(MainViewModelWithPaging::class.java)

        itemViewModel.userPagedList.observe(this, Observer {
            adapter.submitList(it)
        })
        rvList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        rvList.adapter = adapter

    }

}