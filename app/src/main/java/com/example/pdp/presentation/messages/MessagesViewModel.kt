package com.example.pdp.presentation.messages

import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent

class MessagesViewModel : ViewModel(), KoinComponent {
	val messages = MessageDataSource.Factory(Dispatchers.Default)
		.toLiveData(20)
}
