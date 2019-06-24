package com.example.pdp

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.github.tmurakami.dexopener.DexOpener

@Suppress("unused")
class DexOpenerRunner : AndroidJUnitRunner() {
	override fun newApplication(classLoader: ClassLoader?, className: String?, context: Context?): Application {
		DexOpener.install(this)
		val appClassName = "com.example.pdp.App"
		return super.newApplication(classLoader, appClassName, context)
	}
}
