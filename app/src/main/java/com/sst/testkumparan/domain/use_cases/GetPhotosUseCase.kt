package com.sst.testkumparan.domain.use_cases

import com.sst.testkumparan.common.Resource
import com.sst.testkumparan.data.remote.dto.toPhoto
import com.sst.testkumparan.domain.models.Photo
import com.sst.testkumparan.domain.repositories.JsonPlaceholderRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository : JsonPlaceholderRepo
) {
    operator fun invoke():Flow<Resource<List<Photo>>> = flow {
        try {
            emit(Resource.Loading())
            val photos = repository.getPhotos().map { it.toPhoto() }
            emit(Resource.Success(photos))
        } catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Error occured"))
        } catch (e: IOException){
            emit(Resource.Error("Tidak bisa menghubungi server , periksa koneksi internet anda"))
        }
    }
}