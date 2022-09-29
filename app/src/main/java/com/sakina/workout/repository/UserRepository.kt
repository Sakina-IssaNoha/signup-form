package com.sakina.workout.repository

import com.sakina.workout.api.ApiClient
import com.sakina.workout.api.ApiInterface
import com.sakina.workout.models.LoginRequest
import com.sakina.workout.models.LoginResponse
import com.sakina.workout.models.RegisterRequest
import com.sakina.workout.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>
    = withContext(Dispatchers.IO){
        val response = apiClient.LoginUser(loginRequest)
        return@withContext response
    }

    suspend fun registerUser(registerRequest: RegisterRequest)
            = withContext(Dispatchers.IO){
        val response = apiClient.registerUser(registerRequest)
        return@withContext response
}
}