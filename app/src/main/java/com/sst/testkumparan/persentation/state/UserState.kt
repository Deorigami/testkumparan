package com.sst.testkumparan.persentation.state

import com.sst.testkumparan.domain.models.User

data class UserState(
    val isLoading :Boolean = false,
    val users : User? = null,
    val error :String = ""
)