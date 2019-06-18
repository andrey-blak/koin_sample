package com.example.pdp.data.api.responses

sealed class LoginResponse {
	data class Success(
		val name: String
	) : LoginResponse()

	class Error : LoginResponse()
}
