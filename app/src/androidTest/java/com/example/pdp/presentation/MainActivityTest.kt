package com.example.pdp.presentation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.pdp.R
import com.example.pdp.data.api.Api
import com.example.pdp.data.api.responses.LoginResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class MainActivityTest {
	@JvmField
	@Rule
	val activityRule = ActivityTestRule(MainActivity::class.java)

	@Test
	fun shouldShowMessagesAfterLogin() {
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

		ActivityScenario.launch(MainActivity::class.java)

		Espresso.onView(ViewMatchers.withId(R.id.loginInput))
			.perform(ViewActions.typeText("a"))
		Espresso.onView(ViewMatchers.withId(R.id.passwordInput))
			.perform(ViewActions.typeText("a"))
		Espresso.onView(ViewMatchers.withId(R.id.loginButton))
			.perform(ViewActions.click())

		Espresso.onView(ViewMatchers.withId(R.id.messagesRecyclerView))
			.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
	}
}

