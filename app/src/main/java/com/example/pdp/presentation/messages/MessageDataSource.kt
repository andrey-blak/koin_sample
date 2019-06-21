package com.example.pdp.presentation.messages

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MessageDataSource(
	private val dispatcher: CoroutineDispatcher
) : PositionalDataSource<Message>() {
	override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Message>) {
		GlobalScope.launch(dispatcher) {
			val messages = generateMessages(0, params.requestedLoadSize)
			callback.onResult(messages, messages.size, 1500)
		}
	}

	override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Message>) {
		GlobalScope.launch(dispatcher) {
			val messages = generateMessages(params.startPosition, params.loadSize)
			callback.onResult(messages)
		}
	}

	private suspend fun generateMessages(start: Int, count: Int): List<Message> {
		delay(200)
		return List(count) { index ->
			Message("message ${start + index}")
		}
	}

	class Factory(
		private val dispatcher: CoroutineDispatcher
	) : DataSource.Factory<Int, Message>() {
		override fun create(): DataSource<Int, Message> {
			return MessageDataSource(dispatcher)
		}
	}
}
