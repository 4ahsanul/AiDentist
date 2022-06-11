package com.fimo.aidentist.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("LoginResult")
    val loginResult: LoginResult?,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("messege")
    val messege: String
)

data class LoginResult(
    @field:SerializedName("UserId")
    val userId: String,

    @field:SerializedName("Name")
    val name: String,

    @field:SerializedName("token")
    val token: String
)
