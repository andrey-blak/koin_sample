package com.example.pdp.data.database

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.pdp.presentation.messages.Message

class MessageDataSource : PageKeyedDataSource<Int, Message>() {
	override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Message>) {
		val messages = generateMessages(0, params.requestedLoadSize)
		callback.onResult(messages, null, 1)
	}

	override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Message>) {
		val pageKey = params.key
		val messages = generateMessages(pageKey, params.requestedLoadSize)
		callback.onResult(messages, pageKey + 1)
	}

	override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Message>) {
		val pageKey = params.key
		val messages = generateMessages(pageKey, params.requestedLoadSize)
		val previousPageKey = if (pageKey > 0) {
			pageKey - 1
		} else {
			null
		}
		callback.onResult(messages, previousPageKey)
	}

	private fun generateMessages(pageKey: Int, count: Int): List<Message> {
		return List(count, { index ->
			Message("page: $pageKey message $index")
		})
	}

	class Factory : DataSource.Factory<Int, Message>() {
		override fun create(): DataSource<Int, Message> {
			return MessageDataSource()
		}
	}
}
