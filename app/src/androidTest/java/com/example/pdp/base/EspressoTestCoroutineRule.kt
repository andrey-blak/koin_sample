package com.example.pdp.base

import androidx.test.espresso.IdlingRegistry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class EspressoTestCoroutineRule : TestWatcher() {
	private val dispatcher = TestCoroutineDispatcher()
	private val dispatcherWrapper = JobCheckingDispatcherWrapper(dispatcher)
	private val idlingResource = CoroutineDispatcherIdlingResource(dispatcherWrapper)

	override fun starting(description: Description?) {
		super.starting(description)
		Dispatchers.setMain(dispatcherWrapper)

		IdlingRegistry.getInstance().register(idlingResource)
	}

	override fun finished(description: Description?) {
		super.finished(description)
		Dispatchers.resetMain()
		dispatcher.cleanupTestCoroutines()

		IdlingRegistry.getInstance().unregister(idlingResource)
	}
}
