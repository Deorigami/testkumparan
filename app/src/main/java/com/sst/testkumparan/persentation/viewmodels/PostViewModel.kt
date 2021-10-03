package com.sst.testkumparan.persentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sst.testkumparan.common.Resource
import com.sst.testkumparan.domain.use_cases.GetPostsUseCase
import com.sst.testkumparan.persentation.state.PostsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
):ViewModel() {
    val postsState = MutableLiveData(PostsState())

    init {
        getPosts()
    }

    fun getPosts(){
        getPostsUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    postsState.value = PostsState(posts = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    postsState.value = PostsState(error = result.message ?: "Unexpected Error Occured")
                }
                is Resource.Loading -> {
                    postsState.value = PostsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}