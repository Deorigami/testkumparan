package com.sst.testkumparan.persentation.state

import com.sst.testkumparan.domain.models.Photo
import com.sst.testkumparan.domain.models.User

data class PhotosState(
    val isLoading :Boolean = false,
    val photos : List<Photo> = emptyList(),
    val error :String = ""
)