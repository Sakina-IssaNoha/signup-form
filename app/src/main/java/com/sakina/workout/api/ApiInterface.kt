package com.sakina.workout.api

import com.sakina.workout.models.LoginRequest
import com.sakina.workout.models.LoginResponse
import com.sakina.workout.models.RegisterResponse
import com.sakina.workout.models.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("/login")
    fun LoginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
}