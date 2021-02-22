package com.thetatechnolabs.test.utils

import android.content.Context
import android.content.SharedPreferences
import com.thetatechnolabs.test.R


object SharedPrefrence {

    //VARIABLES
    const val IS_LOGED_IN = "is_logged_in"

    private fun getSharedPref(context: Context?): SharedPreferences? {
        return context?.getSharedPreferences(
            context?.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }



    fun setLogin(context : Context,flag : Boolean){
        val sharedPref = getSharedPref(context)
        val editor = sharedPref!!.edit()
        editor.putBoolean(IS_LOGED_IN, flag)
        editor.apply()
    }

    fun getLogin(context :Context?): Boolean {
        val sharedPref = getSharedPref(context)
        return sharedPref!!.getBoolean(IS_LOGED_IN, false)
    }


}
