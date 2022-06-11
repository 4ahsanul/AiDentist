package com.fimo.aidentist.data.remote.api

import com.fimo.aidentist.data.model.UserLoginModel
import com.fimo.aidentist.data.model.UserSignUpModel
import com.fimo.aidentist.data.remote.response.LoginResponse
import com.fimo.aidentist.data.remote.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    //User API
    @POST("login")
    suspend fun login(
        @Body user: UserLoginModel
    ): LoginResponse

    @POST("register")
    suspend fun signup(
        @Body user: UserSignUpModel
    ): SignUpResponse
}