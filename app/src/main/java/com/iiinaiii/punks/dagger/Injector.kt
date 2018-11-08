package com.iiinaiii.punks.dagger

import com.iiinaiii.punks.PunksApplication
import com.iiinaiii.punks.ui.detail.BeerDetailActivity
import com.iiinaiii.punks.ui.home.HomeActivity

/**
 * Inject [HomeActivity].
 */
fun HomeActivity.inject() {
    DaggerHomeComponent.builder()
        .coreComponent(PunksApplication.coreComponent(this))
        .homeModule(HomeModule(this))
        .build()
        .inject(this)
}

/**
 * Inject [BeerDetailActivity].
 */
fun BeerDetailActivity.inject(beerId: Int) {
    DaggerBeerDetailComponent.builder()
        .coreComponent(PunksApplication.coreComponent(this))
        .beerDetailModule(BeerDetailModule(beerId, this))
        .build()
        .inject(this)
}

