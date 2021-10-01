package com.sst.testkumparan.domain.use_cases

import com.sst.testkumparan.common.Resource
import com.sst.testkumparan.data.remote.dto.toAlbum
import com.sst.testkumparan.domain.models.Album
import com.sst.testkumparan.domain.repositories.JsonPlaceholderRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val repository : JsonPlaceholderRepo
) {
    operator fun invoke():Flow<Resource<List<Album>>> = flow {
        try {
            emit(Resource.Loading())
            val albums = repository.getAlbums().map { it.toAlbum() }
            emit(Resource.Success(albums))
        } catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Error occured"))
        } catch (e: IOException){
            emit(Resource.Error("Tidak bisa menghubungi server , periksa koneksi internet anda"))
        }
    }
}