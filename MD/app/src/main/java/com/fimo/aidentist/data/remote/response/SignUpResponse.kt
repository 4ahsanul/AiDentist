package com.fimo.aidentist.data.remote.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("messege")
	val messege: String

)
