package com.sst.testkumparan.data.remote

import com.sst.testkumparan.data.remote.dto.*
import retrofit2.http.GET

interface JsonPlaceholderAPI {
    @GET("posts")
    suspend fun getPosts() : MutableList<PostDto>

    @GET("comments")
    suspend fun getComments() : List<CommentDto>

    @GET("albums")
    suspend fun getAlbums() : List<AlbumDto>

    @GET("photos")
    suspend fun getPhotos() : List<PhotoDto>

    @GET("users")
    suspend fun getUsers() : MutableList<UserDto>
}