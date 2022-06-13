package com.fimo.aidentist.di

import com.fimo.aidentist.data.remote.UserRepository
import com.fimo.aidentist.data.remote.api.ApiConfig

class Injection {
    companion object {
        fun provideUserRepository(): UserRepository {
            val apiService = ApiConfig.getApiService()
            return UserRepository.getInstance(apiService)
        }
    }
}