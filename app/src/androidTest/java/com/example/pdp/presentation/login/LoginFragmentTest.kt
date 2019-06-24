package com.example.pdp.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pdp.R
import com.example.pdp.data.api.Api
import com.example.pdp.data.api.responses.LoginResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CompletableDeferred
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest : AutoCloseKoinTest() {
	@JvmField
	@Rule
	val Rule = InstantTaskExecutorRule()

	@Test
	fun shouldGoToMessagesAfterLogin() {
		val api = mockk<Api>()
		every { api.login(any(), any()) }
			.returns(CompletableDeferred(LoginResponse.Success("Empty")))

		stopKoin()
		startKoin {
			modules(module {
				single<Api> { api }
			})
		}

		val navController = mockk<NavController>()
		every { navController.navigate(any() as NavDirections) }
			.answers {
			}

		val loginFragmentScenario = launchFragmentInContainer<LoginFragment>()

		loginFragmentScenario.onFragment { fragment ->
			Navigation.setViewNavController(fragment.requireView(), navController)
		}

		Espresso.onView(ViewMatchers.withId(R.id.loginInput))
			.perform(ViewActions.typeText("a"))
		Espresso.onView(ViewMatchers.withId(R.id.passwordInput))
			.perform(ViewActions.typeText("a"))
		Espresso.onView(ViewMatchers.withId(R.id.loginButton))
			.perform(ViewActions.click())

		val action = LoginFragmentDirections.actionLoginFragmentToMessagesFragment("Empty")
		verify { navController.navigate(action) }
	}
}
