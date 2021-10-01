package com.sst.testkumparan.persentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sst.testkumparan.common.Resource
import com.sst.testkumparan.domain.models.User
import com.sst.testkumparan.domain.use_cases.*
import com.sst.testkumparan.persentation.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
):ViewModel() {
    val userState = MutableLiveData(UserState())


    fun getUser(userId:Int){
        getUsersUseCase(userId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    userState.value = UserState(users = result.data)
                }
                is Resource.Error -> {
                    userState.value = UserState(error = result.message ?: "Unexpected Error Occured")
                }
                is Resource.Loading -> {
                    userState.value = UserState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }




}