package com.sakina.workout.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakina.workout.models.LoginRequest
import com.sakina.workout.models.LoginResponse
import com.sakina.workout.models.RegisterRequest
import com.sakina.workout.models.RegisterResponse
import com.sakina.workout.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    val userRepository= UserRepository()
    val loginResponseLiveData= MutableLiveData<LoginResponse>()
    val errorLiveData = MutableLiveData<String>()
    val registerRequestLiveData = MutableLiveData<RegisterResponse>()
    val registerErrorLiveData = MutableLiveData<String?>()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }

    }

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response = userRepository.registerUser(registerRequest)
            if (response.isSuccessful){
                registerRequestLiveData.postValue(response.body())
            }
            else{
                registerErrorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}