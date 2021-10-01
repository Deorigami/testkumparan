package com.sst.testkumparan.persentation.state

import com.sst.testkumparan.domain.models.Album
import com.sst.testkumparan.domain.models.User

data class AlbumsState(
    val isLoading :Boolean = false,
    val albums : List<Album> = emptyList(),
    val error :String = ""
)