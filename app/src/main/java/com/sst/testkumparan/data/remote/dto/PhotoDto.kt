package com.sst.testkumparan.data.remote.dto

import com.sst.testkumparan.domain.models.Photo

data class PhotoDto(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)

fun PhotoDto.toPhoto():Photo{
    return Photo(
        albumId = albumId,
        id = id,
        thumbnailUrl = thumbnailUrl,
        title = title,
        url = url,
    )
}