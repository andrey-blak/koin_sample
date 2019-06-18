package com.example.pdp.presentation.login

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pdp.R
import com.example.pdp.data.api.Api
import com.example.pdp.data.api.responses.LoginResponse
import com.example.pdp.utils.LiveEvent
import com.example.pdp.utils.exhaustive
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class LoginViewModel : ViewModel(), KoinComponent {
	private val user: MutableLiveData<UserData> = MutableLiveData()
	private val error: MutableLiveData<UserError> = LiveEvent()
	private val api: Api by inject()

	fun getUser(): LiveData<UserData> {
		return user
	}

	fun getError(): LiveData<UserError> {
		return error
	}

	fun login(login: String, password: String) {
		viewModelScope.launch {
			val response = api.login(login, password).await()
			when (response) {
				is LoginResponse.Success -> {
					user.postValue(UserData(response.name))
				}
				is LoginResponse.Error -> {
					error.postValue(UserError(R.string.login_incorrect_credentials_error))
				}
			}.exhaustive
		}
	}

	data class UserData(
		val name: String
	)

	data class UserError(
		@StringRes val messageResId: Int
	)
}
