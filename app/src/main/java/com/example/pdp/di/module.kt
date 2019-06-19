package com.example.pdp.di

import com.example.pdp.data.api.Api
import com.example.pdp.data.api.MockApi
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModule = module {
	single<Api> { MockApi(Dispatchers.Default) }
}
