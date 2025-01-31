package com.example.pdp.presentation.login

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pdp.R
import com.example.pdp.data.api.Api
import com.example.pdp.data.api.responses.LoginResponse
import com.example.pdp.utils.LiveEvent
import com.example.pdp.utils.exhaustive
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.get

class LoginViewModel(
	private val state: SavedStateHandle
) : ViewModel(), KoinComponent {
	private val navigateToMessages = LiveEvent<String>()
	private val navigateToRegister = LiveEvent<Nothing>()
	private val error = LiveEvent<UserError>()
	private val api: Api = get()

	fun getNavigationToMessages(): LiveData<String> {
		return navigateToMessages
	}

	fun getNavigationToRegister(): LiveData<Nothing> {
		return navigateToRegister
	}

	fun getError(): LiveData<UserError> {
		return error
	}

	fun login(login: String, password: String) {
		viewModelScope.launch {
			val response = api.login(login, password).await()
			when (response) {
				is LoginResponse.Success -> {
					state.set("login", login)
					state.set("pass", password)
					navigateToMessages.postValue(response.name)
				}
				is LoginResponse.Error -> {
					error.postValue(UserError(R.string.login_incorrect_credentials_error))
				}
			}.exhaustive
		}
	}

	fun register() {
		navigateToRegister.call()
	}

	data class UserError(
		@StringRes val messageResId: Int
	)
}
