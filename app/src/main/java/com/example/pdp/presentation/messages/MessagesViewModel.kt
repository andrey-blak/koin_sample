package com.example.pdp.presentation.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.pdp.data.database.MessageDataSource
import org.koin.core.KoinComponent

class MessagesViewModel : ViewModel(), KoinComponent {
	val messages: LiveData<PagedList<Message>> =
		MessageDataSource.Factory().toLiveData(20)
}
