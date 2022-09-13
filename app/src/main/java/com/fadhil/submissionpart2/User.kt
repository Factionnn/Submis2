package com.fadhil.submissionpart2

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(

    val login: String,


    val url: Int,


    val avatar_url: String
): Parcelable
