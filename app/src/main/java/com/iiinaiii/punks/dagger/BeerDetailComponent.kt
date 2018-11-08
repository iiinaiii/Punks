package com.iiinaiii.punks.dagger

import com.iiinaiii.punks.ui.detail.BeerDetailActivity
import dagger.Component

@Component(
    modules = [BeerDetailModule::class],
    dependencies = [CoreComponent::class]
)
interface BeerDetailComponent : BaseComponent<BeerDetailActivity> {

    @Component.Builder
    interface Builder {
        fun build(): BeerDetailComponent
        fun coreComponent(component: CoreComponent): Builder
        fun beerDetailModule(module: BeerDetailModule): Builder
    }
}