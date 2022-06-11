package com.fimo.aidentist.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fimo.aidentist.data.remote.UserRepository
import com.fimo.aidentist.di.Injection

class ViewModelFactory private constructor(private val userRepo: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory {
            return instance ?: synchronized(this) {
                val viewModelFactory = ViewModelFactory(Injection.provideUserRepository())
                instance = viewModelFactory
                viewModelFactory
            }
        }
    }
}