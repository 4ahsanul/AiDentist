package com.fimo.aidentist.data.remote

import com.fimo.aidentist.data.local.UserPreference
import com.fimo.aidentist.data.model.UserDataModel
import com.fimo.aidentist.data.model.UserLoginModel
import com.fimo.aidentist.data.model.UserSignUpModel
import com.fimo.aidentist.data.remote.api.ApiService
import com.fimo.aidentist.data.remote.response.LoginResponse
import com.fimo.aidentist.data.remote.response.SignUpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(private val apiService: ApiService) {

    fun authenticate(user: UserLoginModel, pref: UserPreference): Flow<LoginResponse> = flow {
        emit(LoginResponse(null, false, ""))
        try {
            val response = apiService.login(user)
            val userPreference = UserDataModel(
                token = response.loginResult?.token as String,
                isLogin = true
            )
            pref.saveUser(userPreference)
            emit(response)
        } catch (e: Exception) {
            emit(LoginResponse(null, true, e.message.toString()))
        }
    }

    //Create User
    fun createUser(user: UserSignUpModel): Flow<SignUpResponse> = flow {
        emit(SignUpResponse(false, ""))
        try {
            val response = apiService.signup(user)
            emit(response)
        } catch (e: Exception) {
            emit(SignUpResponse(true, e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService): UserRepository {
            return instance ?: synchronized(this) {
                val userRepo = UserRepository(apiService)
                instance = userRepo
                userRepo
            }
        }
    }
}