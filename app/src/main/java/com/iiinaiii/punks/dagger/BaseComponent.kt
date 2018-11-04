package com.iiinaiii.punks.dagger

import android.app.Activity

interface BaseComponent<T : Activity> {

    fun inject(target: T)
}