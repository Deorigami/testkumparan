package com.sst.testkumparan.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int,
    var author : User? = null,
) : Parcelable