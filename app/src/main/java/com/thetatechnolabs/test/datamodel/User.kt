package com.thetatechnolabs.test.datamodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    @SerializedName("avatar")
    val image: String,
    @SerializedName("email")
    val userEmail: String,

    @SerializedName("id")
    val userId: String,
    @SerializedName("first_name")
    val userName: String
):Parcelable