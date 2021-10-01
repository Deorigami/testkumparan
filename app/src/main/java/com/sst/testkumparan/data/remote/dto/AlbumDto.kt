package com.sst.testkumparan.data.remote.dto

import com.sst.testkumparan.domain.models.Album

data class AlbumDto(
    val id: Int,
    val title: String,
    val userId: Int
)

fun AlbumDto.toAlbum(): Album {
    return Album(
        id = id,
        title = title,
        userId = userId,
    )
}