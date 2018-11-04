package com.iiinaiii.punks.dagger

import com.iiinaiii.punks.PunksApplication
import com.iiinaiii.punks.ui.home.HomeActivity

fun HomeActivity.inject() {
    DaggerHomeComponent.builder()
        .coreComponent(PunksApplication.coreComponent(this))
        .homeModule(HomeModule(this))
        .build()
        .inject(this)
}