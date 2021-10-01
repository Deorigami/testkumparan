package com.sst.testkumparan.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    var user : User? = null,
    var comments : List<Comment>? = null
) : Parcelable