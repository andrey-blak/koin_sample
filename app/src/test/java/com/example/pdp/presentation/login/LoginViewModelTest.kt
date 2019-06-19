package com.example.pdp.presentation.login

import androidx.lifecycle.SavedStateHandle
import com.example.pdp.R
import com.example.pdp.base.CoroutinesTestExtension
import com.example.pdp.base.InstantExecutorExtension
import com.example.pdp.data.api.Api
import com.example.pdp.data.api.responses.LoginResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.context.startKoin
import org.koin.dsl.module

@ExtendWith(
	CoroutinesTestExtension::class,
	InstantExecutorExtension::class
)
class LoginViewModelTest {
	@Test
	fun testLoginError() {
		val api = mockk<Api>()
		every { api.login(any(), any()) }
			.returns(CompletableDeferred(LoginResponse.Error()))

		startKoin {
			modules(module {
				single<Api> { api }
			})
		}

		val model = LoginViewModel(SavedStateHandle())

		model.login("login", "pass")

		Assertions.assertEquals(R.string.login_incorrect_credentials_error, model.getError().value?.messageResId)
	}
}
