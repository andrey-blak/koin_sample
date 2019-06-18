package com.example.pdp.data.api

import com.example.pdp.data.api.responses.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class MockApi(
	private val coroutineDispatcher: CoroutineDispatcher
) : Api {
	override fun login(login: String, password: String) = GlobalScope.async(coroutineDispatcher) {
		delay(DELAY_MS)
		if (login == "asd" && password == "asd") {
			LoginResponse.Success("Smith")
		} else {
			LoginResponse.Error()
		}
	}

	companion object {
		private const val DELAY_MS: Long = 50
	}
}
