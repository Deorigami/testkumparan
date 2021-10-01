package com.sst.testkumparan.domain.use_cases

import com.sst.testkumparan.common.Resource
import com.sst.testkumparan.data.remote.dto.toComment
import com.sst.testkumparan.data.remote.dto.toPost
import com.sst.testkumparan.data.remote.dto.toUser
import com.sst.testkumparan.domain.models.Post
import com.sst.testkumparan.domain.repositories.JsonPlaceholderRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository : JsonPlaceholderRepo
) {
    operator fun invoke():Flow<Resource<List<Post>>> = flow {
        try {
            emit(Resource.Loading())
            val posts = repository.getPosts().map { it.toPost() }
            val users = repository.getUsers().map { it.toUser() }
            val comments = repository.getComments().map { it.toComment() }


            posts.map { post ->
                post.comments = comments.filter { comment ->
                    comment.postId == post.id
                }
                post.user =  users.find { it.id == post.userId }
            }

            emit(Resource.Success(posts))
        } catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Error occured"))
        } catch (e: IOException){
            emit(Resource.Error("Tidak bisa menghubungi server , periksa koneksi internet anda"))
        }
    }
}