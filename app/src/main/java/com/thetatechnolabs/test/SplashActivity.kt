package com.thetatechnolabs.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thetatechnolabs.test.utils.Utils
import kotlinx.coroutines.*
import spencerstudios.com.bungeelib.Bungee
import kotlin.coroutines.CoroutineContext

class SplashActivity : BaseActivity(), CoroutineScope {

    private var user: FirebaseUser?=null
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private var mAuth: FirebaseAuth? = null
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mAuth=(application as TestApplication).getFirebaseAuth()

        fullScreen()
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            user = firebaseAuth.currentUser


        }
        launch {
            delay(2000)
            withContext(Dispatchers.Main) {
               updateUI()
            }
        }

    }




    private fun updateUI() {

        if(user!=null && !user!!.uid.isNullOrEmpty()){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            Bungee.slideLeft(this)
        }else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            Bungee.slideLeft(this)
        }
    }

    override fun onBackPressed() {
        finish()
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
}
