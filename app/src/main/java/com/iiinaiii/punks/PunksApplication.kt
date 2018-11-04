package com.iiinaiii.punks

import android.app.Application
import android.content.Context
import com.iiinaiii.punks.dagger.CoreComponent
import com.iiinaiii.punks.dagger.DaggerCoreComponent

class PunksApplication : Application() {

    val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent
            .builder()
            .build()
    }

    companion object {
        fun coreComponent(context: Context) =
            (context.applicationContext as PunksApplication).coreComponent
    }
}