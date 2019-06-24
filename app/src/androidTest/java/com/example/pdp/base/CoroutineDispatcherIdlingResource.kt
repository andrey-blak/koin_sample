package com.example.pdp.base

import androidx.test.espresso.IdlingResource

class CoroutineDispatcherIdlingResource(
	private val dispatcher: JobCheckingDispatcherWrapper
) : IdlingResource {
	@Volatile
	private var resourceCallback: IdlingResource.ResourceCallback? = null

	init {
		dispatcher.completionEvent = {
			if (isIdleNow) {
				resourceCallback?.onTransitionToIdle()
			}
		}
	}

	override fun getName(): String {
		return "CoroutineDispatcherIdlingResource"
	}

	override fun isIdleNow(): Boolean {
		return !dispatcher.isAnyJobRunning
	}

	override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
		resourceCallback = callback
	}
}
