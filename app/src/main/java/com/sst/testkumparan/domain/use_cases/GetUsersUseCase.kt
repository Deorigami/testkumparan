package com.sst.testkumparan.domain.use_cases

import com.sst.testkumparan.common.Resource
import com.sst.testkumparan.data.remote.dto.toAlbum
import com.sst.testkumparan.data.remote.dto.toPhoto
import com.sst.testkumparan.data.remote.dto.toUser
import com.sst.testkumparan.domain.models.User
import com.sst.testkumparan.domain.repositories.JsonPlaceholderRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository : JsonPlaceholderRepo
) {
    operator fun invoke(userId:Int): Flow<Resource<User?>> = flow {
        try {
            emit(Resource.Loading())
            val users = repository.getUsers().map { it.toUser() }
            val albums = repository.getAlbums().map { it.toAlbum() }
            val photos = repository.getPhotos().map { it.toPhoto() }

            albums.map { album ->
                album.photos = photos.filter { photo ->
                    photo.albumId == album.id
                }
            }

            users.map { user ->
                user.albums = albums.filter { album ->
                    album.userId == user.id
                }
            }

            val user = users.find { it.id == userId }

            emit(Resource.Success(user))
        } catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Error occured"))
        } catch (e: IOException){
            emit(Resource.Error("Tidak bisa menghubungi server , periksa koneksi internet anda"))
        }
    }
}