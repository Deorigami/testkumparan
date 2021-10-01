package com.sst.testkumparan.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val address: String,
    val company: String,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    var albums : List<Album>? = null

) : Parcelable