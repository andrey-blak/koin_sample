package com.example.pdp

import androidx.multidex.MultiDexApplication
import com.example.pdp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

public class App : MultiDexApplication() {
	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidLogger()
			androidContext(this@App)
			modules(appModule)
		}
	}
}
