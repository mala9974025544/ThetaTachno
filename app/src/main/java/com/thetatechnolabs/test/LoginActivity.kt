package com.thetatechnolabs.test

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.thetatechnolabs.test.utils.Utils
import kotlinx.android.synthetic.main.activity_emailpassword.*
import spencerstudios.com.bungeelib.Bungee
import java.net.URL

class LoginActivity : BaseActivity(), View.OnClickListener {

    private var mAuthListener: AuthStateListener? = null
    private var mAuth: FirebaseAuth? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emailpassword)

        btnSignIn.setOnClickListener(this)
        btnEmailCreate.setOnClickListener(this)

        mAuth=(application as TestApplication).getFirebaseAuth()
        mAuthListener = AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            //SignUp
            if (user != null) {
                redirecToMainActivity()
            }else{

            }

        }
    }

    public override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart")
        mAuth!!.addAuthStateListener(mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop")
        if (mAuthListener != null) {
            mAuth!!.removeAuthStateListener(mAuthListener!!)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnEmailCreate -> createAccount(etEmail.text.toString(), etPassword!!.text.toString())
            R.id.btnSignIn -> signIn(etEmail!!.text.toString(), etPassword!!.text.toString())

        }
    }

    private fun createAccount(email: String, password: String) {
        Log.d("TAG", "createAccount")
        if (!validateForm()) {
            return
        }
        showProgressDialog()
        mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (!task.isSuccessful) {
                Utils.showToast(this,"User  exits!! Please Log in ",Toast.LENGTH_LONG)

            }
            hideProgressDialog()
        }
    }

    private fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        showProgressDialog()
        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful)
            if (!task.isSuccessful) {
                Utils.showToast(this,"User Does Not exits!! Please Sign in First",Toast.LENGTH_LONG)

            }
            hideProgressDialog()
        }
    }

    private fun signOut() {
        val alert = AlertDialog.Builder(this)
        alert.setMessage(R.string.logout)
        alert.setCancelable(false)
        alert.setPositiveButton(android.R.string.yes) { dialogInterface, i ->
            mAuth!!.signOut()

        }
        alert.setNegativeButton(android.R.string.no) { dialogInterface, i -> dialogInterface.dismiss() }
        alert.show()
    }
    private fun redirecToMainActivity() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        Bungee.slideLeft(this)
    }

    private fun validateForm(): Boolean {
        return if (TextUtils.isEmpty(etEmail!!.text.toString())) {
            ilEmail!!.error = "Required."
            false
        } else if (TextUtils.isEmpty(etPassword!!.text.toString())) {
            ilPassword!!.error = "Required."
            false
        } else {
            ilEmail!!.error = null
            ilPassword!!.error = null
            true
        }
    }


    }

