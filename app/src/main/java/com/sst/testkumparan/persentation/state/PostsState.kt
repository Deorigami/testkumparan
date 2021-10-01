package com.sst.testkumparan.persentation.state

import com.sst.testkumparan.domain.models.Post
import com.sst.testkumparan.domain.models.User

data class PostsState(
    val isLoading :Boolean = false,
    val posts : List<Post> = emptyList(),
    val error :String = ""
)