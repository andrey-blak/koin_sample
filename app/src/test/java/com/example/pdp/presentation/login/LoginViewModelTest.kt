package com.example.pdp.presentation.login

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

@ExtendWith(
	CoroutinesTestExtension::class,
	InstantExecutorExtension::class
)
class LoginViewModelTest {
	@Test
	fun testLogin() {
		val name = "Name"
		val api = mockk<Api>()
		every { api.login(any(), any()) }
			.returns(CompletableDeferred(LoginResponse.Success(name)))

		val model = LoginViewModel(api)

		model.login("login", "pass")

		Assertions.assertEquals(name, model.getUser().value?.name)
	}
}
