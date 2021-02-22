package com.thetatechnolabs.test.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.thetatechnolabs.test.R
import com.thetatechnolabs.test.TestApplication
import com.thetatechnolabs.test.utils.SharedPrefrence
import com.thetatechnolabs.test.utils.Utils
import kotlinx.android.synthetic.main.activity_emailpassword.*
import spencerstudios.com.bungeelib.Bungee

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

                if(!user.email.isNullOrEmpty())
                SharedPrefrence.setUserEmail(this,user.email.toString())

                redirecToMainActivity()
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

