package com.sst.testkumparan.domain.repositories

import com.sst.testkumparan.data.remote.dto.*

interface JsonPlaceholderRepo {
    suspend fun getAlbums(): List<AlbumDto>
    suspend fun getUsers(): MutableList<UserDto>
    suspend fun getPhotos(): List<PhotoDto>
    suspend fun getPosts(): List<PostDto>
    suspend fun getComments(): List<CommentDto>
}