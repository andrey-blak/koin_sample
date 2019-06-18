package com.example.pdp.data.api

import com.example.pdp.data.api.responses.LoginResponse
import kotlinx.coroutines.Deferred

interface Api {
	fun login(login: String, password: String): Deferred<LoginResponse>
}
