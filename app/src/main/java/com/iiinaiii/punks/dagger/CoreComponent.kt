package com.iiinaiii.punks.dagger

import dagger.Component

@Component(
    modules = [BeersDataModule::class]
)
interface CoreComponent {

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
    }
}