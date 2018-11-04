package com.iiinaiii.punks.dagger

import com.iiinaiii.punks.ui.home.HomeActivity
import dagger.Component

@Component(
    modules = [HomeModule::class],
    dependencies = [CoreComponent::class]
)
interface HomeComponent : BaseComponent<HomeActivity> {

    @Component.Builder
    interface Builder {
        fun build(): HomeComponent
        fun coreComponent(component: CoreComponent): Builder
        fun homeModule(module: HomeModule): Builder
    }
}