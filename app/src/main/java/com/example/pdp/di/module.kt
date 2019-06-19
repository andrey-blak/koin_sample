package com.example.pdp.di

import com.example.pdp.data.api.Api
import com.example.pdp.data.api.MockApi
import com.example.pdp.presentation.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
	single<Api> { MockApi(Dispatchers.Default) }
	viewModel { LoginViewModel(get()) }
}
