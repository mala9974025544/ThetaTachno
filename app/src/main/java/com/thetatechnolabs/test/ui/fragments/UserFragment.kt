package com.thetatechnolabs.test.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

import com.google.firebase.auth.FirebaseAuth

import com.thetatechnolabs.test.R
import com.thetatechnolabs.test.TestApplication
import com.thetatechnolabs.test.ui.activities.LoginActivity
import com.thetatechnolabs.test.utils.SharedPrefrence
import kotlinx.android.synthetic.main.fragment_user.*
import spencerstudios.com.bungeelib.Bungee

class UserFragment : Fragment(), View.OnClickListener {


    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignOut.setOnClickListener(this)
        mAuth=(requireActivity().application as TestApplication).getFirebaseAuth()

        val email = SharedPrefrence.getuserEmail(requireContext())
        if(!email.isNullOrEmpty())
            tvEmail.text =email



    }



    private fun signOut() {
        val alert = AlertDialog.Builder(requireActivity())
        alert.setMessage(R.string.logout)
        alert.setCancelable(false)
        alert.setPositiveButton(android.R.string.yes) { dialogInterface, i ->
            mAuth!!.signOut()
            SharedPrefrence.setUserEmail(requireContext(),"")
            redirecToMainActivity()
        }
        alert.setNegativeButton(android.R.string.no) { dialogInterface, i -> dialogInterface.dismiss() }
        alert.show()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnSignOut-> signOut()
        }
    }
    private fun redirecToMainActivity() {

        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
        Bungee.slideRight(requireActivity())
    }
}