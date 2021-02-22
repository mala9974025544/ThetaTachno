package com.thetatechnolabs.test

import android.app.Application
import android.util.Log
import com.google.firebase.auth.FirebaseAuth



class TestApplication : Application() {


     var mAuth: FirebaseAuth? = null
    override fun onCreate() {
        Log.d("Tag","Application")
        mAuth = FirebaseAuth.getInstance()
        super.onCreate()

    }
    fun getFirebaseAuth(): FirebaseAuth? {
        return mAuth
    }

}
