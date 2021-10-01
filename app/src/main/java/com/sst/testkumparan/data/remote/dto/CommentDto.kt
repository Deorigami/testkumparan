package com.sst.testkumparan.data.remote.dto

import com.sst.testkumparan.domain.models.Comment

data class CommentDto(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)

fun CommentDto.toComment (): Comment {
    return Comment(
        body = body,
        email = email,
        id = id,
        name = name,
        postId = postId,
    )
}