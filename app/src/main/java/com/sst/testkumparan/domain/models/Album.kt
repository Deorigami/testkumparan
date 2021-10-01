package com.sst.testkumparan.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val id: Int,
    val title: String,
    val userId: Int,
    var photos: List<Photo>? = null
) : Parcelable

