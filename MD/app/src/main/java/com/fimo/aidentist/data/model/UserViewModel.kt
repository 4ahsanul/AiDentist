package com.fimo.aidentist.data.model

import androidx.lifecycle.*
import com.fimo.aidentist.data.local.UserPreference
import com.fimo.aidentist.data.remote.UserRepository
import com.fimo.aidentist.data.remote.response.LoginResponse
import com.fimo.aidentist.data.remote.response.SignUpResponse
import kotlinx.coroutines.launch

class UserViewModel(private val userRepo: UserRepository): ViewModel() {
    private var _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private var _signUpResponse = MutableLiveData<SignUpResponse>()
    val signUpResponse: LiveData<SignUpResponse> = _signUpResponse

    //Login user
    fun login(user: UserLoginModel, pref: UserPreference) {
        viewModelScope.launch {
            userRepo.authenticate(user, pref).collect{ response ->
                _loginResponse.value = response
            }
        }
    }

    //SignUp user
    fun signUp(user: UserSignUpModel) {
        viewModelScope.launch {
            userRepo.createUser(user).collect { response ->
                _signUpResponse.value = response
            }
        }
    }


    //LogOut user
    fun logout(pref: UserPreference) {
        viewModelScope.launch {
            pref.clearUser()
        }
    }

    //Load user
    fun loadUser(pref: UserPreference): LiveData<UserDataModel> {
        return pref.getUser().asLiveData()
    }





}