package com.sst.testkumparan.data.repositories

import com.sst.testkumparan.data.remote.JsonPlaceholderAPI
import com.sst.testkumparan.data.remote.dto.*
import com.sst.testkumparan.domain.repositories.JsonPlaceholderRepo
import javax.inject.Inject

class JSONPlaceHolderImpl @Inject constructor(
    private val api:JsonPlaceholderAPI
): JsonPlaceholderRepo{
    override suspend fun getAlbums(): List<AlbumDto> {
        return api.getAlbums()
    }

    override suspend fun getUsers(): MutableList<UserDto> {
        return api.getUsers()
    }

    override suspend fun getPhotos(): List<PhotoDto> {
        return api.getPhotos()
    }

    override suspend fun getPosts(): MutableList<PostDto> {
        return api.getPosts()
    }

    override suspend fun getComments(): List<CommentDto> {
        return api.getComments()
    }
}