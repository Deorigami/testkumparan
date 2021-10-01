package com.sst.testkumparan.persentation.state

import com.sst.testkumparan.domain.models.Comment
import com.sst.testkumparan.domain.models.User

data class CommentsState(
    val isLoading :Boolean = false,
    val comments : List<Comment> = emptyList(),
    val error :String = ""
)