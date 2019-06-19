package com.example.pdp.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class CoroutinesTestExtension : AfterEachCallback, BeforeEachCallback {
	private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

	@Throws(Exception::class)
	override fun beforeEach(context: ExtensionContext) {
		Dispatchers.setMain(testDispatcher)
	}

	@Throws(Exception::class)
	override fun afterEach(context: ExtensionContext) {
		Dispatchers.resetMain()
		testDispatcher.cleanupTestCoroutines()
	}
}
