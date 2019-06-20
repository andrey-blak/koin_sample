@file:JvmName("LiveDataUtils")

package com.example.pdp.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
	liveData.observe(this, Observer(body))
