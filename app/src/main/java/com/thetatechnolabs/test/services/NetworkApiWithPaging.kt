package com.thetatechnolabs.test.services

import com.thetatechnolabs.test.datamodel.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApiWithPaging{

    @GET("users")
     fun getUsers(@Query("page") page: Int): Call<UserResponse>

}