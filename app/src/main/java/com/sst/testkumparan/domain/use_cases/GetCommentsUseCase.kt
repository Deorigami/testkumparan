package com.sst.testkumparan.domain.use_cases

import com.sst.testkumparan.common.Resource
import com.sst.testkumparan.data.remote.dto.toComment
import com.sst.testkumparan.domain.models.Comment
import com.sst.testkumparan.domain.repositories.JsonPlaceholderRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository : JsonPlaceholderRepo
) {
    operator fun invoke():Flow<Resource<List<Comment>>> = flow {
        try {
            emit(Resource.Loading())
            val comments = repository.getComments().map { it.toComment() }
            emit(Resource.Success(comments))
        } catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Error occured"))
        } catch (e: IOException){
            emit(Resource.Error("Tidak bisa menghubungi server , periksa koneksi internet anda"))
        }
    }
}