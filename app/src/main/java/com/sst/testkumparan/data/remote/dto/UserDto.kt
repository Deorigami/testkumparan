package com.sst.testkumparan.data.remote.dto

import com.sst.testkumparan.domain.models.User

data class UserDto(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

fun UserDto.toUser():User {
    return User(
        address = "${address.street}, ${address.city}",
        company = company.name,
        email = email,
        id = id,
        name = name,
        phone = phone,
        username = username
    )
}